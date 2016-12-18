package com.example.clarinetmaster.finalexamcpsu2016.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.clarinetmaster.finalexamcpsu2016.Database.DatabaseHelper;
import com.example.clarinetmaster.finalexamcpsu2016.R;

import java.util.ArrayList;

public class UsersMenu {

    private static DatabaseHelper helper;
    private static SQLiteDatabase db;

    private static UsersMenu mInstance;
    private static Context context;

    private static ArrayList<Users> mUsersList;

    public static UsersMenu getInstance(Context context){
        if(mInstance == null){
            mInstance = new UsersMenu(context);
        }
        return mInstance;
    }

    private UsersMenu(Context context) {
        this.context = context;
        loadFromDatabase();
    }

    public static void loadFromDatabase() {

        mUsersList = new ArrayList<>();
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
            String username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USERNAME));
            String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PASSWORD));
            mUsersList.add(new Users(name, username, password));
        }

        cursor.close();

    }

    public static ArrayList<Users> getUsersList() {
        return mUsersList;
    }

    public static Users findUsersByName(String username){
        for(Users u : mUsersList){
            if(u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public static void insertData(Users users){
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COL_NAME, users.getName());
        cv.put(DatabaseHelper.COL_USERNAME, users.getUsername());
        cv.put(DatabaseHelper.COL_PASSWORD, users.getPassword());
        db.insert(DatabaseHelper.TABLE_NAME, null, cv);
        db.close();
        loadFromDatabase();
        Toast.makeText(context, R.string.user_created, Toast.LENGTH_SHORT).show();
    }

}
