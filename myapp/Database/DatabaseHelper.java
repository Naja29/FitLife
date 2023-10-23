package com.project.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fitLifeDatabase";
    private Context context ;
    public DatabaseHelper(Context context){

    super(context, DATABASE_NAME, null, 1);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users (username TEXT primary key, email TEXT, password TEXT, confirmPassword TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists users");
        onCreate(db);
    }
    public boolean insertData(String username, String email, String password, String confirmPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("username", username);
        contentvalues.put("email", email);
        contentvalues.put("password", password);
        contentvalues.put("confirmPassword", confirmPassword);

        long result = db.insert("users", null, contentvalues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where Username = ?",new String[]{username});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }
    public boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ? ", new String[]{username,password});
        if (cursor.getCount()>0)
            return  true;
        else
            return false;
    }
    public boolean checkUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}




























//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.EditText;
//
//import androidx.annotation.Nullable;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    public static final String DATABASE_NAME = "SignUp";
//    public static final String TABLE_NAME = "Users";
//    public static final String COL_1 = "id";
//    public static final String COL_2 = "Username";
//    public static final String COL_3 = "Email";
//    public static final String COL_4 = "Password";
//    public static final String COL_5 = "ConfirmPassword";
//
//    public DatabaseHelper(@Nullable Context context) {
//        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL("create table " + TABLE_NAME +
//                "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ "USERNAME TEXT, " +
//                "EMAIL TEXT, PASSWORD TEXT, CONFIRMPASSWORD TEXT)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + DATABASE_NAME);
//        onCreate(sqLiteDatabase);
//    }
//
//    public boolean insertData(String Useraname, String Email, String Password, String Confirmpassword){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2,Useraname);
//        contentValues.put(COL_3,Email);
//        contentValues.put(COL_4,Password);
//        contentValues.put(COL_5,Confirmpassword);
//
//        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }
//
//    public Cursor viewData() {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        Cursor result = sqLiteDatabase.rawQuery(" select * from " + TABLE_NAME,
//                null);
//        return result;
//    }
//
//    public Integer deleteData(String id, String s,  String toString, String string){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        return sqLiteDatabase.delete(TABLE_NAME,"ID = ?", new String[]{id});
//    }
//
//    public boolean updateData(String id, String Useraname, String Email, String Password, String Confirmpassword){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,Useraname);
//        contentValues.put(COL_3,Email);
//        contentValues.put(COL_4,Password);
//        contentValues.put(COL_5,Confirmpassword);
//        sqLiteDatabase.update(TABLE_NAME, contentValues,"id = ?",
//                new String[]{id});
//        return true;
//    }
//
//    public long addUser(EditText Useraname, EditText Email, EditText Password, EditText Confirmpassword) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COL_2, String.valueOf(Useraname));
//        values.put(COL_3, String.valueOf(Email));
//        values.put(COL_4, String.valueOf(Password));
//        values.put(COL_5, String.valueOf(Confirmpassword));
//
//        long id = db.insert(TABLE_NAME, null, values);
//        db.close();
//
//        return id;
//    }
//
//    public static class LoginManager {
//        public boolean login(EditText username, EditText password) throws SQLException {
//            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
//            try (Connection conn = DatabaseConnection.getConnection();
//                 PreparedStatement stmt = conn.prepareStatement(query)) {
//                stmt.setString(1, username);
//                stmt.setString(2, password);
//                try (ResultSet rs = stmt.executeQuery()) {
//                    return rs.next(); // return true if there is a match
//                }
//            }
//        }
//    }
//
//}

//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    // Database Name
//    private static final String DATABASE_NAME = "userdatabase";
//    // Database Version
//    private static final int DATABASE_VERSION = 1;
//
//    // Table name
//    private static final String TABLE_NAME = "user";
//
//    // Table Columns
//    private static final String KEY_ID = "id";
//    private static final String KEY_USERNAME = "username";
//    private static final String KEY_EMAIL = "email";
//    private static final String KEY_PASSWORD = "password";
//
//    // Create Table Query
//    private static final String CREATE_TABLE = "CREATE TABLE " + UserMaster.Users.TABLE_NAME +
//            "(" + UserMaster.Users.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//            UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT," + UserMaster.Users.COLUMN_NAME_EMAIL + " TEXT," +
//            UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT);";
//
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // Create table
//        db.execSQL(CREATE_TABLE);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        // Create tables again
//        onCreate(db);
//    }
//}
