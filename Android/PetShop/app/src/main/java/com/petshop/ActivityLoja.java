package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.joaquimley.faboptions.FabOptions;
import com.petshop.adapter.ProdutosCadastradosAdapter;
import com.petshop.db.Database;
import com.petshop.db.ProdutoDAO;
import com.petshop.listener.RecyclerItemClickListener;
import com.petshop.listener.RecyclerItemLongClickListener;
import com.petshop.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ActivityLoja extends AppCompatActivity implements View.OnClickListener, RecyclerItemClickListener, RecyclerItemLongClickListener {
    Toolbar mToolbar;
    FabOptions mFabOptions;
    private RecyclerView lvContact;
    private ProdutosCadastradosAdapter produtosCadastradosAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Produto produto;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        lvContact = (RecyclerView) findViewById(R.id.lvContact);
        linearLayoutManager = new LinearLayoutManager(this);
        produtosCadastradosAdapter = new ProdutosCadastradosAdapter(this);
        produtosCadastradosAdapter.setOnItemClickListener(this);
        produtosCadastradosAdapter.setOnItemLongClickListener(this);
        lvContact.setLayoutManager(linearLayoutManager);
        lvContact.setHasFixedSize(true);
        lvContact.setAdapter(produtosCadastradosAdapter);

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("PetShop");
        setSupportActionBar(mToolbar);

        mFabOptions = findViewById(R.id.fab_options);
        mFabOptions.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_produtos:
                startActivity(new Intent( ActivityLoja.this, CadastrarProdutoActivity.class));
                break;

            case R.id.button_veterinarios:
                startActivity(new Intent(ActivityLoja.this, CadastroVeterinario.class));
                break;

            default:
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    void loadData(){
        database = new Database(this);

        List<Produto> contactList = new ArrayList<>();

        Cursor cursor = database.retrieve();

        if (cursor.moveToFirst()) {
            do {
                produto = new Produto();
                produto.setId(cursor.getInt(0));
                produto.setNome(cursor.getString(1));
                produto.setDescricao(cursor.getString(2));
                produto.setPreco(cursor.getDouble(3));
                produto.setImg(cursor.getBlob(4));
                contactList.add(produto);

            }while (cursor.moveToNext());
        }

        produtosCadastradosAdapter.clear();
        produtosCadastradosAdapter.addAll(contactList);

    }

    @Override
    public void onItemClick(int position, View view) {

    }

    @Override
    public void onItemLongClick(int position, View view) {

    }
}