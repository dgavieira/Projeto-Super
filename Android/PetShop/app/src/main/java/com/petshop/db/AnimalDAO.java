package com.petshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.petshop.model.Animais;

import java.util.ArrayList;

public class AnimalDAO implements DAP {

    private SQLiteDatabase db;

    public AnimalDAO(Context context){
            this.db = (new Database(context)).getWritableDatabase();
    }

    @Override
    public boolean create(Object obj) {

        if ( obj instanceof Animais){

            ContentValues values = createContentValues(obj);
            if ( db.insert("Animal", null, values) != -1 ){
                db.close();
                return true;
            } else {
                Log.e("LojaModel","Error ao criar Animal");
                db.close();
                return false;
            }
        }

        return false;
    }

    @Override
    public boolean update(int id, Object obj) {

        if ( obj instanceof Animais ){

            Animais animais = (Animais) obj;

            ContentValues values = createContentValues(obj);
            String[] whereArgs = {String.valueOf(animais.getId())};

            if ( db.update("Animal", values, "id=?", whereArgs) != 0 ){
                db.close();
                return true;
            } else {
                Log.e("LojaModel","Error ao atualizar animal");
                db.close();
                return false;
            }

        }

        return false;

    }

    @Override
    public boolean delete(int id) {

        String[] where = {String.valueOf(id)};

        if ( db.delete("Animal","id=?",where) != 0 ){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object get(int id) {
        Animais animais = null;
        String sql = String.format(Database.SQL_SELECT_ANIMAL_BY_ID);
        String clauses[] = {Integer.toString(id)};

        Cursor cursor = this.db.rawQuery(sql, clauses);

        if ( cursor.moveToNext() ){
            animais = new Animais(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getBlob(4)
            );
        }

        cursor.close();
        return animais;
    }

    @Override
    public ArrayList<Object> getList() {
        Cursor cursor =  this.db.rawQuery(Database.SQL_SELECT_ALL_ANIMAL_BY_ID,null);
        ArrayList<Object> animais = new ArrayList<>();
        if ( cursor.moveToNext() ){
            do {
                animais.add( new Animais(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getBlob(4))
                );
            } while(cursor.moveToNext());
        }

        return animais;

    }

    protected static ContentValues createContentValues(Object obj){

        Animais animais = (Animais) obj;
        ContentValues values = new ContentValues();
        values.put("nome", animais.getNome());
        values.put("categoria", animais.getCategoria());
        values.put("raca", animais.getRaca());
        values.put("foto", animais.getFoto());
        return values;

    }

}
