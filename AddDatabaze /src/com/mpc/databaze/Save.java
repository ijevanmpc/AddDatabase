package com.mpc.databaze;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Save extends Activity {
	EditText mTvyal;
	Button mSave;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTvyal = (EditText) findViewById(R.id.edit);
		final DB db = new DB(getApplicationContext());
		final String TFromDB = db.getData()[0];
		mSave = (Button) findViewById(R.id.save);
		mSave.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String tv = mTvyal.getText().toString();
				if (!tv.equals(TFromDB)) {
					db.addTvyal(mTvyal.getText().toString());
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
