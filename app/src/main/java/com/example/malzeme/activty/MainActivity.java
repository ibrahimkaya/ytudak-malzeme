package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.malzeme.R;

public class MainActivity extends AppCompatActivity {
    Button zimmetal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zimmetal = findViewById(R.id.btn_teslimal);
        zimmetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZimmetAlActivity.class);
                startActivity(intent);
            }
        });
    }

    public void zimmetOnClick(View view) {
        Intent intent = new Intent(this, ZimmetlemeActivity.class);
        startActivity(intent);
    }
}
