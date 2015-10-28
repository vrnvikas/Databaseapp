package com.materialtest.vikaskumar.databaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Vikas Kumar on 11-07-2015.
 */
public class VikasDatabaseAdapter {

    VikasHelper helper;

    public VikasDatabaseAdapter(Context context) {
        helper = new VikasHelper(context);
    }

    public long insertData(String user, String password) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.Name, user);
        contentValues.put(helper.Password, password);
        long id = db.insert(helper.Tablename, null, contentValues);

        return id;
    }

    public ArrayList<String> viewAllData() {

        ArrayList<String> list = new ArrayList<String>();
        String[] columns = {helper.UID, helper.Name, helper.Password};
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(helper.Tablename, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(helper.UID);
            int index2 = cursor.getColumnIndex(helper.Name);
            int index3 = cursor.getColumnIndex(helper.Password);
            int id = cursor.getInt(index1);
            String name = cursor.getString(index2);
            String password = cursor.getString(index3);
            buffer.append(id + " " + name + " " + password + "\n");
            list.add(id + "    " + name + "    " + password);
        }

        return list;
    }

    public ArrayList<Integer> viewIdData() {

        ArrayList<Integer> idList = new ArrayList<>();
        String[] columns = {helper.UID};
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(helper.Tablename, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(helper.UID);
            int id = cursor.getInt(index1);
            idList.add(id);
        }

        return idList;
    }

    public ArrayList<String> viewNameData() {

        ArrayList<String> nameList = new ArrayList<String>();
        String[] columns = {helper.Name};
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(helper.Tablename, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index2 = cursor.getColumnIndex(helper.Name);
            String name = cursor.getString(index2);
            nameList.add(name);
        }

        return nameList;
    }

    public ArrayList<String> viewPasswordData() {

        ArrayList<String> passwordList = new ArrayList<String>();
        String[] columns = {helper.Password};
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(helper.Tablename, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index3 = cursor.getColumnIndex(helper.Password);
            String password = cursor.getString(index3);
            passwordList.add(password);
        }

        return passwordList;
    }

    public String viewData(String name) {


        String[] columns = {helper.UID, helper.Name, helper.Password};
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] selectionArgs = {helper.Name};
        String query = helper.Name + " =?";
        Cursor cursor = db.query(helper.Tablename, columns, helper.Name + " = '" + name + "'", null, null, null, null);

        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(helper.UID);
            int index2 = cursor.getColumnIndex(helper.Name);
            int index3 = cursor.getColumnIndex(helper.Password);
            int id = cursor.getInt(index1);
            String userName = cursor.getString(index2);
            String password = cursor.getString(index3);
            buffer.append(id + " " + userName + " " + password + "\n");


        }
        return buffer.toString();

    }

    public void updateName(String oldName, String newName) {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.Name, newName);
        String[] selecArgs = {oldName};
        db.update(helper.Tablename, contentValues, helper.Name + " =?", selecArgs);

    }

    public int deleteRow(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {name};
        int count = db.delete(helper.Tablename, helper.Name + " =?", whereArgs);
        return count;

    }

    public class VikasHelper extends SQLiteOpenHelper {


        private static final int DataBaseVersion = 1;
        private static final String DataBaseName = "vikasDatabase";
        private static final String Tablename = "vikasTable";
        private static final String UID = "id";
        private static final String Name = "name";
        private static final String Password = "password";
        private static final String CREATETABLE = "CREATE TABLE " + Tablename + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Name + " VARCHAR(250), " + Password + " VARCHAR(250));";
        private static final String DROPTABLE = "DROP TABLE IF EXISTS" + Tablename;

        public VikasHelper(Context context) {
            super(context, DataBaseName, null, DataBaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATETABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROPTABLE);
        }
    }


}
