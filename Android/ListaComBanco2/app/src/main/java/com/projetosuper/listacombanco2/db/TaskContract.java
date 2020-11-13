package com.projetosuper.listacombanco2.db;

import android.provider.BaseColumns;

public class TaskContract {
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tarefas";
    public static final String DB_NAME = "com.projetosuper.listacombanco2.tarefa";

    public class Columns{
        public static final String TAREFA = "tarefa";
        public static final String _ID = BaseColumns._ID;
    }

}
