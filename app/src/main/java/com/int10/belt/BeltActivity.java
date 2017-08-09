package com.int10.belt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by int10 on 2017/8/9.
 */

public class BeltActivity extends AppCompatActivity {
	String serial = null;
	String model = null;

	private dbHelper db;
	private Cursor BeltCursor;
	private ListView BeltListView;
	private TextView SerialTextView;
	private TextView ModelTextView;
	private int _id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.belt);

		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		serial = bundle.getString("serial");
		model = bundle.getString("model");
		Log.v("int10", serial);
		Log.v("int10", model);

		BeltListView = (ListView)findViewById(R.id.LvBelt);
		SerialTextView = (TextView)findViewById(R.id.TvBeltSerial);
		ModelTextView = (TextView)findViewById(R.id.TvBeltModel);
		SerialTextView.setText(serial);
		ModelTextView.setText(model);
		db = new dbHelper(BeltActivity.this);
		BeltCursor = db.SelectBeltInModel(serial, model);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this
				, R.layout.beltitem, BeltCursor,
				new String[]{dbHelper.FIELD_MODELCODE, dbHelper.FIELD_ENGINEERCODE, dbHelper.FIELD_AKOKNUM, dbHelper.FIELD_FACTORYNUM, dbHelper.FIELD_POS},
				new int[]{R.id.TvModelCode, R.id.TvEngineerCode, R.id.TvAkokNum, R.id.TvFactoryNum, R.id.TvPos});
		BeltListView.setAdapter(adapter);
	}
}