package com.projetosuper.listatarefas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskDBHelper extends SQLiteOpenHelper {
    public TaskDBHelper(@Nullable Context context) {
        super(context, com.projetosuper.listatarefas.db.TaskContract.DB_NAME, null,
                com.projetosuper.listatarefas.db.TaskContract.DB_VERSION());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sqlQuery = String.format("CREATE TABLE %s (" + " id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "%s TEXT)", com.projetosuper.listatarefas.db.TaskContract.TABLE, TaskContract.Columns.TAREFA);

    }
}
