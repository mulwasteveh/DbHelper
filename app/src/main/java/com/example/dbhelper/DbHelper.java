package com.example.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final String DATABASE_NAME="database.db";
    private static final int DATABASE_VERSION=1;

    private static final String TABLE_STUDENT="_student";

    private static final  String KEY_ID="id";
    private static final  String KEY_NAME="name";
    private static final  String KEY_EMAIL="email";
    private static final  String KEY_MOBILE="mobile";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String Query_Table=" CREATE TABLE " +TABLE_STUDENT+ "("
                +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +KEY_NAME+ " TEXT, " +KEY_EMAIL+ " TEXT, " +KEY_MOBILE+ " TEXT);";
        db.execSQL(Query_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_STUDENT);
        onCreate(db);

    }

    public long insertStudent(String name, String email, String mobile) {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_MOBILE,mobile);
        return db.insert(TABLE_STUDENT,null,values);
    }

    public String getData() {
        db=this.getReadableDatabase();
        String[] columns=new String[] {KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor=db.query(TABLE_STUDENT,columns,null,null,null,null,null);

        int iId= cursor.getColumnIndex(KEY_ID);
        int iName= cursor.getColumnIndex(KEY_NAME);
        int iEmail= cursor.getColumnIndex(KEY_EMAIL);
        int iMobile= cursor.getColumnIndex(KEY_MOBILE);
        String result="";

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            result=result+
                    "Id: "+cursor.getString(iId)+ "\n"+
                    "Name: " +cursor.getString(iName)+"\n"+
                    "Email: " +cursor.getString(iEmail)+ "\n"+
                    "Mobile: "+cursor.getString(iMobile)+ "\n\n";
        }
        db.close();
        return result;
    }

    public void deleteStudent(long l) {
        db=this.getWritableDatabase();
        db.delete(TABLE_STUDENT,KEY_ID+"="+l,null);
    }

    public void updateStudent(long l, String name, String email, String mobile) {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,name);
        values.put(KEY_EMAIL,email);
        values.put(KEY_MOBILE,mobile);
        db.update(TABLE_STUDENT,values,KEY_ID+"="+l,null);
        db.close();
    }

    public String getName(long l1) {
        db=this.getReadableDatabase();
        String[] columns=new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor=db.query(TABLE_STUDENT,columns,KEY_ID+"="+l1,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            String name=cursor.getString(1);
            return name;
        }
        return null;
    }

    public String getEmail(long l1) {
        db=this.getReadableDatabase();
        String[] columns=new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor=db.query(TABLE_STUDENT,columns,KEY_ID+"="+l1,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            String name=cursor.getString(2);
            return name;
        }
        return null;
    }

    public String getMobile(long l1) {
        db=this.getReadableDatabase();
        String[] columns=new String[]{KEY_ID,KEY_NAME,KEY_EMAIL,KEY_MOBILE};
        Cursor cursor=db.query(TABLE_STUDENT,columns,KEY_ID+"="+l1,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            String name=cursor.getString(3);
            return name;
        }
        return null;
    }
}

