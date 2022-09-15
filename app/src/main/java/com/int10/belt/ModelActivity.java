package com.int10.belt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ModelActivity extends AppCompatActivity {
    String serial = null;

    private dbHelper db;
    private Cursor ModelCursor;
    private ListView ModelListView;
    private EditText SerialEditText;
    private int _id;

    private static final String TAG = "int10";



    SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        serial = bundle.getString("serial");

        ModelListView = (ListView)findViewById(R.id.LvModel);
        SerialEditText = (EditText)findViewById(R.id.EdSamBeltSerial);


        SerialEditText.setText(serial);
        db = new dbHelper(ModelActivity.this);
        if (null != ModelCursor) {
            ModelCursor.close();
        }
        ModelCursor = db.SelectModelInSerial(serial);
        adapter = new SimpleCursorAdapter(this
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