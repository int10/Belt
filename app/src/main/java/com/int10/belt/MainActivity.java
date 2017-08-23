package com.int10.belt;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnBelt;
    private Button btnEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(copyAssetsToFilesystem("belt.db", "belt.db")){
            Log.v("int10", "copy success");
        } else {
            Log.v("int10", "copy fail");
        }

        btnBelt = (Button)findViewById(R.id.btnBeltEntrance);
        btnEngine = (Button)findViewById(R.id.btnEngineEntrance);

        btnBelt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BeltSerialActivity.class);
                startActivity( intent);
            }
        });

        btnEngine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EngineSerialActivity.class);
                startActivity( intent);
            }
        });


    }

    private boolean copyAssetsToFilesystem(String assetsSrc, String des){
        des = getApplicationInfo().dataDir + "/databases/" + des;
        Log.i("testDb", "Copy "+assetsSrc+" to "+des);

        InputStream istream = null;
        OutputStream ostream = null;
        try{
            Context context  = getApplication();
            AssetManager am = context.getAssets();
            istream = am.open(assetsSrc);
            ostream = new FileOutputStream(des);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = istream.read(buffer))>0){
                ostream.write(buffer, 0, length);
            }
            istream.close();
            ostream.close();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                if(istream!=null)
                    istream.close();
                if(ostream!=null)
                    ostream.close();
            }
            catch(Exception ee){
                ee.printStackTrace();
            }
            return false;
        }
        return true;
    }

}
