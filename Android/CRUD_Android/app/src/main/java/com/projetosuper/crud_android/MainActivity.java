package com.projetosuper.crud_android;

/* Projeto baseado com informacoes de:
https://www.luiztools.com.br/post/tutorial-crud-em-android-com-sqlite-e-recyclerview/
https://www.luiztools.com.br/post/tutorial-crud-em-android-com-sqlite-e-recyclerview-2/
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    RecyclerView listaContatos;
    ContatoAdapter adapter;
    FloatingActionButton flt_btn_adicionar;

    private void configurarRecycler(){
        listaContatos = findViewById(R.id.recycler_contados);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listaContatos.setLayoutManager(layoutManager);

        ContatoDAO dao = new ContatoDAO(this);
        adapter = new ContatoAdapter(dao.retornarTodos());
        listaContatos.setAdapter(adapter);
        listaContatos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        flt_btn_adicionar = findViewById(R.id.flt_btn_adicionar);

        flt_btn_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go_to_adicionar = new Intent(MainActivity.this, AdicionaContatoActivity.class);
                startActivity(go_to_adicionar);
                finish();
            }
        });
        configurarRecycler();
    }

    private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for (int i = 0; i<spinner.getCount();i++){
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

}