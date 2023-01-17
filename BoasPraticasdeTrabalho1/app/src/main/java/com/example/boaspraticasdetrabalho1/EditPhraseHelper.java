package com.example.boaspraticasdetrabalho1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

public class EditPhraseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "praticas.db";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;
    String currentColumn = "";

    private int index_action = 0;
    private int index_receiver = 0;

    static final String DATA = "CreationDate";
    static final String SUBJECT = "Subject_ID";
    static final String ACTION_ID = "Acao_ID";
    static final String ACOUNT_ID = "Account_ID";
    static final String RECEIVER = "Receiver_ID";
    static final String ARTEFACT = "Artefact";
    static final String RESOURCE = "Resource";
    static final String TABLE_PHRASE = "Phrase";
    static final String TABLE_ACTION = "Acao";
    static final String ACTION_NAME = "Name";

    public EditPhraseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;
        this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    public String getDate(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select CreationDate " + "from Phrase " + "where ID ='" + value + "'",null);
        res.moveToFirst();
        String result = res.getString(res.getColumnIndexOrThrow("CreationDate"));
        return result;
    }

    public String getAction(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Name from Acao inner join Phrase ON Phrase.Acao_ID=Acao.ID where Phrase.ID ='" + value + "'",null);
        res.moveToFirst();
        String result = res.getString(res.getColumnIndexOrThrow("Name"));
        return result;
    }

    public String getReceiver(String value) {
        String result="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Username from Account inner join Phrase ON Account.ID=Phrase.Receiver_ID where Phrase.ID ='" + value + "'", null);
        res.moveToFirst();
        int x = res.getCount();
        if (x > 0)
            result = res.getString(res.getColumnIndexOrThrow("Username"));
        return result;
    }
    public String getArtefact(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Artefact from Phrase where ID ='" + value + "'",null);
        res.moveToFirst();
        String result = res.getString(res.getColumnIndexOrThrow("Artefact"));
        return result;
    }

    public String getResource(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Resource from Phrase where ID ='" + value + "'",null);
        res.moveToFirst();
        String result = res.getString(res.getColumnIndexOrThrow("Resource"));
        return result;
    }

    public int getID_Subject(String subject){
        SQLiteDatabase db = this.getWritableDatabase();
        int y = -1;
        Cursor res1 = db.rawQuery("select ID, Username from Account", null);
        res1.moveToFirst();
        do{
            currentColumn = res1.getString(res1.getColumnIndexOrThrow("Username"));
            index_action = res1.getPosition();
            if (currentColumn.equals(subject)) {
                y = res1.getInt(res1.getColumnIndexOrThrow("ID"));
            }
        } while (res1.moveToNext());
        return y;
    }

    public boolean updateData(String date, String subject, String action, String recetor, String artefact, String resource, String id_phrase){
        int sub_id = getID_Subject(subject);
        int rec_id = getID_Receiver(recetor);
        if (TextUtils.isEmpty(recetor)) {
            if (isReceiverExist(recetor) == true) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(ACOUNT_ID, sub_id);
                contentValues.put(DATA, date);
                contentValues.put(SUBJECT, sub_id);
                contentValues.put(ACTION_ID, action);
                contentValues.put(RECEIVER, recetor);
                contentValues.put(ARTEFACT, artefact);
                contentValues.put(RESOURCE, resource);
                contentValues.put(ACTION_ID, action);
                long result = db.update(TABLE_PHRASE, contentValues, "ID=" + id_phrase, null);
                if (result == -1)
                    return false;
                return true;
            }
        }
        else {
            if (isReceiverExist(recetor) == true) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(ACOUNT_ID, sub_id);
                contentValues.put(DATA, date);
                contentValues.put(SUBJECT, sub_id);
                contentValues.put(ACTION_ID, action);
                contentValues.put(RECEIVER, rec_id);
                contentValues.put(ARTEFACT, artefact);
                contentValues.put(RESOURCE, resource);
                contentValues.put(ACTION_ID, action);
                long result = db.update(TABLE_PHRASE, contentValues, "_id=" + id_phrase, null);
                if (result == -1)
                    return false;
                return true;
            }
        }
        return false;
    }

    public boolean isReceiverExist(String name){
        boolean isExist = false;
        SQLiteDatabase db = this.getReadableDatabase();
        if (TextUtils.isEmpty(name)) {
            return true;
        }
        Cursor res = db.rawQuery("select Username " + "from Account",null);
        res.moveToFirst();
        do{
            currentColumn = res.getString(res.getColumnIndexOrThrow("Username"));
            index_receiver = res.getPosition();
            if (currentColumn.equals(name)) {
                isExist = true;
                return isExist;
            }
        } while (res.moveToNext());
        return isExist;
    }

    public int getID_Receiver(String receiver) {
        SQLiteDatabase db = this.getWritableDatabase();
        int y = -1;
        Cursor res1 = db.rawQuery("select ID, Username from Account", null);
        res1.moveToFirst();
        do{
            currentColumn = res1.getString(res1.getColumnIndexOrThrow("Username"));
            index_action = res1.getPosition();
            if (currentColumn.equals(receiver)) {
                y = res1.getInt(res1.getColumnIndexOrThrow("ID"));
            }
        } while (res1.moveToNext());
        return y;
    }

    public int insertAction(String subject, String date, String name){
        int control = -1;
        int sub_id = getID_Subject(subject);
        if(isActionExist(name) == false) {
            Log.w("rita", "vai acrescentar na base de dados uma nova aÃ§Ã£o ");
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ACOUNT_ID, sub_id);
            contentValues.put(DATA, date);
            contentValues.put(ACTION_NAME, name);
            long result = db.insert(TABLE_ACTION, null, contentValues);
            isActionExist(name);
            control = getID_action(name);
            if (result == -1)
                return control;
        }
        else {
            isActionExist(name);
            control = getID_action(name);
        }
        return control;
    }

    public boolean isActionExist(String name){
        boolean isExist = false;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select Name " + "from Acao",null);
        res.moveToFirst();
        do{
            currentColumn = res.getString(res.getColumnIndexOrThrow("Name"));
            index_action = res.getPosition();
            if (currentColumn.equals(name)) {
                isExist = true;
                return isExist;
            }
        } while (res.moveToNext());
        return isExist;

    }

    public int getID_action(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        int y = -1;
        Cursor res1 = db.rawQuery("select ID " + "from acao", null);
        res1.moveToPosition(index_action);
        if (currentColumn.equals(name)){
            y = res1.getInt(res1.getColumnIndexOrThrow("ID"));
        }
        return y;
    }


}
