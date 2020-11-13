package com.projetosuper.lista_contatos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public ListView lista;
    public ListView lista_emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adapter do listview de NOMES
        ArrayAdapter<String> aContatos = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, contatos);
        lista = findViewById(R.id.lstcontatos);
        lista.setAdapter(aContatos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView a, View v, int b, long c){
                String Value = lista.getItemAtPosition(b).toString();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Contato Selecionado");
                dialogo.setMessage(Value);
                dialogo.setNeutralButton("Ok",null);
                dialogo.show();
                }
        });

        //Adapter do ListView de EMAILS
        ArrayAdapter<String> aEmails = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, emails);
        lista_emails = findViewById(R.id.lstemails);
        lista_emails.setAdapter(aEmails);

        lista_emails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value_email = lista_emails.getItemAtPosition(position).toString();
                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("E-mail Selecionado");
                dialogo.setMessage(Value_email);
                dialogo.setNeutralButton("Ok",null);
                dialogo.show();
            }
        });


    }
    static final String[] contatos = new String[]{
      "ALEX ANDRÉ RUIZ DA ROCHA","CRISTIAN LOPES GUERRA","DIEGO GIOVANNI DE ALCÂNTARA VIEIRA",
            "ELTON DIONE NASCIMENTO DE ALENCAR","GUILHERME SOUZA DA SILVA","JORGE DARLIM RODRIGUES DA SILVA",
            "JOSIAS BEN FERREIRA CAVALCANTE","KHALEB LISBOA SILVA E SILVA","LUCAS LIMA DE OLIVEIRA",
            "NADER MORAES HAUACHE","RAFAEL OLIVEIRA MOREIRA","THYAGO ALEX CARDOSO SAMPAIO",
            "LEILA CAROLINE ARAGÃO SILVA","VICTORIA CAROLINA OLIVEIRA","THAYLA MOURA DA SILVA",
            "CICERO FERREIRA FERNANDES COSTA FILHO","MARLY GUIMARÃES FERNANDES COSTA",
            "EMMERSON SATNA RITA DA SILVA","ANNE DE SOUZA OLIVEIRA","GUSTAVO DE AQUINO E AQUINO",
            "MIKAELA KALINE MACIEL SERRÃO","JOÃO VICTOR CAMPOS DE NEGREIRO"
    };
    static final String[] emails = new String[]{
            "alexrocha@super.ufam.edu.br","cristianguerra@super.ufam.edu.br","diegovieira@super.ufam.edu.br",
    "eltonalencar@super.ufam.edu.br","guilhermesouza@super.ufam.edu.br","jorgesilva@super.ufam.edu.br",
    "josiascavalcante@super.ufam.edu.br","khaleblisboa@super.ufam.edu.br","lucasoliveira@super.ufam.edu.br",
    "naderhauache@super.ufam.edu.br","rafaelmoreira@super.ufam.edu.br","thyagosampaio@super.ufam.edu.br",
            "leilacarolineas@gmail.com","viviicaroline@hotmail.com","thaylamourasilva@gmail.com",
            "cicero@super.ufam.edu.br","marly@super.ufam.edu.br","emmerson@super.ufa.edu.br",
    "anneoliveira@super.ufam.edu.br","gustavoaquino@super.ufam.edu.br","mikaelaserrao@super.ufam.edu.br",
    "joaonegreiros@super.ufam.edu.br"
    };
}