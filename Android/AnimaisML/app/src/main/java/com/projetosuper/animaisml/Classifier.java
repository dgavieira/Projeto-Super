package com.projetosuper.animaisml;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import org.tensorflow.lite.Interpreter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class Classifier {
    private Interpreter interpreter;
    private List<String> labelList;
    private int INPUT_SIZE = 224;
    private int PIXEL_SIZE = 3;
    private float IMAGE_STD = 255.0f;
    private float THRESHOLD = 0.5f;

    Classifier(AssetManager assetManager, String modelPath, String labelPath) throws IOException {
        Interpreter.Options options = new Interpreter.Options();
        options.setNumThreads(5);
        options.setUseNNAPI(true);

        interpreter = new Interpreter(loadModelFile(assetManager, modelPath), options);
        labelList = loadLabelList(assetManager, labelPath);
    }

    private List<String> loadLabelList(AssetManager assetManager, String labelPath) throws IOException {
        List<String> labelList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open(labelPath))); // both simplify and optimize the text reading
        String line;
        while ((line = reader.readLine()) != null){
            labelList.add(line);
        }
        reader.close();
        return labelList;
    }

    private MappedByteBuffer loadModelFile(AssetManager assetManager, String MODEL_FILE) throws IOException {
        //get the file descriptor of the model file
        AssetFileDescriptor fileDescriptor = assetManager.openFd(MODEL_FILE);
        // open the input stream
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        //read the file channels along with its offset and length as follows
        FileChannel fileChannel  = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength); // finally we load the tfLite model as
    }
    String recognizeImage(Bitmap bitmap) {
        //List<Recognition> recognizeImage(Bitmap bitmap) {
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);
        ByteBuffer byteBuffer = convertBitmapToByteBuffer(scaledBitmap); // Tensor allocation and normalization
        float [] [] result = new float[1][1];
        interpreter.run(byteBuffer, result);
        return getSortedResultFloat(result);
    }

    private ByteBuffer convertBitmapToByteBuffer(Bitmap bitmap){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4*INPUT_SIZE*INPUT_SIZE*PIXEL_SIZE);

        byteBuffer.order(ByteOrder.nativeOrder());
        int [] intValues = new int [INPUT_SIZE*INPUT_SIZE];
        bitmap.getPixels(intValues, 0 , bitmap.getWidth(), 0,0, bitmap.getWidth(), bitmap.getHeight());
        int pixel = 0;

        for (int i = 0; i< INPUT_SIZE; ++i){
            for (int j = 0; j<INPUT_SIZE; ++j){
                final int val = intValues[pixel++];

                byteBuffer.putFloat((((val >> 16) & 0xFF) )/IMAGE_STD);
                byteBuffer.putFloat((((val >> 8) & 0xFF) )/IMAGE_STD);
                byteBuffer.putFloat((((val) & 0xFF))/IMAGE_STD);
            }
        }
        return byteBuffer;
    }
    private String getSortedResultFloat(float [][]labelProbArray){

        String label;
        float confidence = labelProbArray[0][0];
        if (confidence> THRESHOLD){
            label = labelList.get(1);

        }
        else {
            label = labelList.get(0);
            confidence = 1-confidence;
        }

        //return (label+ " , Confidence: "+confidence).toString();
        return label;
    }

}

