package com.projetosuper.app05cadastro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Runnable{
    EditText edtnome, edtfone, edtmail, edtcep;
    Button btncadastrar;
    ProgressBar progresso;
    Spinner spnidade;
    ArrayAdapter<String> adapter_idades;
    private static final String[] idades = {"18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50"};
    Thread t;
    Handler h;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Views by ID - EDIT TEXT
        edtnome = findViewById(R.id.edtnome);
        edtfone = findViewById(R.id.edtfone);
        edtmail = findViewById(R.id.edtmail);
        edtcep = findViewById(R.id.edtcep);

        //Find Views by ID - BUTTON
        btncadastrar = findViewById(R.id.btncadastrar);

        //Find View by ID - PROGRESS BAR
        progresso = findViewById(R.id.progresso);

        //Setting up the adapter and the Spinner
        adapter_idades = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,idades);
        spnidade = findViewById(R.id.spnidade);
        spnidade.setAdapter(adapter_idades);

        //Setting up the Button OnClickListener event
        btncadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if there is any empty field
                if(edtnome.getText().toString().isEmpty() || edtcep.getText().toString().isEmpty() || edtfone.getText().toString().isEmpty() || edtmail.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Campo vazio. Preencha seus dados", Toast.LENGTH_LONG).show();
                }else{
                    btncadastrar.setEnabled(true);
                    h = new Handler();
                    t = new Thread(MainActivity.this);
                    t.start();
                }
            }
        });

    }
    public void run(){
        i = 1;
        try{
            while(i<=100){
                Thread.sleep(50);
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        progresso.setProgress(i++);
                    }
                });
            }
            h.post(new Runnable() {
                @Override
                public void run() {
                    //Create String on set Message
                    String message = "Nome: " + edtnome.getText().toString() + "\n" +
                            "Telefone: " + edtfone.getText().toString() + "\n" +
                            "CEP: " + edtcep.getText().toString() + "\n" +
                            "Idade: " + spnidade.getSelectedItem().toString() + "\n";

                    //Create Alert Dialog
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Usuário Cadastrado");
                    dialogo.setMessage(message);
                    dialogo.setNeutralButton("Ok",null);
                    dialogo.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            progresso.setProgress(0);
                            edtnome.setText("");
                            edtfone.setText("");
                            edtcep.setText("");
                            edtmail.setText("");
                        }
                    });
                    dialogo.show();
                }
            });
        }catch(InterruptedException e){
            //codigo de erro
        }
    }
}