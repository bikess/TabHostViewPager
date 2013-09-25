package com.tabhost;

import com.example.tabhostviewpager.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class T2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabcontent_view);
		((TextView)findViewById(R.id.text_show)).setText("222222");
	}
	
}
