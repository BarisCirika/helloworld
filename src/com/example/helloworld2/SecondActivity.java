package com.example.helloworld2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity{
	
	private ApplicationName applicationName;
	private TextView txtApplicationName;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtApplicationName = (TextView)findViewById(R.id.txtApplicationName);
        applicationName = (ApplicationName) getIntent().getSerializableExtra("appname");
        txtApplicationName.setText(applicationName.getApplicationName());
    }
}
