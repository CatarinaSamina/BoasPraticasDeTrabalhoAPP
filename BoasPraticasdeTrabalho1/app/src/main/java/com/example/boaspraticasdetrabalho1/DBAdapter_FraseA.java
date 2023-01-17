package com.example.boaspraticasdetrabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBAdapter_FraseA {
    DBHelper helper;
    SQLiteDatabase mDataBase;

    public DBAdapter_FraseA(Context mContext) {
        helper = new DBHelper(mContext);
    }


    public ArrayList<Spacecraft> retrieveSpacecrafts() {
        ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Account.Username, Acao.Name, Phrase.Artefact, Phrase.CreationDate, Phrase.Resource, Phrase.ID" + " from Phrase" + " inner join Acao" + " on Phrase.Acao_ID = Acao.ID " + "inner join Account" + " on Phrase.Subject_ID = Account.ID";
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, null);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_subject = c.getString(0);
                    String s_action = c.getString(1);
                    String s_artefact = c.getString(2);
                    String s_date = c.getString(3);
                    String s_resource = c.getString(4);
                    String s_id = c.getString(5);
                    s = new Spacecraft();
                    s.setSubject(s_subject);
                    s.setAction_name(s_action);
                    s.setArtefact(s_artefact);
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

    public ArrayList<Spacecraft_FraseA> verfrase_agrupamentos(String a) {
        ArrayList<Spacecraft_FraseA> spacecrafts = new ArrayList<>();
        String rawQuery;
        rawQuery = "select Groups.Name, Groups.Description, Groups.CreationDate, Account.Username, Groups_has_Phrase.Groups_ID, Groups_has_Phrase.Phrase_ID" + " from Groups_has_Phrase" + " inner join Groups" + " on Groups_has_Phrase.Groups_ID = Groups.ID" + " inner join Account" + " on Groups.Account_ID = Account.ID" + " and Groups_has_Phrase.Phrase_ID =?";
        String[] selectionArgs = {a};
        try {
            mDataBase = helper.getWritableDatabase();
            Spacecraft_FraseA s;
            Cursor c;
            c = mDataBase.rawQuery(rawQuery, selectionArgs);
            if (c != null) {
                while (c.moveToNext()) {
                    String s_nomeagrupamento= c.getString(0);
                    String s_descricao = c.getString(1);
                    String s_data_agrupamento = c.getString(2);
                    String s_criador = c.getString(3);
                    String s_id = c.getString(4);
                    String s_idf = c.getString(5);
                    s = new Spacecraft_FraseA();
                    s.setNome_agrupamento(s_nomeagrupamento);
                    s.setDescricao_agrupamento(s_descricao);
                    s.setData_agrupamento(s_data_agrupamento);
                    s.setCriador_agrupamento(s_criador);
                    s.setId_agrupamento(s_id);
                    s.setId(s_idf);
                    spacecrafts.add(s);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spacecrafts;
    }

}
