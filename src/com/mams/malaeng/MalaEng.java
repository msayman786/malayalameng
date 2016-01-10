package com.mams.malaeng;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Parser;


import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Spinner;

public class MalaEng extends ActionBarActivity {

	
	
	EditText txt;
	Button btn;
	SQLiteDatabase	db;
	String engword="a";
	ListAdapter adapter;
	ListView lv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mala_eng);
		
		txt=(EditText)findViewById(R.id.editText1);
		btn=(Button)findViewById(R.id.button1);
		lv1=(ListView)findViewById(R.id.listView1);
		Typeface customfont1=Typeface.createFromAsset(getAssets(), "Meera.ttf");
		
	
		
		
		
	
	}
	
	public void getvaluefromtxt(View v) {
		if(v.getId()==R.id.button1){
			engword=txt.getText().toString();
			 loadSpinnerData();
		}

	}
	

	
	private void loadSpinnerData() {
 
		List<String> lables = getAllLabels();
		String[] mStringArray = new String[lables.size()];
		mStringArray = lables.toArray(mStringArray);
       ListAdapter dataAdapter = new CustomList(this, mStringArray);
        
        lv1.setAdapter(dataAdapter);
		               
    }
	private List<String> getAllLabels() {
		Usemalengdatabase count=new Usemalengdatabase(this);
		count.open();
        List<String> labels = new ArrayList<String>();
        labels=count.getmeaningofeng(engword);
        return labels;
    
	}

}
