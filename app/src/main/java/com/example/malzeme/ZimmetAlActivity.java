package com.example.malzeme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class ZimmetAlActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Spinner spinner_malzemeci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetal);

        spinner_malzemeci = findViewById(R.id.spinner_malzeme);
        recyclerView = findViewById(R.id.recyclerview_zimmetal);
        recyclerView.setHasFixedSize(true);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.malzemeci, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_malzemeci.setAdapter(adapter);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyRecyclerAdapter_zimmet RecyclerAdapter = new MyRecyclerAdapter_zimmet(this, ZimmetAlModelActivity.getData());
        recyclerView.setAdapter(RecyclerAdapter);

    }

    public void itemSearchOnclick(View view) {
    }

    public void onayOnclick(View view) {

    }
}

