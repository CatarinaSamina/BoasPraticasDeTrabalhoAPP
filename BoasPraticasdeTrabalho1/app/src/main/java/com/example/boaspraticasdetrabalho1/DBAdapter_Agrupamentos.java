package com.example.boaspraticasdetrabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBAdapter_Agrupamentos {

    DBHelper helper;
    SQLiteDatabase mDataBase;

    public DBAdapter_Agrupamentos(Context mContext) {
        helper = new DBHelper(mContext);
    }



    public ArrayList<Spacecraft_Agrupamentos> retrieveSpacecrafts() {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Groups.Name, Groups.Description, Account.Username, Groups.CreationDate, Groups.ID" + " from Groups" + " inner join Account" + " on Groups.Account_ID = Account.ID ";
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

    public ArrayList<Spacecraft> veragrupamento_frases(String a) {
        ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Acao.Name, Phrase.Artefact, Account.Username, Phrase.CreationDate, Phrase.Resource, Groups_has_Phrase.Groups_ID" + " from Groups_has_Phrase" + " inner join Phrase" + " on Phrase.ID = Groups_has_Phrase.Phrase_ID" + " inner join Acao" + " on Phrase.Acao_ID = Acao.ID " + "inner join Account" + " on Phrase.Subject_ID = Account.ID" + " and Groups_has_Phrase.Groups_ID = ?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_action = c.getString(0);
                    String s_artefact = c.getString(1);
                    String s_subject = c.getString(2);
                    String s_date = c.getString(3);
                    String s_resource = c.getString(4);
                    String s_id = c.getString(5);
                    s = new Spacecraft();
                    s.setAction_name(s_action);
                    s.setArtefact(s_artefact);
                    s.setSubject(s_subject);
                    s.setData(s_date);
                    s.setResource(s_resource);
                    s.setId(s_id);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }

    public ArrayList<Spacecraft_Agrupamentos> padroes_veragrupamento(String a) {
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Pattern.Name, Pattern.Description, Account.Username, Pattern.CreationDate, Pattern_has_Groups.Groups_ID" + " from Pattern_has_Groups" + " inner join Account" + " on Pattern.Account_ID = Account.ID " + " inner join Pattern" + " on Pattern_has_Groups.Pattern_ID = Pattern.ID" + " and Pattern_has_Groups.Groups_ID = ?";
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
