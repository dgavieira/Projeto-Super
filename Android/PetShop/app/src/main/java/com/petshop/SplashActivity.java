package com.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import com.petshop.resource.TextWriter;

public class SplashActivity extends AppCompatActivity {
    TextWriter textWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textWriter = findViewById(R.id.tw);

        textWriter.setColor(Color.parseColor("#252525"))
                .setWidth(8)
                .setDelay(40)
                .setSizeFactor(25f)
                .setLetterSpacing(30f)
                .setConfig(TextWriter.Configuration.INTERMEDIATE)
                .setText("PETSHOP")
                .setListener(new TextWriter.Listener() {
                    @Override
                    public void WritingFinished() {
                        //Toast.makeText(MainActivity.this, "boom", Toast.LENGTH_SHORT).show();
                    }
                })
                .startAnimation();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMainActivity();
            }
        }, 4000);
    }

    private void mostrarMainActivity() {
        Intent intent = new Intent(
                SplashActivity.this, PetShopActivityLogin.class
        );
        startActivity(intent);
        finish();
    }

}