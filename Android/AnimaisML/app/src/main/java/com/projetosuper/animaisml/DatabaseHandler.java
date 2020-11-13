package com.projetosuper.animaisml;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    Context context = null;
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Petshop.db";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ANIMAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_ANIMAL);
        onCreate(db);

    }

    private static final String SQL_CREATE_TABLE_ANIMAL =
            "CREATE TABLE Animal ( "+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    "nome TEXT, "+
                    "categoria TEXT, "+
                    "especie TEXT, " +
                    "idade INTEGER)";

    protected static final String SQL_SELECT_ANIMAL_BY_ID =
            "SELECT * FROM Animal WHERE id=?";

    protected static final String SQL_SELECT_ALL_ANIMAL_BY_ID =
            "SELECT nome,categoria,especie,idade FROM Animal ORDER BY nome";

    protected static final String SQL_DELETE_TABLE_ANIMAL =
            "DROP TABLE Animal";
}
