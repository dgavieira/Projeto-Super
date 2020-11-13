package com.projetosuper.listaprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView list_view_produtos;
    Button btn_inserir;
    EditText txt_produto;
    ArrayAdapter produtosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view_produtos = findViewById(R.id.list_view_produtos);
        btn_inserir = findViewById(R.id.btn_inserir);
        txt_produto = findViewById(R.id.txt_produto);

        produtosAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        list_view_produtos.setAdapter(produtosAdapter);

        btn_inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String produto = txt_produto.getText().toString();

                produtosAdapter.add(produto);
                txt_produto.setText("");
            }
        });
    }

    public void clicouTela(View view){
        Toast.makeText(this, "clicou na tela", Toast.LENGTH_LONG).show();
    }
}