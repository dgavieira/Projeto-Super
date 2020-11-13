package com.projetosuper.appsalario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText edtSal;
    Button btnCalc;
    RadioGroup opcoesIR, opcoesINSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc = findViewById(R.id.btncalcular);
        edtSal = findViewById(R.id.edtSalario);
        opcoesIR = findViewById(R.id.rgIR);
        opcoesINSS = findViewById(R.id.rgINSS);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    double salario, novo_sal = 0, desc_inss, desc_ir;
                    int op, ap;

                    salario = Double.parseDouble(edtSal.getText().toString());
                    op = opcoesIR.getCheckedRadioButtonId();
                    ap = opcoesINSS.getCheckedRadioButtonId();

                    //calcula desconto IR
                    if(op == R.id.rbIR08)
                        desc_ir = salario*0.08;
                    else if(op == R.id.rbIR09)
                        desc_ir = salario*0.09;
                    else
                        desc_ir = salario*0.11;

                    //calcula desconto INSS
                    if(ap == R.id.rbINSS75)
                        desc_inss = salario*0.075;
                    else if(ap == R.id.rbINSS9)
                            desc_inss = salario*0.09;
                        else if(ap == R.id.rbINSS12)
                                desc_inss = salario*0.12;
                                else
                                    desc_inss = salario*0.14;

                    //calculo do salario liquido
                    novo_sal = salario - desc_inss - desc_ir;

                    AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("Novo salário");
                    dialogo.setMessage("Seu novo salário é: R$ "+String.valueOf(novo_sal));
                    dialogo.setNeutralButton("Ok",null);
                    dialogo.show();

                    //bloquear botao
                    if(edtSal.getText().toString() == ""){
                        btnCalc.setEnabled(false);
                    }
                    else{
                        btnCalc.setEnabled(true);
                    }
                }
                catch(NumberFormatException e){}



            }
        });
    }
}