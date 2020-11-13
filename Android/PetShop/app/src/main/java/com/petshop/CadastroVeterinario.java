package com.petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.petshop.db.BancoDados;
import com.petshop.db.BancoHoras;
import com.petshop.model.Veterinario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CadastroVeterinario extends AppCompatActivity {

    EditText edtIdade, edtNome, edtEspecialidade, edtTelefone, edtValorConsulta, edtTempoExp, edtEndereco;
    Button btnSalvar, btnLimpar;
    ListView listviewclientes;
    Spinner spinner;
    Object item = null;


    ArrayAdapter<String> adapter, adapterSpinner;
    ArrayList<String> vets, esp;
    Set especialidades;

    BancoDados banco = new BancoDados(this);
    BancoHoras bancoHoras = new BancoHoras(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veterinario);

        vets = new ArrayList<String>();
        especialidades = new HashSet<String>();
        esp = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(CadastroVeterinario.this, android.R.layout.simple_list_item_1, vets);
        adapterSpinner = new ArrayAdapter<String>(CadastroVeterinario.this,android.R.layout.simple_list_item_1, esp);

        edtIdade = (EditText) findViewById(R.id.edtIdade);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEspecialidade = (EditText) findViewById(R.id.edtEspecialidade);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtValorConsulta = (EditText) findViewById(R.id.edtValorConsulta);
        edtTempoExp = (EditText) findViewById(R.id.edtTempExp);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnLimpar = (Button)findViewById(R.id.btnLimpar);

        listviewclientes = (ListView)findViewById(R.id.listviewclientes);
        spinner = (Spinner)findViewById(R.id.spinner);

        listaProfissionais();


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Veterinario veterinario = new Veterinario(edtNome.getText().toString(), Integer.parseInt(edtIdade.getText().toString()),
                        edtTelefone.getText().toString(), edtEspecialidade.getText().toString(), Integer.parseInt(edtTempoExp.getText().toString()),
                        edtEndereco.getText().toString(), Float.parseFloat(edtValorConsulta.getText().toString()));
                String teste = banco.addVet(veterinario);

                Toast.makeText(CadastroVeterinario.this, teste, Toast.LENGTH_SHORT).show();

                //Cliente cliente = banco.selecionarCliente(1);

                AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroVeterinario.this);
                alerta.setTitle("Usuário cadastrado:");
                alerta.setMessage(veterinario.getNome() + "\n" +
                        veterinario.getIdade() + "\n" +
                        veterinario.getTelefone() + "\n" +
                        veterinario.getEspecialidade() + "\n" +
                        veterinario.getTempo_exp() + "\n" +
                        veterinario.getValor_consulta()+ "\n" +
                        banco.getVERSAO());

                alerta.show();
                listaProfissionais();
                //int teste1 = bancoHoras.getVERSION();
                //teste1 ++;
                //bancoHoras.TABELA_DOIS = "tabela_" + teste1;
                //bancoHoras.setVERSION(teste1);

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Veterinario veterinario = banco.selecionarVet(6);

                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Usuário cadastrado:");
                alerta.setMessage(veterinario.Nome + "\n" +
                        veterinario.Idade + "\n" +
                        veterinario.Telefone + "\n" +
                        veterinario.Especialidade + "\n" +
                        veterinario.Tempo_exp + "\n" +
                        veterinario.Valor_consulta + "\n");

                alerta.show();*/
                String opa = bancoHoras.addAtendimento("12", "23h", "2020");
                Toast.makeText(CadastroVeterinario.this, opa, Toast.LENGTH_SHORT).show();

            }
        });

        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        listviewclientes.setAdapter(adapter);
                        item = parent.getItemAtPosition(position);
                        vets.clear();
                        adapter.notifyDataSetChanged();
                        if(item != null)
                            Toast.makeText(CadastroVeterinario.this, item.toString(), Toast.LENGTH_LONG).show();

                        List<Veterinario> profissionais = banco.listaVet();


                        for (Veterinario v : profissionais){
                            if(item.toString().equals(v.getEspecialidade())){
                                vets.add(v.getNome());
                                adapter.notifyDataSetChanged();}
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                return false;
            }
        });




        listviewclientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CadastroVeterinario.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }
        });







    }

    public void listaProfissionais(){
        List<Veterinario> profissionais = banco.listaVet();

        esp.clear();
        listviewclientes.setAdapter(adapter);
        spinner.setAdapter(adapterSpinner);

        for (Veterinario v : profissionais){
            vets.add(v.getNome());
            especialidades.add(v.getEspecialidade());
            adapter.notifyDataSetChanged();
            adapterSpinner.notifyDataSetChanged();
        }
        esp.addAll(especialidades);
        adapterSpinner.notifyDataSetChanged();


    }



}