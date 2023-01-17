package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
        private static String DB_NAME = "praticas.db";
        private static String DB_PATH = "";
        private static final int DB_VERSION = 1;

        private SQLiteDatabase mDataBase;
        private final Context mContext;
        private boolean mNeedUpdate = false;

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            if (android.os.Build.VERSION.SDK_INT >= 17)
                DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
            else
                DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
            this.mContext = context;

            copyDataBase();
            // updateDataBase();
            this.getReadableDatabase();
        }

        public void updateDataBase() {
            if (mNeedUpdate) {
                File dbFile = new File(DB_PATH + DB_NAME);
                if (dbFile.exists()) {
                    dbFile.delete();
                } else {
                    copyDataBase();

                    mNeedUpdate = true;
                }
            }
        }
        //---------------------------------------------------------------

        private boolean checkDataBase1() {

            SQLiteDatabase checkDB = null;

            try {
                String myPath = DB_PATH + DB_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

            } catch (SQLiteException e) {

                //database does't exist yet.

            }

            if (checkDB != null) {

                checkDB.close();

            }

            return checkDB != null;
        }


        //-----------------------------------------------------------------------

        private boolean checkDataBase() {
            Log.w("catarina", "bd");
            File dbFile = new File(DB_PATH + DB_NAME);
            return dbFile.exists();
        }

        ////-------INFO!!!! PARA A BASE DE DADOS NÃƒO ESTAR SEMPRE A SER CRIADA DE NOVO, MUDAR O CHECKDATABASE PARA !CHECKDATABASE-------------------
        private void copyDataBase() {
            if (!checkDataBase()) {
                this.getReadableDatabase();
                this.close();
                try {
                    copyDBFile();
                } catch (IOException mIOException) {
                    throw new Error("ErrorCopyingDataBase");
                }
            }
        }

        private void copyDBFile() throws IOException {
            InputStream mInput = mContext.getAssets().open(DB_NAME);
            //InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
            OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = mInput.read(mBuffer)) > 0)
                mOutput.write(mBuffer, 0, mLength);
            mOutput.flush();
            mOutput.close();
            mInput.close();
        }

        public boolean openDataBase() throws SQLException {
            mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            return mDataBase != null;
        }

        @Override
        public synchronized void close() {
            if (mDataBase != null)
                mDataBase.close();
            super.close();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion)
                mNeedUpdate = true;
        }


        //-------------------- QUERY LOGIN ------------------------

        private static final String TABLE_UTILIZADOR = "Account";

        private static final String COLUMN_UTILIZADOR_USERNAME = "Username";
        private static final String COLUMN_UTILIZADOR_PASSWORD = "Password";
        private static final String ROLE = "Role";

        public boolean checkUser(String a, String b) {

            String[] columns = {
                    COLUMN_UTILIZADOR_USERNAME
            };
            SQLiteDatabase db = this.getReadableDatabase();


            String selection;
            selection = COLUMN_UTILIZADOR_USERNAME + " = ?" + " AND " + COLUMN_UTILIZADOR_PASSWORD + " = ?" + " AND " + ROLE + " = ?"  ;//+ " OR " + ROLE + " = ? )";
            String[] selectionArgs = {a, b, "Trabalhador"};


            Cursor cursor;
            cursor = db.query(TABLE_UTILIZADOR,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);
            int cursorCount = cursor.getCount();

            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return true;
            }
            return false;
        }

        public boolean checkGestor(String a, String b) {

            String[] columns = {
                    COLUMN_UTILIZADOR_USERNAME
            };
            SQLiteDatabase db = this.getReadableDatabase();


            String selection;
            selection = COLUMN_UTILIZADOR_USERNAME + " = ?" + " AND " + COLUMN_UTILIZADOR_PASSWORD + " = ?" + " AND " + ROLE + " = ?";
            String[] selectionArgs = {a, b, "Gestor"};


            Cursor cursor;
            cursor = db.query(TABLE_UTILIZADOR,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);
            int cursorCount = cursor.getCount();

            cursor.close();
            db.close();
            if (cursorCount > 0) {
                return true;
            }
            return false;
        }

    public boolean checkAnalista(String a, String b) {

        String[] columns = {
                COLUMN_UTILIZADOR_USERNAME
        };
        SQLiteDatabase db = this.getReadableDatabase();


        String selection;
        selection = COLUMN_UTILIZADOR_USERNAME + " = ?" + " AND " + COLUMN_UTILIZADOR_PASSWORD + " = ?" + " AND " + ROLE + " = ?";
        String[] selectionArgs = {a, b, "Analista"};


        Cursor cursor;
        cursor = db.query(TABLE_UTILIZADOR,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    }
