package com.int10.belt;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by int10 on 2017/8/24.
 */

public class EngineModelActivity extends AppCompatActivity {

    private dbHelper db;
    private Cursor ModelCursor;
    private ListView ModelListView;
    private String serial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineitem);
        ModelListView = (ListView) findViewById(R.id.lvEngineModel);
        ModelListView.requestFocus();

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        serial = bundle.getString("serial");

        db = new dbHelper(EngineModelActivity.this);
        ModelCursor = db.SelectEngineModelInSerial(serial);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this
                , R.layout.engineitem, ModelCursor,
                new String[]{dbHelper.FIELD_MODEL, dbHelper.FIELD_ENGINEERCODE, dbHelper.FIELD_POS},
                new int[]{R.id.tvEngineModel, R.id.tvEngineCode, R.id.tvEnginePos});
        ModelListView.setAdapter(adapter);
    }
}
