package com.petshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.NoCopySpan;
import android.util.Log;

import com.petshop.model.Animais;

import java.util.ArrayList;
import java.util.List;

//A classe e sua extensão para conexaão com o banco de dados.
public class SQLDataAnimal extends SQLiteOpenHelper {

    //Criação do nome do banco de dados e versão.
    public SQLDataAnimal(Context context) {
        super(context, "banco", null, 10);
    }


    /*Subescrevendo o método de criação do objeto no  banco de dados.
     O método Oncreate cria o banco no momento de execução do aplicativo*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql  = "CREATE TABLE IF NOT EXISTS animais(" +
                "id INTEGER not null primary key AUTOINCREMENT ," +
                "nome TEXT UNIQUE ," +
                "categoria TEXT ," +
                "foto BLOB ," +
                "raca TEXT );";
                db.execSQL(sql);
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS animais;";
        db.execSQL(sql);
        onCreate(db);
    }


    public void insereAnimal(Animais animais, String animaisParaAtualizar) {


        SQLiteDatabase db = getWritableDatabase(); //verificar
        ContentValues dados = new ContentValues();

        dados.put("id", animais.getId());
        dados.put("nome", animais.getNome());
        dados.put("raca", animais.getRaca());
        dados.put("categoria", animais.getCategoria());
        dados.put("foto", animais.getFoto());

        if(animaisParaAtualizar != null){
            dados.put("id", animaisParaAtualizar);
        }else{
            dados.put("id", animais.getId());
        }
        try{
            db.insertOrThrow("animais",null, dados);
        }catch (SQLiteConstraintException e){
            dados.put("id", animais.getId());
            db.update("animais", dados, "id = ?", new String[]{animaisParaAtualizar});

        }

    }
    // método de busca no banco.Retornando uma lista de animais.
    public List<Animais> buscaAnimais(){
        //leitura no banco de dados.
        SQLiteDatabase db = getReadableDatabase();
        String sql =  "SELECT * FROM animais;";

        //Buscando diretamente no banco de dados.
        Cursor c = db.rawQuery(sql,null);
        //Adicionando na lista.
        List<Animais> animal = new ArrayList<Animais>();

        //Enquanto tiver dados no banco. executo uma ação.
        while(c.moveToNext()){
            Animais animais = new Animais();
            animais.setId(c.getInt(c.getColumnIndex("id")));
            animais.setNome(c.getString(c.getColumnIndex("nome")));
            animais.setCategoria(c.getString(c.getColumnIndex("categoria")));
            animais.setRaca(c.getString(c.getColumnIndex("raca")));
            animais.setFoto(c.getBlob(c.getColumnIndex("foto")));
            animal.add(animais);

        }
        return animal;

    }

    public void apagaAnimais(Integer id){
        SQLiteDatabase db  = getReadableDatabase();
        String sql =  "DELETE FROM animais WHERE id = " + "'" + id + "'";
        db.execSQL(sql);
    }

}
