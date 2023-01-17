package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBAdapterSearch {
    DBHelper helper;
    SQLiteDatabase mDataBase;

    public DBAdapterSearch(Context mContext) {
        helper = new DBHelper(mContext);
    }

    public ArrayList<Spacecraft> retrieveSpacecrafts_Search(String a, String b, String choice) {
        ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
        String rawQuery;
        /*if(Integer.valueOf(choice) == 1)
            rawQuery = "select Phrase.ID, Account.Username, Acao.Name, Phrase.Artefact,Phrase.CreationDate, Phrase.Resource, Phrase.Receiver_ID,  Phrase.ID" + " from Phrase" + " inner join Acao" + " on Phrase.Acao_ID = Acao.ID " + "inner join Account" + " on Phrase.Subject_ID = Account.ID" + " and Account.Username = ?";
        else */if(Integer.valueOf(choice) == 2)
            rawQuery = "select Account.Username, Acao.Name, Phrase.Artefact,  Phrase.Receiver_ID, Phrase.CreationDate, Phrase.Resource, Phrase.ID " + " from Phrase" + " inner join Acao" + " on Phrase.Acao_ID = Acao.ID " + "inner join Account" + " on Phrase.Subject_ID = Account.ID" + " and Account.Username = ?" + "and Acao.Name = ?";
        else
            rawQuery = "select Account.Username, Acao.Name, Phrase.Artefact,  Phrase.Receiver_ID, Phrase.CreationDate, Phrase.Resource, Phrase.ID " + " from Phrase" + " inner join Acao" + " on Phrase.Acao_ID = Acao.ID " + "inner join Account" + " on Phrase.Subject_ID = Account.ID" + " and Account.Username = ?" +  "and Phrase.Resource = ?";
        String[] selectionArgs = {a, b};
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

}
