package com.petshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.petshop.model.Veterinario;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    BancoHoras bancoHoras;

    static int VERSAO = 1;
    private static final String BANCO_CLIENTE = "clioi.db";
    private static final String TABELA_CLIENTE = "tb_cliente";
    public  String TABELA_DOIS ;


    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_IDADE = "idade";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_ESPECIALIDADE = "especialidade";
    private static final String COLUNA_TEMPODEEXP = "experiencia";
    private static final String COLUNA_ENDERECO = "endereco";
    private static final String COLUNA_VALORCONSULTA = "valor_da_consulta";



    public BancoDados(Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String QUERY_COLUNA = "CREATE TABLE IF NOT EXISTS " + TABELA_CLIENTE + " ( "
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUNA_NOME + " TEXT, "
                + COLUNA_IDADE + " INTEGER, "
                + COLUNA_TELEFONE + " TEXT, "
                + COLUNA_ESPECIALIDADE + " TEXT, "
                + COLUNA_TEMPODEEXP + " INTEGER, "
                + COLUNA_ENDERECO + " TEXT, "
                + COLUNA_VALORCONSULTA + " REAL )";


        sqLiteDatabase.execSQL(QUERY_COLUNA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public String addVet(Veterinario veterinario){


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, veterinario.getNome());
        values.put(COLUNA_IDADE, veterinario.getIdade());
        values.put(COLUNA_TELEFONE, veterinario.getTelefone());
        values.put(COLUNA_ESPECIALIDADE, veterinario.getEspecialidade());
        values.put(COLUNA_TEMPODEEXP, veterinario.getTempo_exp());
        values.put(COLUNA_ENDERECO, veterinario.getEndereço());
        values.put(COLUNA_VALORCONSULTA, veterinario.getValor_consulta());

        long resultado = db.insert(TABELA_CLIENTE, null, values);
        db.close();


        if(resultado == -1)
            return "erro";
        else{
            return "Sucesso";
        }
    }

    Veterinario selecionarVet(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_CLIENTE, new String[] {COLUNA_CODIGO, COLUNA_NOME, COLUNA_IDADE,
                        COLUNA_TELEFONE, COLUNA_ESPECIALIDADE, COLUNA_TEMPODEEXP, COLUNA_ENDERECO, COLUNA_VALORCONSULTA},
        COLUNA_CODIGO + " = ?", new String[] {String.valueOf(codigo)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Veterinario veterinario = new Veterinario(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)), cursor.getString(6), Float.parseFloat(cursor.getString(7)));
        return veterinario;
    }

    void atualizaVet(Veterinario veterinario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, veterinario.getNome());
        values.put(COLUNA_IDADE, veterinario.getIdade());
        values.put(COLUNA_TELEFONE, veterinario.getTelefone());
        values.put(COLUNA_ESPECIALIDADE, veterinario.getEspecialidade());
        values.put(COLUNA_TEMPODEEXP, veterinario.getTempo_exp());
        values.put(COLUNA_ENDERECO, veterinario.getEndereço());
        values.put(COLUNA_VALORCONSULTA, veterinario.getValor_consulta());

        db.update(TABELA_CLIENTE, values, COLUNA_CODIGO + " = ?", new String[] {String.valueOf(veterinario.getCodigo())});


    }

    void apagaVet(Veterinario veterinario){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CLIENTE, COLUNA_CODIGO + " = ?", new String[] {String.valueOf(veterinario.getCodigo())});
        db.close();
    }

    public List<Veterinario> listaVet(){
        List<Veterinario> veterinarios = new ArrayList<Veterinario>();

        String query = "SELECT * FROM " + TABELA_CLIENTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()){
            do{
                Veterinario veterinario = new Veterinario();
                veterinario.setCodigo(Integer.parseInt(c.getString(0)));
                veterinario.setNome(c.getString(1));
                veterinario.setIdade(Integer.parseInt(c.getString(2)));
                veterinario.setTelefone(c.getString(3));
                veterinario.setEspecialidade(c.getString(4));
                veterinario.setTempo_exp(Integer.parseInt(c.getString(5)));
                veterinario.setEndereço(c.getString(6));
                veterinario.setValor_consulta(Float.parseFloat(c.getString(7)));

                veterinarios.add(veterinario);

            }while (c.moveToNext());
        }

        return veterinarios;
    }


    public static void setVERSAO(int VERSAO) {
        BancoDados.VERSAO = VERSAO;

    }

    public static int getVERSAO() {
        return VERSAO;
    }
}
