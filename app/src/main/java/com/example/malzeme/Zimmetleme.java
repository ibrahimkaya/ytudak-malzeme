package com.example.malzeme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Zimmetleme extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetleme);

        Intent intent = getIntent();
        String malzemeciadi = intent.getStringExtra("malzemecino");

        TextView myTextView = findViewById(R.id.textview);

        myTextView.setText(malzemeciadi);

        Spinner kategoriSpinner = (Spinner) findViewById(R.id.kategori);

        ArrayAdapter<CharSequence> adapterKategori = ArrayAdapter.createFromResource(this, R.array.kategori, android.R.layout.simple_spinner_item);

        adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        kategoriSpinner.setAdapter(adapterKategori);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(this, MalzemeModel.getData());
        recyclerView.setAdapter(myRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    public void itemSearchOnclick(View view) {
    }

    public void onayOnclick(View view) {
    }
}

