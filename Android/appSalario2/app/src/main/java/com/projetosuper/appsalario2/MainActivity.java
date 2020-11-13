package com.projetosuper.appsalario2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private static final String[] percentual = {"De 40%", "De 45%", "De 50%"};
    ArrayAdapter<String> aPercentual;
    Spinner spnsal;
    Button btncalc;
    EditText edtsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtsal = findViewById(R.id.edtsal);
        btncalc = findViewById(R.id.btncalcular);
        aPercentual = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,percentual);
        spnsal = findViewById(R.id.spnopcoes);
        spnsal.setAdapter(aPercentual);

        btncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double salario = 0, novo_sal = 0;

                salario = Double.parseDouble(edtsal.getText().toString());

                switch (spnsal.getSelectedItemPosition()){
                    case 0: novo_sal = salario *1.4;break;
                    case 1: novo_sal = salario *1.45;break;
                    case 2: novo_sal = salario *1.5;break;
                }

                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Novo Salário");
                dialogo.setMessage("Seu novo salário é: R$ "+String.valueOf(novo_sal));
                dialogo.setNeutralButton("Ok",null);
                dialogo.show();
            }
        });
    }
}