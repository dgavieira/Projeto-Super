package com.projetosuper.animaisml;

import java.util.ArrayList;

public interface DAO {

    /*essa interface será responsável para fazer manipulações de comunicação entre o
    DatabaseHandler e as views
     */

        boolean create(Object obj);
        boolean update(int id, Object obj);
        boolean delete(int id);
        Object get(int id);
        ArrayList<Object> getList();
}
