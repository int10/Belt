package com.int10.belt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by int10 on 2017/8/9.
 */

public class ModelActivity extends AppCompatActivity {
	String serial = null;

	private dbHelper db;
	private Cursor ModelCursor;
	private ListView ModelListView;
	private EditText SerialEditText;
	private int _id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.model);

		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		serial = bundle.getString("serial");

		ModelListView = (ListView)findViewById(R.id.LvModel);
		SerialEditText = (EditText)findViewById(R.id.EdSerial);

		SerialEditText.setText(serial);
		db = new dbHelper(ModelActivity.this);
		ModelCursor = db.SelectModelInSerial(serial);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this
				, R.layout.modelitem, ModelCursor,
				new String[]{dbHelper.FIELD_MODEL},
				new int[]{R.id.Modelitem});
		ModelListView.setAdapter(adapter);

		ModelListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Log.v("int10","Click"+arg2);
				ModelCursor.moveToPosition(arg2);
				_id = ModelCursor.getInt(0);
				Log.v("int10","id=" + _id);
				SerialEditText.setText(ModelCursor.getString(1));
				Intent intent = new Intent();
				intent.setClass(ModelActivity.this, BeltActivity.class);
				intent.putExtra("serial", serial);
				intent.putExtra("model", ModelCursor.getString(1));
				startActivity( intent);
			}
		});



	}

}
