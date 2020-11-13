package com.projetosuper.exbarraprogresso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements Runnable {
    ProgressBar barra;
    Button btnp;
    Thread t;
    Handler h;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barra = findViewById(R.id.progresso);
        btnp = findViewById(R.id.btndownload);

        btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                h = new Handler();
                t = new Thread(MainActivity.this);
                t.start();
            }
        });
    }
    public void run(){
        i = 1;
        try{
            while(i<=100){
                Thread.sleep(100);
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        barra.setProgress(i++);
                    }
                });
            }
        }catch(Exception e){
            //codigo de erro
        }
    }
}