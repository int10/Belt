package com.int10.belt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class EngineSerialActivity extends AppCompatActivity {
    private dbHelper db;
    private Cursor SerialCursor;
    private ListView SerialListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engine_serial);

        SerialListView = (ListView)findViewById(R.id.lvEngineSerial);
        SerialListView.requestFocus();

        db = new dbHelper(EngineSerialActivity.this);
        SerialCursor = db.SelectEngineSerial();
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(this
                , R.layout.engineserialitem, SerialCursor,
                new String[]{dbHelper.FIELD_SERIAL},
                new int[]{R.id.tvEngineSerialItem});
        SerialListView.setAdapter(adapter);

        SerialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                SerialCursor.moveToPosition(arg2);
                Intent intent = new Intent();
                intent.setClass(EngineSerialActivity.this, EngineModelActivity.class);
                intent.putExtra("serial", SerialCursor.getString(1));
                startActivity( intent);
            }
        });
    }
}