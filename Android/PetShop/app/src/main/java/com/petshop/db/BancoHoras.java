package com.petshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.petshop.model.CalendarioConsulta;

import java.util.ArrayList;
import java.util.List;


public class BancoHoras extends SQLiteOpenHelper {

    static int VERSION = 1;
    private static final String BANCO_HORAS = "horash.db";
    private static final String TABELA_CONSULTAS = "tb_consu";
    public  String TABELA_DOIS ;

    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_DIA = "dia";
    private static final String COLUNA_HORA = "hora";
    private static final String COLUNA_ANO = "ano";


    public BancoHoras(Context context) {
        super(context, BANCO_HORAS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COLUNA = "CREATE TABLE IF NOT EXISTS " + TABELA_CONSULTAS + " ( "
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_DIA + " TEXT, "
                + COLUNA_HORA + " TEXT, "
                + COLUNA_ANO + " TEXT )";


        db.execSQL(QUERY_COLUNA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String QUERY_COLUNA = "CREATE TABLE IF NOT EXISTS " + TABELA_DOIS + " ( "
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_DIA + " TEXT, "
                + COLUNA_HORA + " TEXT, "
                + COLUNA_ANO + " TEXT )";

        db.execSQL(QUERY_COLUNA);
        onCreate(db);

    }

    public String addAtendimento(String oi, String poi, String ano){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_DIA, oi);
        values.put(COLUNA_HORA, poi);
        values.put(COLUNA_ANO, ano);

        long resultado = db.insert(TABELA_CONSULTAS, null, values);
        db.close();


        if(resultado == -1)
            return "erro";
        else{
            return "Sucesso";
        }
    }

    public List<CalendarioConsulta> listaConsultas(){
        List<CalendarioConsulta> consultas = new ArrayList<CalendarioConsulta>();

        String query = "SELECT * FROM " + TABELA_CONSULTAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do{
                CalendarioConsulta con = new CalendarioConsulta();
                con.setId(Integer.parseInt(c.getString(0)));
                con.setDia(c.getString(1));
                con.setHora(c.getString(2));
                con.setAno(c.getString(3));


                consultas.add(con);
            }while (c.moveToNext());
        }

        return consultas;
    }


    public static void setVERSION(int VERSION) {
        BancoHoras.VERSION = VERSION;
    }

    public static int getVERSION() {
        return VERSION;
    }
}
