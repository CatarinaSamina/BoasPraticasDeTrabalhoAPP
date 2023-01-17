package com.example.boaspraticasdetrabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBAdapter_Processo {
    DBHelper helper;
    SQLiteDatabase mDataBase;

    public  DBAdapter_Processo(Context mContext){
        helper = new DBHelper(mContext);
    }


    public ArrayList<Spacecraft_Processo> retrieveSpacecrafts_process(){
        ArrayList<Spacecraft_Processo> spacecrafts_process = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Process.Name, Process.Description,  Account.Username, Organization.Name, Process.ID" + " from Process" + " inner join Organization" + " on Process.Organization_ID = Organization.ID " + " inner join Account" + " on Process.Account_ID = Account.ID ";
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_Processo s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, null);
            if(c != null){
                while (c.moveToNext()){
                    String s_process = c.getString(0);
                    String s_description = c.getString(1);
                    String s_data = c.getString(2);
                    String s_criador = c.getString(3);
                    String s_IDProcess = c.getString(4);

                    s = new Spacecraft_Processo();
                    s.setProcess(s_process);
                    s.setDescription(s_description);
                    s.setData(s_data);
                    s.setAccount_name(s_criador);
                    s.setId_process(s_IDProcess);

                    spacecrafts_process.add(s);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return spacecrafts_process;
    }

    public ArrayList<Spacecraft_Processo> retrieveSpacecrafts_atividade(String a){
        ArrayList<Spacecraft_Processo> spacecrafts_process = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Activity.Name, Activity.Description, Account.Username, Activity.CreationDate, Activity.ID, Process_has_Activity.Process_ID" + " from Process_has_Activity" + " inner join Account" + " on Activity.Account_ID = Account.ID " + " inner join Activity" + " on Process_has_Activity.Activity_ID = Activity.ID" + " and Process_has_Activity.Process_ID = ?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_Processo s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if(c != null){
                while (c.moveToNext()){
                    String s_ativity= c.getString(0);
                    String s_description = c.getString(1);
                    String s_criador = c.getString(2);
                    String s_data = c.getString(3);
                    String s_IDAtivity = c.getString(4);
                    String s_IDProcess = c.getString(5);

                    s = new Spacecraft_Processo();
                    s.setProcess(s_ativity);
                    s.setDescription(s_description);
                    s.setData(s_data);
                    s.setAccount_name(s_criador);
                    s.setId_ativity(s_IDAtivity);
                    s.setId_process(s_IDProcess);

                    spacecrafts_process.add(s);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return spacecrafts_process;
    }


}
