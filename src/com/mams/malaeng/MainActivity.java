package com.mams.malaeng;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	String value;
	String update="01";
	Boolean mastupdate=false;
	private static String  DB_PATHNAME="/data/data/com.mams.malaeng/databases/enml_db.sqlite";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		makethefile();
		openfilelevel();
		if(mastupdate){
			deleteDB();
			copyDBfromassets();
		}
		
		Thread time=new Thread(){
			public void run(){
				try{
					sleep(5000);
				}catch(InterruptedException e){
					
				}finally{
					Intent a=new Intent(MainActivity.this,MalaEng.class);
					startActivity(a);
				}
			}
		};
		time.start();
	}

	private void copyDBfromassets() {
	DataBaseCreator myHelper;
	myHelper=new DataBaseCreator(this);
	try{
		myHelper.createDatabase();
	}catch(IOException e){
		throw new Error("Unable to create database");
	}
	try{
		myHelper.openDatabase();
	}catch(SQLiteException eql){
		throw eql;
		
	}
		
	}

	private void deleteDB() {
		try{
			File f=new File(DB_PATHNAME);
			if(f.exists()){
				f.delete();
			}
		}catch(Exception ex){
			
		}
		
	}

	private void openfilelevel() {
	FileInputStream fis;
	try{
		fis=openFileInput("Update.ss");
		byte[] input=new byte[fis.available()];
		while(fis.read(input)!=-1){
			value=new String(input);
			}
		fis.close();
		if(!value.contentEquals(update)){
			mastupdate=true;
			creatUpdatelevel();
			
		}
	}catch(FileNotFoundException e){
		
	}catch (IOException e) {
		// TODO: handle exception
	}
		
	}

	private void creatUpdatelevel() {
	String FILENAME="Update.ss";
	String JOUR=update;
	try{
		File file=getBaseContext().getFileStreamPath(FILENAME);
		mastupdate=true;
		FileOutputStream fos=openFileOutput(FILENAME, getBaseContext().MODE_PRIVATE);
		fos.write(JOUR.getBytes());
		fos.close();
	}catch(FileNotFoundException e){
		
	}catch (IOException e) {
		// TODO: handle exception
	}
		
	}

	private void makethefile() {
	String FILENAME="Update.ss";
	String JOUR=update;
	try{
		File file=getBaseContext().getFileStreamPath(FILENAME);
		if(file.exists()){
			Toast.makeText(this, "exist", Toast.LENGTH_LONG).show();
		}else{
			mastupdate=true;
			FileOutputStream fos=openFileOutput(FILENAME, getBaseContext().MODE_PRIVATE);
			fos.write(JOUR.getBytes());
			fos.close();
		}
	}catch(FileNotFoundException e){
		
	}catch (IOException e) {
		// TODO: handle exception
	}
		
	}

	
}
