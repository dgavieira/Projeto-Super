package com.projetosuper.aposentadoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spn_sexo;
    EditText txt_idade;
    Button btn_calcular;
    TextView txt_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> lista = new ArrayList<String>();
        lista.add("Masculino");
        lista.add("Feminino");

        spn_sexo = findViewById(R.id.spn_sexo);
        txt_idade = findViewById(R.id.txt_idade);
        btn_calcular = findViewById(R.id.btn_calcular);
        txt_resultado = findViewById(R.id.txt_resultado);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_sexo.setAdapter(dataAdapter);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sexo = (String)spn_sexo.getSelectedItem();
                int idade = Integer.parseInt(txt_idade.getText().toString());
                double resultado;

                if(sexo == "Masculino"){
                    resultado = 65 - idade;
                }else{
                    resultado = 60 - idade;
                }
                txt_resultado.setText("Faltam "+resultado+" anos para você se aposentar");
            }
        });
    }
}