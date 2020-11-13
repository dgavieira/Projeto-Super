package com.petshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.petshop.adapter.AnimalViewAdapter;
import com.petshop.db.AnimalDAO;
import com.petshop.db.Database;
import com.petshop.listener.RecyclerItemClickListener;
import com.petshop.listener.RecyclerItemLongClickListener;
import com.petshop.model.Animais;

import java.util.ArrayList;
import java.util.List;

public class PetsCadastrados extends AppCompatActivity implements RecyclerItemClickListener, RecyclerItemLongClickListener {

    private RecyclerView lvContact;
    private AnimalViewAdapter animalViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Animais animais;
    private AnimalDAO animalDAO;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets_cadastrados);

        lvContact = (RecyclerView) findViewById(R.id.recyclerViewAnimal);
        linearLayoutManager = new LinearLayoutManager(this);
        animalViewAdapter = new AnimalViewAdapter(this);
        animalViewAdapter.setOnItemClickListener(this);
        animalViewAdapter.setOnItemLongClickListener(this);
        lvContact.setLayoutManager(linearLayoutManager);
        lvContact.setHasFixedSize(true);
        lvContact.setAdapter(animalViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }

    void loadData() {
        database = new Database(this);

        List<Animais> animaisList = new ArrayList<>();

        Cursor cursor = database.retrieveAnimal();

        if (cursor.moveToFirst()) {
            do {
                animais = new Animais();
                animais.setId(cursor.getInt(0));
                animais.setNome(cursor.getString(1));
                animais.setCategoria(cursor.getString(2));
                animais.setRaca(cursor.getString(3));
                animais.setFoto(cursor.getBlob(4));

                animaisList.add(animais);

            } while (cursor.moveToNext());
        }

        animalViewAdapter.clear();
        animalViewAdapter.addAll(animaisList);

    }

    @Override
    public void onItemClick(int position, View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_custom_edit:
                        Intent intent = new Intent(PetsCadastrados.this, AtualizaAnimal.class);
                        intent.putExtra("nome", animalViewAdapter.getItem(position).getNome());
                        intent.putExtra("categoria", animalViewAdapter.getItem(position).getCategoria());
                        intent.putExtra("raca", animalViewAdapter.getItem(position).getRaca());
                        intent.putExtra("foto", animalViewAdapter.getItem(position).getFoto());
                        startActivity(intent);

                        return true;
                    case R.id.menu_custom_delete:
                        final AlertDialog.Builder dialog = new AlertDialog.Builder(PetsCadastrados.this);
                        dialog.setTitle("Aviso");
                        dialog.setMessage("Deseja realmente deletar o contato da lista?");
                        dialog.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.deleteAnimal(animalViewAdapter.getItem(position).getId());
                                Toast.makeText(PetsCadastrados.this, "Contato deletado com sucesso", Toast.LENGTH_SHORT).show();
                                animalViewAdapter.notifyDataSetChanged();
                                loadData();
                            }
                        });
                        dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(PetsCadastrados.this, "Ação Cancelada", Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.custom_menu);
        popupMenu.show();

    }

    @Override
    public void onItemLongClick(int position, View view) {

    }
}