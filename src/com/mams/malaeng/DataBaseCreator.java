package com.mams.malaeng;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseCreator extends SQLiteOpenHelper {
	
	private static String DB_PATH="/data/data/com.mams.malaeng/databases/";
	private static String DB_NAME="enml_db.sqlite";
	private static int DB_VERSION=1;
	public static String DB_TABLE="mytable";
	private SQLiteDatabase mydatabase;
	private final Context myContext;
	
	
	
	
	public DataBaseCreator(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.myContext=context;
	}
	public void createDatabase() throws IOException{
		boolean dbExist=checkDatabase();
		if (dbExist){
		Log.i("tag", "dbExists"+dbExist);	
		}else{
			Log.i("tag", "dbnotExists"+dbExist);
			this.getReadableDatabase();
			copydatabase();
		}
			
	}
	private boolean checkDatabase() {
		SQLiteDatabase checkDB=null;
		String myPath=DB_PATH+DB_NAME;
		try{
		checkDB=SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);}
		catch(SQLiteException e){
		}
		if(checkDB!=null){
			checkDB.close();
			
		}
		return checkDB!=null?true:false;
	}
	private void copydatabase() throws IOException {
		InputStream myInput=myContext.getAssets().open(DB_NAME);
		String outFileName=DB_PATH+DB_NAME;
		OutputStream myOutput=new FileOutputStream(outFileName);
		byte[] buffer=new byte[1024];
		int length;
		while((length=myInput.read(buffer))>0){
		myOutput.write(buffer,0,length);	
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
		
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void openDatabase() throws SQLiteException {
		String myPath=DB_PATH+DB_NAME;
		mydatabase=SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		
	}

}
