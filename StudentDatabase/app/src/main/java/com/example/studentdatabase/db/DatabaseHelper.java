package com.example.studentdatabase.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.example.studentdatabase.model.StudentModel;
import com.example.studentdatabase.model.SubjectModel;
import com.example.studentdatabase.model.TeacherModel;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "School.db";

    public static final String DB_ID = "id";

    public static final String COLUMN_STUDENT_NAME = "Name";

    public static final String COLUMN_FATHER_NAME = "Father_Name";

    public static final String COLUMN_STUDENT_AGE = "Age";

    public static final String COLUMN_CONTACT_NO = "Contact";


    public static final String DBS_TABLE = "DBS_TABLE";


    public static final String COLUMN_SUBJECT = "subject";


    public static final String DBS_RELATIONAL_TABLE = "DBS_RELATIONAL_TABLE";

    public static final String STU_ID = "stu_id";

    public static final String SUB_ID = "sub_id";


    public static final String DB_TABLE = "DB_TABLE";


//teacher table//

    public static final String TEACHER_TABLE = "teach_table";

    public static final String COLUMN_NAME = "name";

    public static final String COLUMN_EMAIL = "email";

    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PHONE_NO = "phone_no";



    private static final String SQL_CREATES_ENTRY1 =
            "CREATE TABLE " + DB_TABLE + "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_STUDENT_NAME + " TEXT," +
                    COLUMN_FATHER_NAME + " TEXT," + COLUMN_STUDENT_AGE + " INTEGER," + COLUMN_CONTACT_NO + " INTEGER)";

    private static final String SQL_CREATES_ENTRY2 =
            "CREATE TABLE " + DBS_TABLE + "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SUBJECT + " TEXT)";

    private static final String SQL_CREATES_ENTRY3 =
            "CREATE TABLE " + DBS_RELATIONAL_TABLE + "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + STU_ID + " INTEGER," + SUB_ID + " INTEGER)";


    private static final String SQL_CREATES_ENTRY4 =
            "CREATE TABLE " + TEACHER_TABLE + "(" + DB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT ," + COLUMN_EMAIL + " TEXT ," + COLUMN_PASSWORD + " TEXT, " + COLUMN_PHONE_NO + " INTEGER)";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 5);
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

        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DBS_RELATIONAL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TEACHER_TABLE);

        onCreate(db);

    }


    public void addStudent(StudentModel model) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_STUDENT_NAME, model.getName());
        values.put(COLUMN_FATHER_NAME, model.getFname());
        values.put(COLUMN_STUDENT_AGE, model.getAge());
        values.put(COLUMN_CONTACT_NO, model.getContact());


        db.insert(DB_TABLE, null, values);

//        Long v;
//        v = db.insert(DB_TABLE,null,values);
//        Log.d(getClass().getSimpleName(), "addStudent: " + v.toString());
//
//       return  v> 0;
    }

    public void addSubject(SubjectModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECT, model.getSubject());

        db.insert(DBS_TABLE, null, values);

    }

    public void addTeacher(TeacherModel model){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME,model.getName());
        values.put(COLUMN_EMAIL,model.getEmail());
        values.put(COLUMN_PASSWORD,model.getPassword());
        values.put(COLUMN_PHONE_NO,model.getPhone_no());

        db.insert(TEACHER_TABLE,null,values);


    }

    public void updateDatabase (String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("password", password);

        db.update(TEACHER_TABLE, contentValues, COLUMN_EMAIL + " = ?", new String[]{email});

    }


    public boolean checkTeacherExists(String email){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DB_ID};

        String selection = COLUMN_EMAIL + "=?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(TEACHER_TABLE,columns,selection,selectionArgs,null,null,null,null);

        int cursorCount = cursor.getCount();
        cursor.close();

        return cursorCount > 0;


    }

    public boolean loginTeacher(String name,String password){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DB_ID};

        String selection = COLUMN_NAME + "=?" + " AND " + COLUMN_PASSWORD + "=?";

        String[] selectionArgs = {name,password};

        Cursor cursor = db.query(TEACHER_TABLE,columns,selection,selectionArgs,null,null,null);

        int count = cursor.getCount();

        cursor.close();

        return count > 0;

    }


    public void allotSubject(int subId, int stuId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUB_ID, subId);
        values.put(STU_ID, stuId);


        db.insert(DBS_RELATIONAL_TABLE, null, values);
    }


    public boolean checkAllotSubject(int stu_id, int sub_id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DB_ID};

        String selection = STU_ID + "=?" + " AND " + SUB_ID + "=?";

        String[] selectionArgs = {String.valueOf(stu_id), String.valueOf(sub_id)};

        Cursor cursor = db.query(DBS_RELATIONAL_TABLE,columns,selection,selectionArgs,null,null,null,null);

        int count = cursor.getCount();

        cursor.close();

        return count>0;


    }




    @SuppressLint("Range")
    public ArrayList<StudentModel> fetchData() {

        ArrayList<StudentModel> dataList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DB_ID, COLUMN_STUDENT_NAME, COLUMN_FATHER_NAME, COLUMN_STUDENT_AGE, COLUMN_CONTACT_NO};

        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                dataList.add(new StudentModel(
                        cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_FATHER_NAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_AGE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NO)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(DB_ID)))));

            } while (cursor.moveToNext());

        }

        cursor.close();

        return dataList;

    }

    @SuppressLint("Range")
    public ArrayList<SubjectModel> fetchSubject() {

        ArrayList<SubjectModel> subList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DB_ID, COLUMN_SUBJECT};

        Cursor cursor = db.query(DBS_TABLE, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                subList.add(new SubjectModel(Integer.parseInt(cursor.getString(0)), cursor.getString(cursor.getColumnIndex(COLUMN_SUBJECT))));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return subList;

    }


    @SuppressLint("Range")
    public StudentModel getData(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {DB_ID, COLUMN_STUDENT_NAME, COLUMN_STUDENT_AGE, COLUMN_FATHER_NAME, COLUMN_CONTACT_NO};
        String selection = DB_ID + "=?";
        String[] selectionArgs = {id};
        Cursor cursor = db.query(DB_TABLE, columns, selection, selectionArgs, null, null, null, null);

        if (cursor.moveToFirst()) {
            StudentModel student = new StudentModel();
            student.setName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)));
            student.setAge(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_AGE)));
            student.setFname(cursor.getString(cursor.getColumnIndex(COLUMN_FATHER_NAME)));
            student.setContact(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT_NO)));

            return student;

        }

        return null;


    }
//
//    public void deleteDatabase (int id) {
//
//        SQLiteDatabase database = this.getWritableDatabase();
//
//        database.delete(DBS_RELATIONAL_TABLE,DB_ID +" =?", new String[]{String.valueOf(id)});
//
//
//
//    }



    @SuppressLint("Range")
    public List<Integer> allot_Subject(int stuid) {

        List<Integer> subList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {DB_ID,SUB_ID};

        String selection = STU_ID + "=?";

        String[] selectionArgs = {String.valueOf(stuid)};

        Cursor cursor = db.query(DBS_RELATIONAL_TABLE,columns,selection,selectionArgs,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                subList.add(Integer.valueOf(cursor.getString(1)));


            } while (cursor.moveToNext());
        }

        if (subList.size() > 0){
            return subList;
        } else {

            return null;
        }

    }

}









