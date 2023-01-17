package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBAdapter_Padroes {
    DBHelper helper;
    SQLiteDatabase mDataBase;

    public DBAdapter_Padroes(Context mContext) {
        helper = new DBHelper(mContext);
    }

    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts() {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Pattern.Name, Pattern.Description, Account.Username, Pattern.CreationDate, Pattern.ID" + " from Pattern" + " inner join Account" + " on Pattern.Account_ID = Account.ID ";
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

    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts_Agrupamentos(String a) {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Groups.Name, Groups.Description, Account.Username, Groups.CreationDate, Pattern_has_Groups.Pattern_ID" + " from Pattern_has_Groups" + " inner join Account" + " on Groups.Account_ID = Account.ID" + " inner join Groups" + " on Pattern_has_Groups.Groups_ID = Groups.ID" + " and Pattern_has_Groups.Pattern_ID = ?";
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
