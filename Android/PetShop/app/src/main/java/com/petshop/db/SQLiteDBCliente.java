package com.petshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.petshop.model.Cliente;

public class SQLiteDBCliente extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Cliente.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";


    private static final String SQL_CREATE_AGENDA =
            "CREATE TABLE " + ClienteField.TABLE_NAME + "(" +
                    ClienteField.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ClienteField.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    ClienteField.COLUMN_PHONE + TEXT_TYPE + COMMA_SEP +
                    ClienteField.COLUMN_IDADE + " INTEGER" + COMMA_SEP +
                    ClienteField.COLUMN_PHOTO + " BLOB" + COMMA_SEP +
                    ClienteField.COLUMN_SENHA + TEXT_TYPE + COMMA_SEP +
                    ClienteField.COLUMN_CPF + TEXT_TYPE + COMMA_SEP +
                    ClienteField.COLUMN_EMAIL + TEXT_TYPE + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ClienteField.TABLE_NAME;

    public SQLiteDBCliente(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_AGENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void create(Cliente cliente){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ClienteField.COLUMN_NAME, cliente.getName());
        values.put(ClienteField.COLUMN_PHONE, cliente.getPhone());
        values.put(ClienteField.COLUMN_IDADE, cliente.getIdade());
        values.put(ClienteField.COLUMN_PHOTO, cliente.getPhoto());
        values.put(ClienteField.COLUMN_SENHA, cliente.getSenha());
        values.put(ClienteField.COLUMN_CPF, cliente.getCpf());
        values.put(ClienteField.COLUMN_EMAIL, cliente.getEmail());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                ClienteField.TABLE_NAME,
                null,
                values);
    }

    public Cursor retrieve(){
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[]  columns = {
                ClienteField.COLUMN_ID,
                ClienteField.COLUMN_NAME,
                ClienteField.COLUMN_PHONE,
                ClienteField.COLUMN_IDADE,
                ClienteField.COLUMN_PHOTO,
                ClienteField.COLUMN_SENHA,
                ClienteField.COLUMN_CPF,
                ClienteField.COLUMN_EMAIL};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ClienteField.COLUMN_NAME + " ASC";

        Cursor c = db.query(
                ClienteField.TABLE_NAME,                    // The table to query
                 columns,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        return c;
    }

    public void update(Cliente cliente){
        SQLiteDatabase db = getReadableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(ClienteField.COLUMN_NAME, cliente.getName());
        values.put(ClienteField.COLUMN_PHONE, cliente.getPhone());
        values.put(ClienteField.COLUMN_IDADE, cliente.getIdade());
        values.put(ClienteField.COLUMN_PHOTO, cliente.getPhoto());
        values.put(ClienteField.COLUMN_SENHA, cliente.getSenha());
        values.put(ClienteField.COLUMN_CPF, cliente.getCpf());
        values.put(ClienteField.COLUMN_EMAIL, cliente.getEmail());


        // Which row to update, based on the ID
        String selection = ClienteField.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(cliente.getId()) };

        int count = db.update(
                ClienteField.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


    public void delete(int id){
        SQLiteDatabase db = getReadableDatabase();

        // Define 'where' part of query.
        String selection = ClienteField.COLUMN_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
        // Issue SQL statement.
        db.delete(ClienteField.TABLE_NAME, selection, selectionArgs);
    }
}
