package com.example.boaspraticasdetrabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBAdapter {
    DBHelper helper;
    SQLiteDatabase mDataBase;

    public DBAdapter(Context mContext) {
        helper = new DBHelper(mContext);
    }


    public ArrayList<Spacecraft> retrieveSpacecrafts(String a) {
        ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Account.Username, Acao.Name, Phrase.Artefact, Phrase.Receiver_ID, Phrase.CreationDate, Phrase.Resource, Phrase.ID" + " from Phrase" + " inner join Acao" + " on Phrase.Acao_ID = Acao.ID " + "inner join Account" + " on Phrase.Subject_ID = Account.ID" + " and Account.Username = ?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_subject = c.getString(0);
                    String s_action = c.getString(1);
                    String s_receiver = c.getString(2);
                    String s_artefact = c.getString(3);
                    String s_date = c.getString(4);
                    String s_resource = c.getString(5);
                    String s_id = c.getString(6);

                    s = new Spacecraft();
                    s.setId(s_id);
                    s.setSubject(s_subject);
                    s.setAction_name(s_action);
                    s.setReceiver(s_receiver);
                    s.setArtefact(s_artefact);
                    s.setData(s_date);
                    s.setResource(s_resource);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }



    public String getName_Perfil(String a) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String rawQuery;
        rawQuery = "select Account.Name" + " from Account" + " where Account.Username =? ";
        String[] selectionArgs = {a};
        Cursor c;
        c = db.rawQuery(rawQuery, selectionArgs);
        c.moveToFirst();
        String result = c.getString(c.getColumnIndexOrThrow("Name"));
        return result;
    }

    public String getAniversário(String a) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String rawQuery;
        rawQuery = "select Account.Birthday" + " from Account" + " where Account.Username =? ";
        String[] selectionArgs = {a};
        Cursor c;
        c = db.rawQuery(rawQuery, selectionArgs);
        c.moveToFirst();
        String result = c.getString(c.getColumnIndexOrThrow("Birthday"));
        return result;
    }

    public String getEmail(String a) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String rawQuery;
        rawQuery = "select Account.Email" + " from Account" + " where Account.Username =? ";
        String[] selectionArgs = {a};
        Cursor c;
        c = db.rawQuery(rawQuery, selectionArgs);
        c.moveToFirst();
        String result = c.getString(c.getColumnIndexOrThrow("Email"));
        return result;
    }

    public String getCC(String a) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String rawQuery;
        rawQuery = "select Account.CC" + " from Account" + " where Account.Username =? ";
        String[] selectionArgs = {a};
        Cursor c;
        c = db.rawQuery(rawQuery, selectionArgs);
        c.moveToFirst();
        String result = c.getString(c.getColumnIndexOrThrow("CC"));
        return result;
    }

    public String getEmpresa(String a) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String rawQuery;
        rawQuery = "select Organization.Name" + " from Account" + " inner join Organization" + " on Account.Organization_ID = Organization.ID" + " where Account.Username =? ";
        String[] selectionArgs = {a};
        Cursor c;
        c = db.rawQuery(rawQuery, selectionArgs);
        c.moveToFirst();
        String result = c.getString(c.getColumnIndexOrThrow("Name"));
        return result;
    }

    public String getMorada(String a) {
        SQLiteDatabase db = helper.getReadableDatabase();
        String rawQuery;
        rawQuery = "select Account.Address" + " from Account" + " where Account.Username =? ";
        String[] selectionArgs = {a};
        Cursor c;
        c = db.rawQuery(rawQuery, selectionArgs);
        c.moveToFirst();
        String result = c.getString(c.getColumnIndexOrThrow("Address"));
        return result;
    }
}



