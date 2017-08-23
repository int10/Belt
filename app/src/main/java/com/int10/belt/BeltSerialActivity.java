package com.int10.belt;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by int10 on 2017/8/23.
 */

public class BeltSerialActivity extends AppCompatActivity {

    private dbHelper db;
    private Cursor SerialCursor;
    private ListView SerialListView;
    private EditText SerialEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beltserial);
        SerialEditText = (EditText)findViewById(R.id.Filter);
        SerialListView = (ListView)findViewById(R.id.LvSerial);
        SerialListView.requestFocus();

//        if(copyAssetsToFilesystem("belt.db", "belt.db")){
//            Log.v("int10", "copy success");
//        } else {
//            Log.v("int10", "copy fail");
//        }
        db = new dbHelper(BeltSerialActivity.this);
        SerialCursor = db.select();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this
                , R.layout.serial, SerialCursor,
                new String[]{dbHelper.FIELD_SERIAL},
                new int[]{R.id.Serial});
        SerialListView.setAdapter(adapter);

        SerialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                SerialCursor.moveToPosition(arg2);
                SerialEditText.setText(SerialCursor.getString(1));
                Intent intent = new Intent();
                intent.setClass(BeltSerialActivity.this, ModelActivity.class);
                intent.putExtra("serial", SerialCursor.getString(1));
                startActivity( intent);
            }
        });
    }

}
