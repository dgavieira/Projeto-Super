package com.projetosuper.a07listaconvidados;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Declare elements from activity_main.xml
    ListView lstconvidados;
    EditText edtnomes;
    Button btnconvidar;
    ArrayAdapter<String> convidadosAdapter;
    ArrayList<String> listaconvidados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaconvidados = new ArrayList<>();
        //Instantiate elements from activity_main.xml using the R class
        lstconvidados = findViewById(R.id.lstconvidados);
        edtnomes = findViewById(R.id.edtnome);
        btnconvidar = findViewById(R.id.btnconvidar);

        //Setting up Array Adapter
        convidadosAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaconvidados);
        lstconvidados.setAdapter(convidadosAdapter);

        //Capture the click event from Button
        btnconvidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //variable receives string with convidado name
                String convidado = edtnomes.getText().toString();
                //checks if convidado field is empty
                if(convidado.isEmpty()){
                    //convidado field IS empty
                    Toast.makeText(MainActivity.this, "Campo CONVIDADO vazio", Toast.LENGTH_SHORT).show();
                    edtnomes.setText("");
                }else{
                    //convidado field is NOT empty
                    //check if there is any duplicates already on Arraylist
                    if(listaconvidados.contains(convidado)){
                        Toast.makeText(MainActivity.this, "Convidado já inserido", Toast.LENGTH_LONG).show();
                        edtnomes.setText("");
                    }else{
                        convidadosAdapter.add(convidado);
                        edtnomes.setText("");
                    }
                }
            }
        });
    }
}