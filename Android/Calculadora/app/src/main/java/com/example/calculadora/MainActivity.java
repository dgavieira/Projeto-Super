package com.example.calculadora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtNumero1, edtNumero2;
    Button btnSomar, btnSubtrair, btnMultiplicar, btnDividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumero1 = (EditText) findViewById(R.id.edtNumero1);
        edtNumero2 = (EditText) findViewById(R.id.edtNumero2);
        btnSomar = (Button) findViewById(R.id.btnSomar);
        btnSubtrair = (Button) findViewById(R.id.btnSubtrair);
        btnMultiplicar = (Button) findViewById(R.id.btnMultiplicar);
        btnDividir = (Button) findViewById(R.id.btnDividir);

        btnSomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1, num2, res;
                    num1 = Double.parseDouble(edtNumero1.getText().toString());
                    num2 = Double.parseDouble(edtNumero2.getText().toString());
                    res = num1 + num2;

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Soma = " + res);
                    dialogo.setNeutralButton("Ok", null);
                    dialogo.show();
                } catch (NumberFormatException e){
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Campos Vazios. Insira números");
                    dialogo.setNeutralButton("Ok", null);
                    dialogo.show();
                }
            }
        });

        btnSubtrair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    double num1, num2, res;

                    num1 = Double.parseDouble(edtNumero1.getText().toString());
                    num2 = Double.parseDouble(edtNumero2.getText().toString());

                    res = num1 - num2;

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Subtração = " + res);
                    dialogo.setNeutralButton("Ok", null);
                    dialogo.show();
                } catch (NumberFormatException e){
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Campos Vazios. Insira números");
                    dialogo.setNeutralButton("Ok", null);
                    dialogo.show();
                }
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1, num2, res;

                    num1 = Double.parseDouble(edtNumero1.getText().toString());
                    num2 = Double.parseDouble(edtNumero2.getText().toString());

                    res = num1 * num2;

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Multiplicação = " + res);
                    dialogo.setNeutralButton("Ok", null);
                    dialogo.show();
                } catch (NumberFormatException e){
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Campos Vazios. Insira números");
                    dialogo.setNeutralButton("Ok", null);
                    dialogo.show();
                }
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double num1, num2, res;

                    num1 = Double.parseDouble(edtNumero1.getText().toString());
                    num2 = Double.parseDouble(edtNumero2.getText().toString());

                    res = num1 / num2;

                    if (num2 == 0){
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                        dialogo.setTitle("Aviso");
                        dialogo.setMessage("Erro de Divisão por Zero");
                        dialogo.setNeutralButton("Ok", null);
                        dialogo.show();
                    } else {
                        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                        dialogo.setTitle("Aviso");
                        dialogo.setMessage("Divisão = " + res);
                        dialogo.setNeutralButton("Ok", null);
                        dialogo.show();
                    }

                } catch (NumberFormatException e){
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Aviso");
                    dialogo.setMessage("Campos Vazios. Insira números");
                    dialogo.setNeutralButton("Ok", null);
                    dialogo.show();
                }
            }
        });
    }
}