package com.example.diy2210.easycounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class SQLHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "counterdb";
    private static final String TABLE_COUNTERS = "counterdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_TIME = "time";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_VALUE = "value";
    private SQLiteDatabase sqLiteDatabase;

    public SQLHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_COUNTERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TIME + " TEXT,"
                + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_VALUE + " TEXT"+ ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
        // Drop older table if exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTERS);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails(String time, String title, String description, String value) {
        //Get the Data Repository in write mode
        sqLiteDatabase = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_TIME, time);
        cValues.put(KEY_TITLE, title);
        cValues.put(KEY_DESCRIPTION, description);
        cValues.put(KEY_VALUE, value);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = sqLiteDatabase.insert(TABLE_COUNTERS,null, cValues);
        sqLiteDatabase.close();
    }

    // Get User Details
    public ArrayList<HashMap<String, String>> GetCounters(){
        sqLiteDatabase = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> counterList = new ArrayList<>();
        String query = "SELECT time, title, description, value FROM "+ TABLE_COUNTERS;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> counter = new HashMap<>();
            counter.put("time",cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            counter.put("title",cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            counter.put("description",cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
            counter.put("value",cursor.getString(cursor.getColumnIndex(KEY_VALUE)));
            counterList.add(counter);
        }
        return  counterList;
    }

    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        sqLiteDatabase = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> counterList = new ArrayList<>();
        String query = "SELECT name, age, city FROM "+ TABLE_COUNTERS;
        Cursor cursor = sqLiteDatabase.query(TABLE_COUNTERS, new String[]{KEY_TIME, KEY_TITLE, KEY_VALUE}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> counter = new HashMap<>();
            counter.put("name",cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            counter.put("age",cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            counter.put("city",cursor.getString(cursor.getColumnIndex(KEY_VALUE)));
            counterList.add(counter);
        }
        return  counterList;
    }

    // Delete User Details
    public void DEleteCounter(int counterId){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_COUNTERS, KEY_ID+" = ?",new String[]{String.valueOf(counterId)});
        sqLiteDatabase.close();
    }

    // Update User Details
    public int UpdateCounterDetails(String time, String tittle, String description, String value, int id){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TIME, time);
        contentValues.put(KEY_TITLE, tittle);
        contentValues.put(KEY_DESCRIPTION, description);
        contentValues.put(KEY_VALUE, value);
        int count = sqLiteDatabase.update(TABLE_COUNTERS, contentValues, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}
