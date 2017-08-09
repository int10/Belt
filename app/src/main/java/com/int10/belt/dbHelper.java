package com.int10.belt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;


/**
 * Created by int10 on 2017/8/9.
 */

public class dbHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="/sdcard/belt.db";
    private final static int DATABASE_VERSION=1;
    private final static String TABLE_NAME="belt";
    public final static String FIELD_ID="_id";
    public final static String FIELD_SERIAL="serial";
	public final static String FIELD_MODEL="model";
	public final static String FIELD_MODELCODE="modelcode";
	public final static String FIELD_ENGINEERCODE="engineercode";
	public final static String FIELD_AKOKNUM="akoknum";
	public final static String FIELD_FACTORYNUM="factorynum";
	public final static String FIELD_POS="pos";

    public dbHelper(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
//        String sql="Create table "+TABLE_NAME+"("+FIELD_ID+" integer primary key autoincrement,"
//                +FIELD_TITLE+" text );";
//        Log.v("dbtest",sql);
//        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql=" DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public Cursor select()
    {
		//   String[] filter = {"22"};
		SQLiteDatabase db=this.getReadableDatabase();
		//Cursor cursor=db.query(TABLE_NAME, null,FIELD_TITLE+"=?", filter, null, null,  " _id desc");
        Cursor cursor=db.query(TABLE_NAME, new String[]{"_id", FIELD_SERIAL },null, null, FIELD_SERIAL, null,  FIELD_ID);
        return cursor;
    }
    public Cursor SelectModelInSerial(String serial)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, new String[]{"_id", FIELD_MODEL },FIELD_SERIAL + "='" + serial + "'", null, null, null,  "_id");
		return cursor;
	}

	public Cursor SelectBeltInModel(String serial, String model)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		String filter = FIELD_SERIAL + "='" + serial + "' and " + FIELD_MODEL + "='" + model +"'";
		Cursor cursor = db.query(TABLE_NAME, null, filter, null, null, null,  "_id");
		return cursor;
	}

    public long insert(String Title)
    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        ContentValues cv=new ContentValues();
//        cv.put(FIELD_TITLE, Title);
//        long row=db.insert(TABLE_NAME, null, cv);
//        return row;
        return 0;
    }

    public void delete(int id)
    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        String where=FIELD_ID+"=?";
//        String[] whereValue={Integer.toString(id)};
//        db.delete(TABLE_NAME, where, whereValue);
    }

    public void update(int id,String Title)
    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        String where=FIELD_ID+"=?";
//        String[] whereValue={Integer.toString(id)};
//        ContentValues cv=new ContentValues();
//        cv.put(FIELD_TITLE, Title);
//        db.update(TABLE_NAME, cv, where, whereValue);
    }
}
