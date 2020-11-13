package com.projetosuper.animaisml;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

public class AnimalDAO implements DAO{
    private SQLiteDatabase db;

    public AnimalDAO(Context context){
        this.db = (new DatabaseHandler(context)).getWritableDatabase();
    }

    @Override
    public boolean create(Object obj) {
        if(obj instanceof Animal){
            ContentValues values = createContentValues(obj);
            //INSERT INTO Animal
            if(db.insert("Animal",null,values) != -1){
                db.close();
                return true;
            }else{
                Log.e("Model","Error ao criar Animal (AnimalDAO)");
                db.close();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(int id, Object obj) {
        if(obj instanceof Animal){
            Animal animal = (Animal) obj;
            ContentValues values = createContentValues(obj);
            //INSERT INTO Animal WHERE id = ?
            String[] whereArgs = {String.valueOf(animal.getId())};
            //
            if ( db.update("Animal", values, "id=?", whereArgs) != 0 ){
                db.close();
                return true;
            } else {
                Log.e("Model","Error ao atualizar Animal (AnimalDAO)");
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
        Animal animal = null;
        String sql = String.format(DatabaseHandler.SQL_SELECT_ANIMAL_BY_ID);
        String clauses[] = {Integer.toString(id)};

        Cursor cursor = this.db.rawQuery(sql, clauses);

        if(cursor.moveToNext()){
            animal = new Animal(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
        }
        cursor.close();
        return animal;
    }

    @Override
    public ArrayList<Object> getList() {
        Cursor cursor = this.db.rawQuery(DatabaseHandler.SQL_SELECT_ALL_ANIMAL_BY_ID, null);
        ArrayList<Object> animais = new ArrayList<>();
        if(cursor.moveToNext()){
            do {
                animais.add(
                        new Animal(cursor.getString(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getInt(3)));
            }while (cursor.moveToNext());
        }
        return animais;
    }
    protected static ContentValues createContentValues(Object object){
        Animal animal = (Animal) object;
        ContentValues values = new ContentValues();
        values.put("nome",animal.getNome());
        values.put("categoria",animal.getCategoria());
        values.put("especie",animal.getEspecie());
        values.put("idade",animal.getIdade());
        return values;
    }
}
