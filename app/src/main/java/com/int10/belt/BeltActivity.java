package com.int10.belt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class BeltActivity extends AppCompatActivity {
    String serial = null;
    String model = null;

    private dbHelper db;
    private Cursor BeltCursor;
    private ListView BeltListView;
    private TextView SerialTextView;
    private TextView ModelTextView;
    private int _id;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belt);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        serial = bundle.getString("serial");
        model = bundle.getString("model");
        Log.v("int10", serial);
        Log.v("int10", model);

        BeltListView = (ListView)findViewById(R.id.LvBelt);
        SerialTextView = (TextView)findViewById(R.id.tvSamBeltSerial);
        ModelTextView = (TextView)findViewById(R.id.tvSamBeltModel);
        SerialTextView.setText(serial);
        ModelTextView.setText(model);
        db = new dbHelper(BeltActivity.this);

        BeltCursor = db.SelectBeltInModel(serial, model);
        if(serial.equals("")) {
            BeltCursor.moveToPosition(0);
            serial = BeltCursor.getString(BeltCursor.getColumnIndex(dbHelper.FIELD_SERIAL));
            SerialTextView.setText(serial);
        }

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this
                , R.layout.beltitem, BeltCursor,
                new String[]{dbHelper.FIELD_MODELCODE, dbHelper.FIELD_ENGINEERCODE, dbHelper.FIELD_AKOKNUM, dbHelper.FIELD_FACTORYNUM, dbHelper.FIELD_POS},
                new int[]{R.id.tvSamBeltEngineerCode, R.id.tvSamBeltOemCode, R.id.tvSamBeltPos, R.id.tvSamBeltSamModel, R.id.TvPos});
        BeltListView.setAdapter(adapter);
    }
}