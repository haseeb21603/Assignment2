package com.example.haseeb.assignment;



        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.Build;
        import android.support.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.O)
public class DBHandler extends SQLiteOpenHelper {
//    LocalDate localDate = LocalDate.now();
  //  DayOfWeek dayOfWeek = localDate.getDayOfWeek();
    String day = "Monday";
    //String day = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    public static final String DATABASE_NAME = "Timetable.db";
    public static final String TABLE_NAME = "time_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Day";
    public static final String COL_3 = "Starttime";
    public static final String COL_4 = "Endingtime";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (Name TEXT,Day TEXT,Starttime TEXT,Endingtime TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name, String Day,String Starttime,String Endingtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Day);
        contentValues.put(COL_3,Starttime);
        contentValues.put(COL_4,Endingtime);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+"where Day="+day ,null);
        return res;
    }


    public boolean updateData(String Name,String Day,String Starttime,String Endingtime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Day);
        contentValues.put(COL_3,Starttime);
        contentValues.put(COL_4,Endingtime);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { Name });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
