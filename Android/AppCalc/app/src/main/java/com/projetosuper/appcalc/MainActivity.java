package com.projetosuper.appcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    float numeroA = 0;
    String operacao = "";
    TextView campoTexto;

    public void clicaBotao(View view){
        switch (view.getId()){
            case R.id.botaoLimpar:
                campoTexto.setText("0");
                numeroA = 0;
                operacao = "";
                break;
            case R.id.botaoSoma:
                calculaNumeros("+");
                break;
            case R.id.botaoSubtrai:
                calculaNumeros("-");
                break;
            case R.id.botaoDivide:
                calculaNumeros("/");
                break;
            case R.id.botaoMultiplica:
                calculaNumeros("x");
                break;
            case R.id.botaoIgual:
                mostraResultado();
                break;
            default:
                String numeroB;
                numeroB = ((Button) view).getText().toString();
                getKeyboard(numeroB);
                break;
        }
    }

    public void calculaNumeros(String tipoOp){
        numeroA = Float.parseFloat(campoTexto.getText().toString());
        operacao = tipoOp;
        campoTexto.setText("0");
    }

    public void getKeyboard(String str){
        String valCorrente = campoTexto.getText().toString();
        valCorrente += str;
        campoTexto.setText(valCorrente);
    }

    public void mostraResultado(){
        float numeroB = Float.parseFloat(campoTexto.getText().toString());
        float resultado = 0;

        if(operacao.equals("+"))
            resultado = numeroB + numeroA;
        if(operacao.equals("-"))
            resultado = numeroA - numeroB;
        if(operacao.equals("x"))
            resultado = numeroB * numeroA;
        if(operacao.equals("/"))
            resultado = numeroA / numeroB;
        campoTexto.setText(String.valueOf(resultado));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoTexto = findViewById(R.id.campoResultado);
        campoTexto.setText("0");
    }
}