package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.database.model.DatabaseModel;
import com.example.database.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB.db";

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_TABLE = "users";

    private static final String COLUMN_USERS_NAME = "name";

    private static final String COLUMN_USERS_EMAIL = "email";

    private static final String COLUMN_USERS_PASSWORD = "password";

    private static final String COLUMN_USERS_ID = "id";

    private static final String SQL_CREATES_ENTRIES =
            "CREATE TABLE " + "DATABASE_TABLE" + "(" + COLUMN_USERS_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USERS_NAME + " TEXT,"
                    + COLUMN_USERS_EMAIL + " TEXT," + COLUMN_USERS_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DATABASE_TABLE;


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATES_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);

        onCreate(sqLiteDatabase);

    }

    public void adduser(UserModel user) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USERS_EMAIL, user.getEmail());
        values.put(COLUMN_USERS_NAME, user.getName());
        values.put(COLUMN_USERS_PASSWORD, user.getPassword());


        database.insert(DATABASE_TABLE, null, values);

//        database.close();
    }

    public Boolean checkUserAlreadyExists(String email) {


        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_USERS_ID};

        String selection = COLUMN_USERS_EMAIL + "=?";

        String[] selectionArgs = {email};

        Cursor cursor = database.query(
                DATABASE_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );

        int cursorCount = cursor.getCount();
        cursor.close();

        return cursorCount > 0;

    }

    public boolean checkUserExists(String email, String password) {

        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_USERS_ID};

        String selection = COLUMN_USERS_EMAIL + "=?" + " AND " + COLUMN_USERS_PASSWORD + "=?";

        String[] selectionArgs = {email, password};

        Cursor cursor = database.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();

        return count > 0;

    }

    @SuppressLint("Range")
    public UserModel fetchData(String id) {

        Log.d(getClass().getSimpleName(), "id detail" + id);

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USERS_ID, COLUMN_USERS_NAME, COLUMN_USERS_EMAIL, COLUMN_USERS_PASSWORD};

        String selection = COLUMN_USERS_ID + "=?";
        String[] selectionArgs = {id};

        Cursor cursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            UserModel newUser = new UserModel();
            newUser.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USERS_NAME)));
            newUser.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USERS_EMAIL)));
            newUser.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USERS_PASSWORD)));

            Log.d(getClass().getSimpleName(), "fetchData: " + newUser.toString());
            return newUser;
        }

        return null;
    }

    @SuppressLint("Range")
    public List<UserModel> getAllUser() {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USERS_ID, COLUMN_USERS_NAME, COLUMN_USERS_EMAIL, COLUMN_USERS_PASSWORD};

        Cursor cursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null, null);

        ArrayList<UserModel> userList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                userList.add(new UserModel(
                        cursor.getString(cursor.getColumnIndex(COLUMN_USERS_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_USERS_PASSWORD)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_USERS_NAME)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USERS_ID)))

                ));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userList;
    }

    public void updateDatabase (int id,String name) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues =  new ContentValues();

        contentValues.put("name",name);

       database.update(DATABASE_TABLE,contentValues, COLUMN_USERS_ID +" = ?",new String[]{String.valueOf(id)});

    //   database.close();

    }

    public void deleteDatabase (int id){

        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(DATABASE_TABLE, COLUMN_USERS_ID +" = ?",new String[]{String.valueOf(id)});

//        database.close();

    }

}












