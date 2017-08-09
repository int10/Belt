package com.int10.belt;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    private dbHelper db;
    private Cursor SerialCursor;
    private ListView SerialListView;
    private EditText SerialEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SerialEditText = (EditText)findViewById(R.id.Filter);
        SerialListView = (ListView)findViewById(R.id.LvSerial);
        SerialListView.requestFocus();

        db = new dbHelper(MainActivity.this);
        SerialCursor = db.select();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this
                , R.layout.serial, SerialCursor,
                new String[]{dbHelper.FIELD_SERIAL},
                new int[]{R.id.Serial});
        SerialListView.setAdapter(adapter);

        SerialListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                SerialCursor.moveToPosition(arg2);
                SerialEditText.setText(SerialCursor.getString(1));
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ModelActivity.class);
                intent.putExtra("serial", SerialCursor.getString(1));
                startActivity( intent);
            }
        });
    }

}
