package com.petshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.petshop.R;
import com.petshop.model.Animais;
import com.petshop.model.DbBitmapUtility;
import com.petshop.model.Produto;

import java.io.IOException;

public class Database extends SQLiteOpenHelper {

    // Nome e versão do banco de dados
    public static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "LojaApp.db";
    Context context = null;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_PRODUCT);
        db.execSQL(SQL_CREATE_TABLE_SHOP);
        db.execSQL(SQL_POPULATE_USER_TABLE);
        db.execSQL(SQL_CREATE_TABLE_ANIMAL);

        // Popular produtos
        for ( int i = 0; i < nomeProdutos.length; i++ ){

            // Obter byte[] de uma imagem
            byte[] img = null;
            try {
                img = DbBitmapUtility.getBytes(BitmapFactory.decodeResource(context.getResources(), imgProdutos[i]));
            } catch (IOException e) {
                Log.e("LojaModel","Error ao recuperar imagem do produto " + nomeProdutos[i]);
                break;
            }

            // Cria um novo produto
            Produto p = new Produto(
                    nomeProdutos[i],
                    descricaoProdutos[i],
                    precoProdutos[i],
                    img
            );
            ContentValues values = ProdutoDAO.createContentValues(p);

            // Adiciona do banco de dados
            db.insert("Produto", null, values);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_USER);
        db.execSQL(SQL_DELETE_TABLE_PRODUTO);
        db.execSQL(SQL_DELETE_TABLE_SHOP);
        onCreate(db);
    }

    public Database (Context context ){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Criação das tabelas
    private static final String SQL_CREATE_TABLE_USER =
            "CREATE TABLE Usuario ( " +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "nome TEXT NOT NULL, " +
                    "senha TEXT NOT NULL, " +
                    "idade INT NOT NULL, " +
                    "img BLOB, " +
                    "email TEXT UNIQUE NOT NULL)";

    private static final String SQL_CREATE_TABLE_PRODUCT =
            "CREATE TABLE Produto ("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                    "nome text NOT NULL, "+
                    "descricao text NOT NULL, "+
                    "valor real NOT NULL, " +
                    "img BLOB)";

    private static final String SQL_CREATE_TABLE_SHOP =
            "CREATE TABLE Shop ("+
                    "id INTEGER not null primary key AUTOINCREMENT,"+
                    "idUsuario INTEGER not null,"+
                    "idProduto INTEGER not null,"+
                    "qnt INTEGER not null,"+
                    "FOREIGN KEY (idUsuario) REFERENCES Usuario(id),"+
                    "FOREIGN KEY (idProduto) REFERENCES Produto(id))";

    private static final String SQL_CREATE_TABLE_ANIMAL =
            "CREATE TABLE Animal ("+
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                    "nome text NOT NULL, "+
                    "categoria text NOT NULL, "+
                    "raca text NOT NULL, " +
                    "foto BLOB)";

    // Comados para deletar tabelas
    private static final String SQL_DELETE_TABLE_USER =
            "DROP TABLE IF EXISTS Usuario";

    private static final String SQL_DELETE_TABLE_PRODUTO =
            "DROP TABLE IF EXISTS Produto";

    private static final String SQL_DELETE_TABLE_SHOP =
            "DROP TABLE IF EXISTS Shop";

    private static final String SQL_DELETE_TABLE_ANIMAL =
            "DROP TABLE IF EXISTS Shop";

    // Comando para popular tabelas
    private static final String SQL_POPULATE_USER_TABLE =
            "INSERT INTO Usuario VALUES (" +
                    "0," +
                    "'root', " +    // nome
                    "'root', " +    //senha
                    "'21', " +
                    "null, " + // idade
                    "'root')"; //email

    // Comandos de SQL para o Usuário
    protected static final String SQL_SELECT_USER_BY_ID =
            "SELECT * FROM Usuario WHERE id=?";
    protected static final String SQL_SELECT_USER_BY_EMAIL =
            "SELECT * FROM Usuario WHERE email=?";
    protected static final String SQL_SELECT_ALL_USER_BY_ID =
            "SELECT id,nome,senha,idade,email FROM Usuario ORDER BY nome";

    // Comandos de SQL para o Produto
    protected static final String SQL_SELECT_PRODUTO_BY_ID =
            "SELECT * FROM Produto WHERE id=?";
    protected static final String SQL_SELECT_ALL_PRODUTO_BY_ID =
            "SELECT id,nome,descricao,valor,img FROM Produto ORDER BY nome";

    // Comandos de SQL para o Animal
    protected static final String SQL_SELECT_ANIMAL_BY_ID =
            "SELECT * FROM Animal WHERE id=?";
    protected static final String SQL_SELECT_ALL_ANIMAL_BY_ID =
            "SELECT id,nome,categoria,raca,foto FROM Animal ORDER BY nome";

    // Comandos de SQL para o Shop
    protected static final String SQL_SELECT_SHOP_BY_ID =
            "SELECT * FROM Shop WHERE id=?";
    protected static final String SQL_SELECT_ALL_SHOP_BY_IDUSUARIO =
            "SELECT * FROM Shop WHERE idUsuario=?";
    protected static final String SQL_JOIN_SHOP_PRODUTO =
            "SELECT Produto.nome,Produto.valor,Produto.img,Shop.qnt" +
                    " FROM Shop" +
                    " INNER JOIN Produto" +
                    " ON Shop.idProduto=Produto.id" +
                    " WHERE Shop.idUsuario=?" +
                    " ORDER BY Shop.id DESC";



    public Cursor retrieve(){
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[]  columns = {"id", "nome", "descricao", "valor", "img"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =  "nome" + " ASC";

        Cursor c = db.query(
                "Produto",                    // The table to query
                columns,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        return c;
    }

    public Cursor retrieveAnimal(){
        SQLiteDatabase db = getReadableDatabase();

        String[]  columns = {"id", "nome", "categoria", "raca", "foto"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =  "nome" + " ASC";

        Cursor c = db.query(
                "Animal",                    // The table to query
                columns,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                sortOrder                                   // The sort order
        );

        return c;
    }


    public void updateAnimal(Animais animais){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", animais.getNome());
        values.put("categoria", animais.getCategoria());
        values.put("raca", animais.getRaca());
        values.put("foto", animais.getFoto());

        String selection = "id" + " LIKE ?";
        String[] selectionArgs = { String.valueOf(animais.getId()) };

        int count = db.update(
                "Animal",
                values,
                selection,
                selectionArgs);
    }

    public boolean update(String newNome, String newCategoria, String newRaca, byte[] newFoto ,int id)
    {
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            ContentValues cv=new ContentValues();
            cv.put("nome",newNome);
            cv.put("categoria", newCategoria);
            cv.put("raca", newRaca);
            cv.put("foto", newFoto);


            int result=db.update("Animal",cv, "id" + " =?", new String[]{String.valueOf(id)});
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;

    }

    public void deleteAnimal(int id){
        SQLiteDatabase db = getReadableDatabase();

        String selection = "id" + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete("Animal", selection, selectionArgs);
    }

    // Produtos
    private String nomeProdutos[] = { "Cama", "Ração Cachorro", "Ração Gato", "Guia", "Osso", "Comedouro" };
    private String descricaoProdutos[] = {"Confortável e resistente, base em tecido impermeável.", "Ração Premium 100% completa e balanceada para cães adultos.", "Ração Premium 100% completa e balanceada para gatos adultos", "De fácil adaptação, segurança e resistência, própria para suportar fortes puxões.", "Indicado para cães e mantém seu pet distraído e livre de estress;", "Comedouro Duplo com potes removíveis e suporte para garrafa pet."};
    private double precoProdutos[] = { 80, 79.90, 69.90, 36.90, 19.99, 18.90 };
    private int imgProdutos[] = {R.drawable.cama, R.drawable.racaocachorro, R.drawable.racaogato, R.drawable.corrente, R.drawable.osso, R.drawable.comedouro };

}