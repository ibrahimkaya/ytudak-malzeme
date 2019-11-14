package com.example.malzeme.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.malzeme.R;

import java.util.ArrayList;

public class ZimmetleOnayActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetle_onay);
        Intent intent = getIntent();
        list = findViewById(R.id.secilen_listview);
        ArrayList<String> array = intent.getStringArrayListExtra("onay_array");
        for(int i=0; i<array.size(); i++){
            System.out.println(array.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
        list.setAdapter(adapter);
    }

    void onaybutonOnClick(View v) {
        Toast.makeText(this,"butona bastin!!",Toast.LENGTH_SHORT);
    }
}
