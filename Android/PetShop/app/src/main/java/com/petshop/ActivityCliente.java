package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joaquimley.faboptions.FabOptions;
import com.petshop.adapter.AnimalViewAdapter;
import com.petshop.adapter.ItensCompradosAdapter;
import com.petshop.adapter.ProdutoAdapter;
import com.petshop.adapter.ProdutosCadastradosAdapter;
import com.petshop.db.AnimalDAO;
import com.petshop.db.Database;
import com.petshop.db.ProdutoDAO;
import com.petshop.db.ShopDAO;
import com.petshop.db.UsuarioDAO;
import com.petshop.listener.RecyclerItemClickListener;
import com.petshop.model.Animais;
import com.petshop.model.DbBitmapUtility;
import com.petshop.model.Produto;
import com.petshop.model.Usuario;
import com.petshop.resource.CircleImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivityCliente extends AppCompatActivity implements  View.OnClickListener {

    Toolbar mToolbar;
    private TextView textViewName, textViewEmail;
    private Button buttonListaPets, buttonListaProdutosComprados;
    FabOptions mFabOptions;
    private Intent intent;
    private ShopDAO shopDAO;
    private ProdutoDAO productDAO;
    private ListView listItemsComprado;
    private SimpleCursorAdapter cursorAdapter;
    private CircleImageView imageViewProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        this.shopDAO = new ShopDAO(this);

        intent = getIntent();

        final Usuario usuario = (Usuario) getIntent().getSerializableExtra("Usuario");

        this.setTitle("Seja bem vindo, " + usuario.getNome() + "!");

        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("PetShop");
        setSupportActionBar(mToolbar);

        textViewName = (TextView) findViewById(R.id.nome);
        textViewName.setText(usuario.getNome());

        textViewEmail = (TextView) findViewById(R.id.email);
        textViewEmail.setText(usuario.getEmail());

        imageViewProfile = (CircleImageView) findViewById(R.id.imageProfilePic);

        buttonListaPets = (Button) findViewById(R.id.btnListaAnimais);

        buttonListaPets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPet = new Intent(ActivityCliente.this, PetsCadastrados.class);
                startActivity(intentPet);
            }
        });

        buttonListaProdutosComprados = (Button) findViewById(R.id.btnListaProdutosComprados);

        buttonListaProdutosComprados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPet = new Intent(ActivityCliente.this, ProdutosComprados.class);
                intentPet.putExtra("Usuario",usuario);
                startActivity(intentPet);
            }
        });


        mFabOptions = findViewById(R.id.fab_options);
        mFabOptions.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("Usuario");
        switch (view.getId()) {
            case R.id.comprar_produto:
                Intent intent = new Intent(ActivityCliente.this, ActivityShop.class);
                intent.putExtra("Usuario", usuario);
                startActivity(intent);
                break;

            case R.id.contratar_veterinario:
                break;

            case R.id.cadastrar_pet:
                startActivity(new Intent(ActivityCliente.this, CadastroAnimal.class));
                break;

            case R.id.editar_dados:
                break;

            default:
        }

    }

}