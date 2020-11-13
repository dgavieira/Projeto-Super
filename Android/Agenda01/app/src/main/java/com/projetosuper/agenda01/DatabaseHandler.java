package com.projetosuper.agenda01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    //seta versão da database para o construtor
    private static final int DATABASE_VERSION = 1;

    //seta strings que comporão colunas da tabela
    private static final String DATABASE_NAME = "controleAgenda",
        TABLE_CONTACTS = "contatos",
        KEY_ID = "_id",
        KEY_NAME = "nome",
        KEY_PHONE = "telefone",
        KEY_ADRESS = "endereco",
        KEY_EMAIL = "email",
        KEY_IMAGEURI = "imageUri";

    //construtor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //override methods


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("CREATE TABLE " + TABLE_CONTACTS + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT," +
                KEY_PHONE + " TEXT," +
                KEY_ADRESS + " TEXT," +
                KEY_EMAIL + " TEXT," +
                KEY_IMAGEURI + " TEXT)");
    }

    /*Understanding the reason of need to drop table on a onUpgrade override method:
    https://stackoverflow.com/questions/20277410/why-does-sqliteopenhelper-drop-the-table-in-onupgrade-method
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
