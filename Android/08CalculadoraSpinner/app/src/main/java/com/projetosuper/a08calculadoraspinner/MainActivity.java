package com.projetosuper.a08calculadoraspinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtnum1, edtnum2;
    Button btncalcular;
    Spinner spnoperacao;
    private static final String[] operacoes = {"Soma", "Subtração", "Multiplicação","Divisão"};
    ArrayAdapter<String> adapterOperacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtnum1 = findViewById(R.id.edtnum1);
        edtnum2 = findViewById(R.id.edtnum2);
        btncalcular = findViewById(R.id.btncalcular);

        //Setting up adapter
        adapterOperacoes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,operacoes);
        spnoperacao = findViewById(R.id.spnoperacao);
        spnoperacao.setAdapter(adapterOperacoes);

        //setting up button event click
        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1=0, num2=0,resultado=0;
                    String resultado_string;
                    String op = new String();

                    String num1_str = edtnum1.getText().toString();
                    String num2_str = edtnum2.getText().toString();

                    num1 = Double.parseDouble(num1_str);
                    num2 = Double.parseDouble(num2_str);


                    switch (spnoperacao.getSelectedItemPosition()){
                        case 0:
                            resultado = num1 + num2;
                            op = "+";
                            break;
                        case 1:
                            resultado = num1 - num2;
                            op = "-";
                            break;
                        case 2:
                            resultado = num1 * num2;
                            op = "x";
                            break;
                        case 3:
                            resultado = num1/num2;
                            op = "/";
                            break;
                    }
                    resultado_string = String.valueOf(resultado);
                    String mensagem_resultado = num1_str + "\t" + op + "\t" + num2_str + " = " + resultado_string;

                    if(num2 == 0.0 && spnoperacao.getSelectedItemPosition() == 3){
                        Toast.makeText(MainActivity.this, "Division Per Zero", Toast.LENGTH_SHORT).show();
                        esvaziaCampo();
                    }
                    else{
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                        dialogo.setTitle("Resultado da Operação");
                        dialogo.setMessage(mensagem_resultado);
                        dialogo.setNeutralButton("Ok",null);
                        dialogo.show();
                        esvaziaCampo();
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    esvaziaCampo();
                }
            }
        });
        }
    public void esvaziaCampo(){
        edtnum1.setText("");
        edtnum2.setText("");
    }
}