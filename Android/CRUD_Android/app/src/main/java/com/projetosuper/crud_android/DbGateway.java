package com.projetosuper.crud_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbGateway {
    private static DbGateway gw;
    private SQLiteDatabase db;

    private DbGateway(Context context){
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DbGateway getInstance(Context context){
        if (gw == null)
            gw = new DbGateway(context);
        return gw;
    }
    public SQLiteDatabase getDatabase(){
        return this.db;
    }

}
