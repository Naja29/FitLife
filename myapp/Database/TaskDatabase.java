package com.project.myapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TaskDatabase extends SQLiteOpenHelper{
        public static final String DATABASE_NAME = "Task";
        public static final String TABLE_NAME = "Tasks";
        public static final String COL_1 = "id";
        public static final String COL_2 = "taskName";
        public static final String COL_3 = "taskDuration";
        public static final String COL_4 = "taskStatus";

        public TaskDatabase (@Nullable Context context) {
            super(context, DATABASE_NAME, null, 1);
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table " + TABLE_NAME +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ "TASKNAME TEXT, " +
                    "TASKDURATION TEXT, TASKSTATUS TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + DATABASE_NAME);
            onCreate(sqLiteDatabase);
        }

        public boolean insertData(String taskName, String taskDuration, String taskStatus){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_2,taskName);
            contentValues.put(COL_3,taskDuration);
            contentValues.put(COL_4,taskStatus);

            long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
            if (result == -1)
                return false;
            else
                return true;
        }

        public Cursor viewData() {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor result = sqLiteDatabase.rawQuery(" select * from " + TABLE_NAME,
                    null);
            return result;
        }
}
