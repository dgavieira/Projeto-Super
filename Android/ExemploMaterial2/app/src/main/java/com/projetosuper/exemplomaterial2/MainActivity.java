package com.projetosuper.exemplomaterial2;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Episodio> episodios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_lista);
        toolbar.setLogo(R.drawable.felp_icon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);

        episodios = new ArrayList<Episodio>();
        episodios.add(new Episodio("Android","Plaraforma mobile", "janeiro de 2020", "1223"));
        episodios.add(new Episodio("Android","Plaraforma mobile", "janeiro de 2020", "1223"));
        episodios.add(new Episodio("Android","Plaraforma mobile", "janeiro de 2020", "1223"));
        episodios.add(new Episodio("Android","Plaraforma mobile", "janeiro de 2020", "1223"));
        episodios.add(new Episodio("Android","Plaraforma mobile", "janeiro de 2020", "1223"));

        MeuAdaptador meuAdaptador = new MeuAdaptador(episodios, getApplicationContext(), new MeuAdaptador.OnItemClickListener() {
            @Override
            public void onItemClick(Episodio episodio) {
                Toast.makeText(MainActivity.this, ""+episodio.getTitulo(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(meuAdaptador);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "testa", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean OnCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.meu_menu, menu);
        return true;
    }
    @Override
    public boolean OnOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_contato:
                Toast.makeText(this, "Clicou em contato", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_fav:
                Toast.makeText(this, "Clicou em favoritos!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_home:
                Toast.makeText(this, "Clicou em contato", Toast.LENGTH_SHORT).show();
                break;
            case R.id.:
                Toast.makeText(this, "Clicou em contato", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}