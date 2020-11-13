package com.projetosuper.dgavieiramusicstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_sign_in, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sign_in = findViewById(R.id.act_main_btn_sign_in);
        btn_register = findViewById(R.id.act_main_btn_register);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_sign_in = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(go_to_sign_in);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_register = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(go_to_register);
            }
        });
    }
}