package com.example.helloworld2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	private Button btnSendAppName;
	private Button btnGesture;
	private ApplicationName applicationName;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstpage);
        System.out.println("Git hub rapper");
        btnSendAppName = (Button) findViewById(R.id.buttonSend);
        btnGesture = (Button)findViewById(R.id.buttonGesture);
        btnSendAppName.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				applicationName = new ApplicationName();
				Intent intent = new Intent(getApplicationContext(),
						SecondActivity.class);
				intent.putExtra("appname", applicationName);
				startActivity(intent);
			}
		});
        
        btnGesture.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						Gridd.class);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
   
}
