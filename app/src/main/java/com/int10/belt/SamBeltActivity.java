package com.int10.belt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SamBeltActivity extends AppCompatActivity {
    private dbHelper db;
    private Cursor SerialCursor;
    private ListView SerialListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sam_belt);

        SerialListView = (ListView)findViewById(R.id.lvSamBeltSerial);
        db = new dbHelper(SamBeltActivity.this);
        SerialCursor = db.SelectSamBeltSerial();
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(this
                , R.layout.sambeltserialitem, SerialCursor,
                new String[]{dbHelper.FIELD_SERIAL},
                new int[]{R.id.SamBeltSerialItem});
        SerialListView.setAdapter(adapter);

        SerialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                SerialCursor.moveToPosition(arg2);
                Intent intent = new Intent();
                intent.setClass(SamBeltActivity.this, SamBeltModelActivity.class);
                intent.putExtra("serial", SerialCursor.getString(1));
                startActivity( intent);
            }
        });
    }
}