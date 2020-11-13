package com.petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.petshop.db.BancoHoras;
import com.petshop.model.CalendarioConsulta;

import java.util.ArrayList;
import java.util.List;

public class VeterinarioActivity extends AppCompatActivity {
    ListView segundalista;
    ArrayAdapter<String> adapter;
    ArrayList<String> agendamentos;
    BancoHoras banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veterinario_activity);

        segundalista = (ListView) findViewById(R.id.segunda);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, agendamentos);
        listaAgendamentos();
    }

    public void listaAgendamentos(){
        List<CalendarioConsulta> profissionais = banco.listaConsultas();

        segundalista.setAdapter(adapter);

        for (CalendarioConsulta v : profissionais){
            agendamentos.add("Dia: " + v.getDia() + " \nHora: " + v.getHora());
            adapter.notifyDataSetChanged();

        }



    }
}