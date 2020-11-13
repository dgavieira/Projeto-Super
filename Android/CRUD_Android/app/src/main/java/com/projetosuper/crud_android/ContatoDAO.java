package com.projetosuper.crud_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private final String TABLE_CONTACTS = "Contatos";
    private DbGateway gw;

    public ContatoDAO(Context context){
        gw = DbGateway.getInstance(context);
    }
    public boolean salvar(String nome, String empresa, String telefone, String email){
        return salvar(0, nome, empresa, telefone, email);
    }

    public boolean salvar(int id, String nome, String empresa, String telefone, String email){
        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Empresa", empresa);
        cv.put("Telefone", telefone);
        cv.put("Email", email);
        if(id > 0)
            return gw.getDatabase().update(TABLE_CONTACTS, cv, "ID=?", new String[]{ id + ""}) > 0;
        else
            return gw.getDatabase().insert(TABLE_CONTACTS, null, cv) > 0;
    }
    public boolean excluir(int id){
        return gw.getDatabase().delete(TABLE_CONTACTS, "ID=?", new String[]{ id + ""}) > 0;
    }

    public List<Contato> retornarTodos(){
        List<Contato> contatos = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Contatos", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String empresa = cursor.getString(2);
            String telefone = cursor.getString(3);
            String email = cursor.getString(4);
            contatos.add(new Contato(id, nome, empresa, telefone, email));
        }
        cursor.close();
        return contatos;
    }

    public Contato retornarUltimo(){
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Contatos ORDER BY ID DESC", null);
        if(cursor.moveToFirst()){
            int id = cursor.getInt(0);
            String nome = cursor.getString(1);
            String empresa = cursor.getString(2);
            String telefone = cursor.getString(3);
            String email = cursor.getString(4);
            cursor.close();
            return new Contato(id, nome, empresa, telefone, email);
        }
        return null;
    }
}
