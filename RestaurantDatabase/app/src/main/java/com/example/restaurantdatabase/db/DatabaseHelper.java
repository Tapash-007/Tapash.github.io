package com.example.restaurantdatabase.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.restaurantdatabase.model.ManagerModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Restaurant.db";

    public static final String DB_ID = "id";

    public static final String DB_TABLE = "DB_TABLE";

    public static final String COLUMN_CHEF_NAME = "Name";

    public static final String COLUMN_CHEF_AGE = "Age";

    public static final String COLUMN_COUNTRY_NAME = "Country";

    public static final String COLUMN_CONTACT_NO = "contact";

    public static final String DB_FOOD_TABLE = "Food_table";

    public static final String COLUMN_FOOD = "food_items";

    public static final String DBS_RELATIONAL_TABLE = "relationalTable";

    public static final String CHEF_ID = "chef_id";

    public static final String FOOD_ID = "food_id";

    //manager
    public static final String MANAGER_TABLE = "manager_table";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PHONE_NO = "phone_no";


    public static final int DATABASE_VERSION = 1;


    private static final String SQL_CREATES_ENTRY1 =
            "CREATE TABLE " + DB_TABLE + "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CHEF_NAME + " TEXT, " +
                    COLUMN_CHEF_AGE + " INTEGER, " + COLUMN_COUNTRY_NAME + " TEXT," + COLUMN_CONTACT_NO + " INTEGER)";

    private static final String SQL_CREATES_ENTRY2 =
            "CREATE TABLE " + DB_FOOD_TABLE + "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FOOD +
                    " TEXT)";

    private static final String SQL_CREATES_ENTRY3 =
            "CREATE TABLE " + MANAGER_TABLE + "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME +
                    " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_PASSWORD + " TEXT, " + COLUMN_PHONE_NO +
                    " TEXT)";

    private static final String SQL_CREATES_ENTRY4 =
            "CREATE TABLE " + DBS_RELATIONAL_TABLE + "(" +DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CHEF_ID + " INTEGER, " + FOOD_ID + " INTEGER)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATES_ENTRY1);
        db.execSQL(SQL_CREATES_ENTRY2);
        db.execSQL(SQL_CREATES_ENTRY3);
        db.execSQL(SQL_CREATES_ENTRY4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DB_TABLE);

        onCreate(db);

    }

    public void addManager(ManagerModel model) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, model.getName());
        values.put(COLUMN_EMAIL, model.getEmail());
        values.put(COLUMN_PASSWORD, model.getPassword());
        values.put(COLUMN_PHONE_NO, model.getPhone_no());

        db.insert(MANAGER_TABLE, null, values);

    }

    public boolean checkManagerExist(String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] column = {DB_ID};

        String selection = COLUMN_EMAIL + "=?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(MANAGER_TABLE, column, selection, selectionArgs, null, null, null);

        int cursorCount = cursor.getCount();
        cursor.close();

        return cursorCount > 0;

    }


    public boolean loginManager(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DB_ID};

        String selection = COLUMN_EMAIL + "=?" + " AND " + COLUMN_PASSWORD + "=?";

        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(MANAGER_TABLE, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();

        cursor.close();

        return count > 0;

    }
}
