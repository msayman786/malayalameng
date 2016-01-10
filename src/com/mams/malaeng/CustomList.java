package com.mams.malaeng;
import com.mams.malaeng.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomList  extends ArrayAdapter<String>{

	public CustomList(Context context, String[] meaning) {
		super(context,R.layout.custom_list,meaning);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater aymanInfalator=LayoutInflater.from(getContext());
		View customView=aymanInfalator.inflate(R.layout.custom_list, parent,false);
		String singleword=getItem(position);
		TextView tv_entoml=(TextView)customView.findViewById(R.id.textView1);
		RobotoBoldFontHelper.applyFont(getContext(), tv_entoml);
		tv_entoml.setText(singleword);
		return customView;
		
	}
 

}