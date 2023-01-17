package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBAdapter_Atividades {
    DBHelper helper;
    SQLiteDatabase mDataBase;

    public DBAdapter_Atividades(Context mContext) {
        helper = new DBHelper(mContext);
    }



    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts() {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Activity.Name, Activity.Description, Account.Username, Activity.CreationDate, Activity.ID" + " from Activity" + " inner join Account" + " on Activity.Account_ID = Account.ID ";
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_Agrupamentos s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, null);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_name = c.getString(0);
                    String s_description = c.getString(1);
                    String s_criador = c.getString(2);
                    String s_date = c.getString(3);
                    String s_id = c.getString(4);
                    s = new Spacecraft_Agrupamentos();
                    s.setName_group(s_name);
                    s.setDescription(s_description);
                    s.setCriador(s_criador);
                    s.setData(s_date);
                    s.setId(s_id);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }

    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts_produtos(String a) {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Product.Name, Product.Description, Account.Username, Product.CreationDate, Product.ID, Activity_has_Product.Activity_ID " + " from Activity_has_Product" + " inner join Account" + " on Product.Account_ID = Account.ID " + " inner join Product" + " on Activity_has_Product.Product_ID = Product.ID" + " and Activity_has_Product.Activity_ID = ?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_Agrupamentos s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_name = c.getString(0);
                    String s_description = c.getString(1);
                    String s_criador = c.getString(2);
                    String s_date = c.getString(3);
                    String s_id = c.getString(4);
                    String s_idatividade = c.getString(4);
                    s = new Spacecraft_Agrupamentos();
                    s.setName_group(s_name);
                    s.setDescription(s_description);
                    s.setCriador(s_criador);
                    s.setData(s_date);
                    s.setId(s_id);
                    s.setId_atividade(s_idatividade);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }


    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts_funcoes(String a) {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Function.Name, Function.Description, Account.Username, Function.CreationDate, Function.ID, Activity_has_Function.Activity_ID " + " from Activity_has_Function" + " inner join Account" + " on Function.Account_ID = Account.ID " + " inner join Function" + " on Activity_has_Function.Function_ID = Function.ID" + " and Activity_has_Function.Activity_ID = ?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_Agrupamentos s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_name = c.getString(0);
                    String s_description = c.getString(1);
                    String s_criador = c.getString(2);
                    String s_date = c.getString(3);
                    String s_id = c.getString(4);
                    String s_idatividade = c.getString(4);
                    s = new Spacecraft_Agrupamentos();
                    s.setName_group(s_name);
                    s.setDescription(s_description);
                    s.setCriador(s_criador);
                    s.setData(s_date);
                    s.setId(s_id);
                    s.setId_atividade(s_idatividade);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }

    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts_atividadesprodutos(String a) {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Activity.Name, Activity.Description, Account.Username, Activity.CreationDate, Activity.ID, Activity_has_Product.Product_ID" + " from Activity_has_Product" + " inner join Account" + " on Activity.Account_ID = Account.ID " + " inner join Activity" + " on Activity_has_Product.Activity_ID = Activity.ID" + " and Activity_has_Product.Product_ID = ?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_Agrupamentos s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_name = c.getString(0);
                    String s_description = c.getString(1);
                    String s_criador = c.getString(2);
                    String s_date = c.getString(3);
                    String s_id = c.getString(4);
                    s = new Spacecraft_Agrupamentos();
                    s.setName_group(s_name);
                    s.setDescription(s_description);
                    s.setCriador(s_criador);
                    s.setData(s_date);
                    s.setId(s_id);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }

    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts_atividadesfuncoes(String a) {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Activity.Name, Activity.Description, Account.Username, Activity.CreationDate, Activity.ID, Activity_has_Function.Function_ID" + " from Activity_has_Function" + " inner join Account" + " on Activity.Account_ID = Account.ID " + " inner join Activity" + " on Activity_has_Function.Activity_ID = Activity.ID" + " and Activity_has_Function.Function_ID = ?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_Agrupamentos s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_name = c.getString(0);
                    String s_description = c.getString(1);
                    String s_criador = c.getString(2);
                    String s_date = c.getString(3);
                    String s_id = c.getString(4);
                    s = new Spacecraft_Agrupamentos();
                    s.setName_group(s_name);
                    s.setDescription(s_description);
                    s.setCriador(s_criador);
                    s.setData(s_date);
                    s.setId(s_id);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }
}
