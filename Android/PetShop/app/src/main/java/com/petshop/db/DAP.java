package com.petshop.db;

import java.util.ArrayList;

public interface DAP {

    boolean create(Object obj);
    boolean update(int id, Object obj);
    boolean delete(int id);
    Object get(int id);
    ArrayList<Object> getList();

}