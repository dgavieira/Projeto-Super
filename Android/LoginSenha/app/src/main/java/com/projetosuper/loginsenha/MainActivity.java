package com.projetosuper.loginsenha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "Teste";
    EditText edtusername, edtpassword;
    Button btnsign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        btnsign = findViewById(R.id.btnsign);


        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_correto = "root";
                String password_correto = "root";

                if(edtusername.getText().toString().isEmpty() && edtpassword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Both fields empty", Toast.LENGTH_SHORT).show();
                }else if(edtusername.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Field username empty", Toast.LENGTH_SHORT).show();
                }else if(edtpassword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Field password empty", Toast.LENGTH_SHORT).show();
                }else{
                    if(edtusername.getText().toString().equals(username_correto) && edtpassword.getText().toString().equals(password_correto)){
                        //Toast.makeText(MainActivity.this, "LOGGED IN", Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_display_message);
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid Access", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
}