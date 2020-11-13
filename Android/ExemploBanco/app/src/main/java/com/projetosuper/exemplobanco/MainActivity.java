package com.projetosuper.exemplobanco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button meuBotao;
    private ListView minhaLista;
    private EditText meuTexto;

    private SQLiteDatabase bancoDados;

    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meuTexto = findViewById(R.id.meuTexto);
        meuBotao = findViewById(R.id.meuBotao);
        minhaLista = findViewById(R.id.minhaLista);

        /*
        meuBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarNovaTarefa(String tarefa);
                carregarTarefas();
            }
        });
         */

        minhaLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //removerTarefa(ids.get(position));
                alertApagaTarefa(position);
                return false;
            }
        });
        carregarTarefas();
    }
    private void carregarTarefas(){
        try {
            bancoDados = openOrCreateDatabase("meuBanco",MODE_PRIVATE,null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS minhastarefas(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefa VARCHAR)");

            String novaTarefa = meuTexto.getText().toString();
            //bancoDados.execSQL("INSERT INTO minhastarefas(tarefa) VALUES (' "+ novaTarefa +" ')");
            gravarNovaTarefa(novaTarefa);
            //meuTexto.setText("");

            Cursor cursor = bancoDados.rawQuery("SELECT * FROM minhastarefas ORDER BY id DESC", null);

            int indiceColunaID = cursor.getColumnIndex("id");
            int indiceColunaTarefa = cursor.getColumnIndex("tarefa");

            itens = new ArrayList<>();
            ids = new ArrayList<>();

            itensAdaptador = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_2,android.R.id.text1,itens);
            minhaLista.setAdapter(itensAdaptador);

            cursor.moveToFirst();

            while (cursor != null){
                Log.i("Logx","ID: "+ cursor.getString(indiceColunaID) + "tarefa: "+cursor.getString(indiceColunaTarefa));
                itens.add(cursor.getString(indiceColunaTarefa));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaID)));

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void gravarNovaTarefa(String tarefa){
        try {
            if(tarefa.equals("")){
                Toast.makeText(this, "Insira uma nova tarefa", Toast.LENGTH_SHORT).show();
            }else{
                bancoDados.execSQL("INSERT INTO minhastarefas(tarefa) VALUES ('"+ tarefa +"')");
                Toast.makeText(this, "Tarefa salva!", Toast.LENGTH_SHORT).show();
                meuTexto.setText("");
                carregarTarefas();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void removerTarefa(Integer id){
        try{
            bancoDados.execSQL("DELETE FROM minhastarefas WHERE id="+id);
            carregarTarefas();
            Toast.makeText(MainActivity.this, "Tarefa Removida", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void alertApagaTarefa(Integer idSelecionado){
        String tarefaSelecionada = itens.get(idSelecionado);
        final Integer numeroid = idSelecionado;

        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Alerta")
                .setMessage("Deseja apagar a tarefa:"+tarefaSelecionada + "?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removerTarefa(ids.get(numeroid));
                    }
                }).setNegativeButton("Não",null).show();
    }
}