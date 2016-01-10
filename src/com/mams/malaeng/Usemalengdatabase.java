package com.mams.malaeng;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Usemalengdatabase {
	public static final String DATABASE_NAME="enml_db.sqlite";
	public static final String DATABASE_TABLE="enml";
	public static final int DATABASE_VERSION=1;
	private static final String MalaEng = null;
	String engword="Advisor";
	Context ourContext;
	ArrayList<String> list=new ArrayList<String>();
	private Dbhelper ourHelper;
	private SQLiteDatabase ourDatabase;
	//fields
	public static String KEY_ROWID="_id";
	public static String KEY_ENG="english_word";
	public static String KEY_PART="part_of_speech";
	public static String KEY_MAL="malayalam_definition";
	private String[] columns=new String[]{KEY_ENG,KEY_PART,KEY_MAL};
	private static class Dbhelper extends SQLiteOpenHelper{

		public Dbhelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
			onCreate(db);
			
		}
		
	}
	public Usemalengdatabase(Context c){
		ourContext=c;
	}
	public Usemalengdatabase open() throws SQLException{
		ourHelper=new Dbhelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		ourHelper.close();
	}
	public ArrayList<String> getmeaningofeng(String eng){
		if(eng!=null){
			Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ENG + " like ?", new String[]{ eng+"%" }, null, null, null);
			
			while(c.moveToNext()){
				int malint=c.getColumnIndex(KEY_MAL);
				String malamng=c.getString(malint);
				list.add(malamng);
			}	
		}
		
		return list;
		
		
		
	}

}
