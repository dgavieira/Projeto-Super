package com.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.petshop.adapter.AnimalViewAdapter;
import com.petshop.db.AnimalDAO;
import com.petshop.db.Database;
import com.petshop.model.Animais;
import com.petshop.model.DbBitmapUtility;
import com.petshop.resource.CircleImageView;

import java.io.ByteArrayOutputStream;


public class AtualizaAnimal extends AppCompatActivity {

    CircleImageView imageViewProfilePet;
    EditText edtNome, edtRaca;
    AnimalViewAdapter adapter;

    String categoria;
    String nome;

    Button btnAtualizar, btnVoltar;

    Animais animais;
    Spinner categoriaSelected;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atualiza_cadastro_animal);


        imageViewProfilePet = findViewById(R.id.imageProfilePet);
        edtNome = findViewById(R.id.activity_dados_pessoas_editText_nome_recebido);
        edtRaca = findViewById(R.id.activity_dados_pessoas_editText_raca_recebido);
        categoriaSelected = findViewById(R.id.activity_dados_pessoas_spinner_categorias_recebido);

        // Widgets do segundo layout (Botões)
        btnAtualizar = findViewById(R.id.activity_dados_pessoas_button_atualizar);
        btnVoltar = findViewById(R.id.activity_dados_pessoas_button_voltar);

        Intent intent = getIntent();


        if (intent.getStringExtra("categoria").equals("Cachorro")){
            //activity_dados_pessoas_icone.setImageResource(R.drawable.ic_dog);
            categoriaSelected.setSelection(1);
            categoria = "Cachorro";
        }else{
            //activity_dados_pessoas_icone.setImageResource(R.drawable.ic_cat);
            categoriaSelected.setSelection(0);
            categoria = "Gato";

        }

        imageViewProfilePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });

        categoriaSelected.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(categoriaSelected.getSelectedItem().equals("Gato")){
                    categoria = "Gato";
                }else{
                    categoria = "Cachorro";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        nome = intent.getStringExtra("nome");
        edtNome.setText(intent.getStringExtra("nome"));
        edtRaca.setText(intent.getStringExtra("raca"));

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update(edtNome.getText().toString(), edtRaca.getText().toString(), categoria, imageViewToByte(imageViewProfilePet));
                Toast.makeText(AtualizaAnimal.this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();

            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void atualizaAnimais(Animais animais) {

        animais.setNome(edtNome.getText().toString());
        animais.setRaca(edtRaca.getText().toString());
        animais.setCategoria(categoria);
        animais.setFoto(imageViewToByte(imageViewProfilePet));
        database.updateAnimal(animais);

        Toast.makeText(this, "Contato Editado Com Sucesso", Toast.LENGTH_SHORT).show();
        finish();

    }

    private void update(String newNome, String newCategoria, String newRaca, byte[] newPhoto)
    {

        int id = adapter.getSelectedItemID();

        Database db= new Database(this);
        boolean updated=db.update(newNome, newCategoria, newRaca, newPhoto ,id);

        if(updated) {
            edtNome.setText("");
            edtRaca.setText("");
            imageViewProfilePet.setImageBitmap(null);

        }else {
            Toast.makeText(this,"Erro ao atualizar contato",Toast.LENGTH_SHORT).show();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent dados) {

        super.onActivityResult(requestCode, resultCode, dados);
        if(requestCode == 1) {
            try{
                Bitmap fotoRegistrada = (Bitmap) dados.getExtras().get("data");
                imageViewProfilePet.setImageBitmap(fotoRegistrada);

                byte[] fotoEmBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();

                //Conversão, codificação e transformação em byte 64.

                fotoRegistrada.compress(Bitmap.CompressFormat.PNG, 70, streamDaFotoEmBytes);
                fotoEmBytes = streamDaFotoEmBytes.toByteArray();

            }catch (Exception e ) {

            }

        }
    }
    public static byte[] imageViewToByte(ImageView image){
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}