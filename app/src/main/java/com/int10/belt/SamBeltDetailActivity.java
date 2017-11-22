package com.int10.belt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by int10 on 2017/11/22.
 */

public class SamBeltDetailActivity extends AppCompatActivity {
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
		setContentView(R.layout.activity_sambeltdetail);

		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		serial = bundle.getString("serial");
		model = bundle.getString("model");
		Log.v("int10", serial);
		Log.v("int10", model);

		BeltListView = (ListView)findViewById(R.id.lvSamBeltDetail);
		SerialTextView = (TextView)findViewById(R.id.tvSamBeltSerial);
		ModelTextView = (TextView)findViewById(R.id.tvSamBeltModel);
		SerialTextView.setText(serial);
		ModelTextView.setText(model);
		db = new dbHelper(SamBeltDetailActivity.this);

		BeltCursor = db.SelectSamBeltInModel(serial, model);
		if(serial.equals("")) {
			BeltCursor.moveToPosition(0);
			serial = BeltCursor.getString(BeltCursor.getColumnIndex(dbHelper.FIELD_SERIAL));
			SerialTextView.setText(serial);
		}

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this
				, R.layout.sambeltdetail, BeltCursor,
				new String[]{dbHelper.FIELD_ENGINEERCODE, dbHelper.FIELD_OEM_CODE, dbHelper.FIELD_POS, dbHelper.FIELD_SAM_MODEL, dbHelper.FIELD_COTY, dbHelper.FIELD_REMARK},
				new int[]{R.id.tvSamBeltEngineerCode, R.id.tvSamBeltOemCode, R.id.tvSamBeltPos, R.id.tvSamBeltSamModel, R.id.tvSamBeltCoty, R.id.tvSamBeltRemark});
		BeltListView.setAdapter(adapter);
	}


}
