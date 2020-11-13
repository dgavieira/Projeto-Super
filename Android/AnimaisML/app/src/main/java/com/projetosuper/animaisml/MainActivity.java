package com.projetosuper.animaisml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button btnImagem, btnCategorizar;
    TextView txtCategoria;
    Uri imagemUri = Uri.parse("android.resource://com.projetosuper.animaisml/drawable/ic_user.png");

    boolean fotoCamera;
    private Bitmap bitmap;

    //variaveis do classificador ML
    private String mModelPath = "converted_model.tflite";
    private String mLabelPath = "labels.txt";
    private Classifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        btnImagem = findViewById(R.id.btnImagem);
        btnCategorizar = findViewById(R.id.btnCategorizar);


        txtCategoria = findViewById(R.id.txtCategoria);

        try {
            initClassifier();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnCategorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                String result = classifier.recognizeImage(bitmap);
                txtCategoria.setText(result);
            }
        });
    }

    private void initClassifier() throws IOException {
        classifier = new Classifier(getAssets(), mModelPath, mLabelPath);
    }


    public void clicaTirarFoto(View v){
        fotoCamera = true;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,0);
    }

    public void clicaCarregarImagem(View v){
        fotoCamera=false;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Contact Image"), 1);
    }

    private static Bitmap resizeImage(Context context, Bitmap bmpOriginal, float newWidth, float newHeight){
        Bitmap novoBmp = null;
        int w = bmpOriginal.getWidth();
        int h = bmpOriginal.getHeight();
        float densityFactor = context.getResources().getDisplayMetrics().density;
        float novoW = newWidth * densityFactor;
        float novoH = newHeight * densityFactor;
        float scalaW = novoW/w;
        float scalaH = novoH/h;

        Matrix matrix = new Matrix();

        matrix.postScale(scalaW,scalaH);
        novoBmp = Bitmap.createBitmap(bmpOriginal,0,0,w,h,matrix,true);
        return novoBmp;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (fotoCamera) {
            super.onActivityResult(requestCode, resultCode, data);
            InputStream stream = null;
            if (requestCode == 0 && resultCode == RESULT_OK) {
                try {
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    stream = getContentResolver().openInputStream(data.getData());
                    bitmap = BitmapFactory.decodeStream(stream);
                    imageView.setImageBitmap(resizeImage(this, bitmap, 120, 120));
                    imageView.setRotation(90);
                    imagemUri = data.getData();
                    imageView.setImageURI(data.getData());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (stream != null)
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

            }
        }else{

            if (resultCode == RESULT_OK) {
                if (requestCode == 1) {
                    imagemUri = data.getData();
                    imageView.setImageURI(data.getData());
                }
            }
        }
    }
}