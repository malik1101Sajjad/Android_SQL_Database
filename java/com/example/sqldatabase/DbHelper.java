package com.example.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

    Context context;

    public static final int VERSION           =1;
    public static final String DATABASE_NAME  ="Data";

    public static final String TABLE_NAME     ="Library";
    public static final String ID             ="id";
    public static final String TITLE          ="Book_title";
    public static final String AUTHOR_NAME    ="Author_name";
    public static final String PAGE           ="Pages_book";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String CREATE_TABLE   =
            String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT)",TABLE_NAME,ID,TITLE,AUTHOR_NAME,PAGE);

    public static DbHelper instance;


    public static DbHelper getInstance(Context context){
        if (instance == null){
            instance = new DbHelper(context);
        }
        return instance;
    }
    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase Db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            Db.execSQL(DROP_TABLE);
            Db.execSQL(CREATE_TABLE);
        }
    }
    public boolean addData(String title,String author_name,String page){
        SQLiteDatabase database= getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(TITLE,title);
        cv.put(AUTHOR_NAME,author_name);
        cv.put(PAGE,page);
        long effectedRows=0;
        try {
            effectedRows=database.insert(TABLE_NAME,null,cv);
        }catch (Exception exception){
            return false;
        }
        return effectedRows==1;

    }

    boolean updateData(String Id, String title, String author_name,String page){
        SQLiteDatabase database= getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(TITLE,title);
        cv.put(AUTHOR_NAME,author_name);
        cv.put(PAGE,page);
        long effectedRows=database.update(TABLE_NAME,cv,"ID=?",new String[]{Id});
        if (effectedRows == 1){
            return true;
        }
        else {
            return false;
        }

    }
    Cursor readAllData(){
        SQLiteDatabase database= this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor= null;
        if (database != null){
            cursor= database.rawQuery(query,null);
        }
        return cursor;
    }
    public boolean deleteData(String Id){
        SQLiteDatabase database= getWritableDatabase();
        long effectedRows=0;
        try {
            effectedRows=database.delete(TABLE_NAME,"Id=?",new String[]{String.valueOf(Id)});
        }catch (Exception exception){
            return false;
        }
        return true;
    }
}
