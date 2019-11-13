package com.example.malzeme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.malzemeciSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.malzemeci, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id){
        Log.d("bu",parent.getItemAtPosition(pos).toString());
    }

    public void onNothingSelected(AdapterView<?> parent){
        Log.d("bu2", "secilmedi");
    }


    public void zimmetOnClick(View view) {
        Intent intent = new Intent(this, Zimmetleme.class);

        Spinner mySpinner = (Spinner) findViewById(R.id.malzemeciSpinner);
        String malzemecino = mySpinner.getSelectedItem().toString();

        intent.putExtra("malzemecino", malzemecino);
        startActivity(intent);


    }
}
