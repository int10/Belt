package com.int10.belt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class EngineModelActivity extends AppCompatActivity {

    private dbHelper db;
    private Cursor ModelCursor;
    private ListView ModelListView;
    private String serial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engine_model);

        ModelListView = (ListView) findViewById(R.id.lvEngineModel);
        ModelListView.requestFocus();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        serial = bundle.getString("serial");

        db = new dbHelper(EngineModelActivity.this);
        ModelCursor = db.SelectEngineModelInSerial(serial);
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(this
                , R.layout.engineitem, ModelCursor,
                new String[]{dbHelper.FIELD_MODEL, dbHelper.FIELD_ENGINEERCODE, dbHelper.FIELD_POS},
                new int[]{R.id.tvEngineModel, R.id.tvEngineCode, R.id.tvEnginePos});
        ModelListView.setAdapter(adapter);
    }
}