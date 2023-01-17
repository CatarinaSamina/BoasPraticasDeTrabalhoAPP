package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DeletePhraseHelper extends SQLiteOpenHelper {


    private static String DB_NAME = "praticas.db";
    private static String DB_PATH = "";
    private static String TABLE_PHRASE = "Phrase";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DeletePhraseHelper(Context context){
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

    public void deletePhrase(String value){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean miau = db.delete(TABLE_PHRASE, "ID = " + value,null) > 0;
    }

}
