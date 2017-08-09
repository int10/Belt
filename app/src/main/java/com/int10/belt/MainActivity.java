package com.int10.belt;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private int _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SerialEditText = (EditText)findViewById(R.id.Filter);
        SerialListView = (ListView)findViewById(R.id.LvSerial);

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
                Log.v("dbtest","Click"+arg2);
                SerialCursor.moveToPosition(arg2);
                _id = SerialCursor.getInt(0);
                Log.v("dbtest","id=" + _id);
                SerialEditText.setText(SerialCursor.getString(1));
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ModelActivity.class);
                intent.putExtra("serial", SerialCursor.getString(1));
                startActivity( intent);
            }
        });


//        myListView.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int arg2, long arg3) {
//                // TODO Auto-generated method stub
//                SQLiteCursor sc=(SQLiteCursor)arg0.getSelectedItem();
//                _id=sc.getInt(0);
//                myEditText.setText(sc.getString(1));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//                // TODO Auto-generated method stub
//
//            }
//        });

    }
}
