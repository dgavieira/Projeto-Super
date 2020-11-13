package com.projetosuper.listacombanco2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.projetosuper.listacombanco2.db.TaskContract;
import com.projetosuper.listacombanco2.db.TaskDBHelper;

public class MainActivity extends AppCompatActivity {
    private TaskDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();
    }
    private void updateUI(){
        helper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE, new String[]{
                TaskContract.Columns._ID, TaskContract.Columns.TAREFA
        }, null, null, null,null, null);

        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,R.layout.celula, cursor, new String[]{TaskContract.Columns.TAREFA}, new int[]{R.id.textoCelula});

        ListView listaTarefas = findViewById(R.id.listaTarefas);
        listaTarefas.setAdapter(listAdapter);
    }

    public void adicionarItemI(View view){
        //AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicione uma tarefa");
        builder.setMessage("O que você precisa fazer?");
        final EditText inputField = new EditText(this);
        builder.setView(inputField);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(inputField.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Campo Vazio", Toast.LENGTH_SHORT).show();
                }else{
                    String tarefa = inputField.getText().toString();
                    Log.d("MainActivity",tarefa);

                    helper = new TaskDBHelper(MainActivity.this);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.clear();
                    values.put(TaskContract.Columns.TAREFA, tarefa);

                    db.insertWithOnConflict(TaskContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                    updateUI();
                }
            }
        });

        builder.setNegativeButton("Cancelar",null);
        builder.create().show();
    }
    public void apagarItem(View view){
        //codigo apagar item da lista
        View v = (View)view.getParent();
        TextView taskTextView = (TextView)v.findViewById(R.id.textoCelula);
        final String tarefa = taskTextView.getText().toString();

        AlertDialog.Builder certeza = new AlertDialog.Builder(this);
        certeza.setTitle("Excluir item");
        certeza.setMessage("Você tem certeza que quer excluir: " + tarefa + " ?");

        certeza.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sql = String.format("DELETE FROM %s WHERE %s = '%s'", TaskContract.TABLE, TaskContract.Columns.TAREFA, tarefa);

                helper = new TaskDBHelper(MainActivity.this);
                SQLiteDatabase sqlDB = helper.getWritableDatabase();
                sqlDB.execSQL(sql);
                updateUI();
            }
        });
        certeza.setNegativeButton("Não",null);
        certeza.create().show();
    }
}