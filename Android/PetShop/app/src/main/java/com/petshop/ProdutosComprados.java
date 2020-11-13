package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.petshop.adapter.ItensCompradosAdapter;
import com.petshop.db.ProdutoDAO;
import com.petshop.db.ShopDAO;
import com.petshop.model.Usuario;

import java.util.ArrayList;

public class ProdutosComprados extends AppCompatActivity {

    private Intent intent;
    private static Usuario usuario;
    private ShopDAO shopDAO;
    private ListView listItemsComprado;
    private SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_comprados);

        this.shopDAO = new ShopDAO(this);
        intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra("Usuario");

        listItemsComprado = findViewById(R.id.recyclerViewProdutoComprados);
        ItensCompradosAdapter adapterItemComprados = new ItensCompradosAdapter(ProdutosComprados.this, R.layout.itens_comprados_list);


        ArrayList itensComprados = shopDAO.getProdutos(usuario.getId());


        for(int i = 0; i < itensComprados.size(); i++){
            adapterItemComprados.add(itensComprados.get(i)); //add into adapter an object returned from the SQL
        }

        listItemsComprado.setAdapter(adapterItemComprados);

    }
}