package com.projetosuper.taberna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CheckBox cbox1, cbox2, cbox3, cbox4;
    Button btnTotal;
    TextView txtViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbox1 = findViewById(R.id.chkLeite);
        cbox2 = findViewById(R.id.chkCarne);
        cbox3 = findViewById(R.id.chkFeijao);
        cbox4 = findViewById(R.id.chkArroz);

        btnTotal = findViewById(R.id.btnTotal);

        txtViewTotal = findViewById(R.id.txtViewTotal);


        btnTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double total = 0;

                if(cbox1.isChecked())
                    total += 5.0;
                if(cbox2.isChecked())
                    total += 23.0;
                if(cbox3.isChecked())
                    total += 10.0;
                if(cbox4.isChecked())
                    total += 5.0;
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Aviso");
                dialogo.setMessage("Valor total da compra:"+String.valueOf(total));
                dialogo.setNeutralButton("Ok",null);
                dialogo.show();

                if (total == 0){
                    btnTotal.setEnabled(false);
                }else{
                    btnTotal.setEnabled(true);
                }
            }
        });

        cbox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total = 0;

                if(cbox1.isChecked())
                    total += 5.0;
                if(cbox2.isChecked())
                    total += 23.0;
                if(cbox3.isChecked())
                    total += 10.0;
                if(cbox4.isChecked())
                    total += 5.0;
                txtViewTotal.setText("Total das Compras R$"+ total);

                if (total == 0){
                    btnTotal.setEnabled(false);
                }else{
                    btnTotal.setEnabled(true);
                }
            }
        });
        cbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbox2.isChecked()){
                    double total = 0;

                    if(cbox1.isChecked())
                        total += 5.0;
                    if(cbox2.isChecked())
                        total += 23.0;
                    if(cbox3.isChecked())
                        total += 10.0;
                    if(cbox4.isChecked())
                        total += 5.0;
                    txtViewTotal.setText("Total das Compras R$"+ total);

                    if (total == 0){
                        btnTotal.setEnabled(false);
                    }else{
                        btnTotal.setEnabled(true);
                    }
                }
            }
        });
        cbox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbox3.isChecked()){
                    double total = 0;

                    if(cbox1.isChecked())
                        total += 5.0;
                    if(cbox2.isChecked())
                        total += 23.0;
                    if(cbox3.isChecked())
                        total += 10.0;
                    if(cbox4.isChecked())
                        total += 5.0;
                    txtViewTotal.setText("Total das Compras R$"+ total);

                    if (total == 0){
                        btnTotal.setEnabled(false);
                    }else{
                        btnTotal.setEnabled(true);
                    }
                }
            }
        });
        cbox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbox3.isChecked()){
                    double total = 0;

                    if(cbox1.isChecked())
                        total += 5.0;
                    if(cbox2.isChecked())
                        total += 23.0;
                    if(cbox3.isChecked())
                        total += 10.0;
                    if(cbox4.isChecked())
                        total += 5.0;
                    txtViewTotal.setText("Total das Compras R$"+ total);

                    if (total == 0){
                        btnTotal.setEnabled(false);
                    }else{
                        btnTotal.setEnabled(true);
                    }
                }
            }
        });

    }
}