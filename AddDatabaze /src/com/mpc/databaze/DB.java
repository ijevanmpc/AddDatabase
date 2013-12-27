package com.mpc.databaze;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DB extends SQLiteOpenHelper {

	// ----------------Database----------------------
	private static final String DATABASE_NAME = "tvyalneri_db.db";
	private static final int DATABASE_VERSION = 1;

	// ----------------Table info---------------------
	public static final String TVYALNERI_TABLE = "tvyalner_tb";

	private static final String tvyali_ID = "ID";
	public static final String name = "name";
	public static SQLiteDatabase dbRead, dbWrite;

	private static final String QUERY_CREATE_USERS_TABLE = "CREATE TABLE "
			+ TVYALNERI_TABLE + "( " + tvyali_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + name
			+ " TEXT NOT NULL" + ")";

	public DB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		dbWrite = this.getWritableDatabase();
        dbRead = this.getReadableDatabase();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL(QUERY_CREATE_USERS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TVYALNERI_TABLE);
		onCreate(db);
	}
	
	 public String[] getData() {
	        String ReturnData;
	        String query = "SELECT " + tvyali_ID + " FROM " + TVYALNERI_TABLE;
	        Cursor cursor = dbRead.rawQuery(query, null);
	        int i = 0;
	        if(cursor.getCount() <= 0) {
	            String[] DataArray = new String[1];
	            DataArray[0] = "";
	            return DataArray;
	        }

	        String[] DataArray = new String[cursor.getCount()];
	        while (cursor.moveToNext()) {
	            ReturnData = cursor.getString(0).toString();
	            DataArray[i] = ReturnData;
	            i++;
	        }
	        return DataArray;
	    }

	    public long addTvyal(String name) {
	        ContentValues values = new ContentValues();
	        values.put(name, name);
	        return dbWrite.insert(TVYALNERI_TABLE, null, values);
	    }
	    
	    public int updateTvyal(String name) {
	        ContentValues values = new ContentValues();
	        values.put(name, name);
	       
	        return dbWrite.update(TVYALNERI_TABLE, values, tvyali_ID + " = ?",
	                new String[] { String.valueOf("1") });
	    }
	    
	    public void addOrEditNick(String name) {
	    	if(getData().length == 1) {
	    		addTvyal(name);
	    	} else {
	    		updateTvyal(name);
	    	}
	    }
	    
	    

	}
	

