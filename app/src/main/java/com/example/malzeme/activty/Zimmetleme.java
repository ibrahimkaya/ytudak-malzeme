package com.example.malzeme.activty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.malzeme.R;
import com.example.malzeme.activty.ZimmetleOnayActivity;
import com.example.malzeme.adapter.MyRecyclerAdapter;
import com.example.malzeme.model.MalzemeModel;

import java.util.ArrayList;

public class Zimmetleme extends AppCompatActivity implements java.io.Serializable{
    MyRecyclerAdapter adapter_zimmetle;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetleme);

        Intent intent = getIntent();
        String malzemeciadi = intent.getStringExtra("malzemecino");

        TextView myTextView = findViewById(R.id.spinner_malzeme);

        myTextView.setText(malzemeciadi);

        Spinner kategoriSpinner = (Spinner) findViewById(R.id.kategori);

        ArrayAdapter<CharSequence> adapterKategori = ArrayAdapter.createFromResource(this, R.array.kategori, android.R.layout.simple_spinner_item);

        adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        kategoriSpinner.setAdapter(adapterKategori);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(this, MalzemeModel.getData());
        recyclerView.setAdapter(myRecyclerAdapter);


    }

    public void itemSearchOnclick(View view) {
    }

    public void onayOnclick(View view) {
        ArrayList<MyRecyclerAdapter.SecilenItemler> array = adapter_zimmetle.getSecilen();
        ArrayList<String> rows = new ArrayList<>();

        if(array.size() == 0){
            Toast.makeText(this,"Seçim Yapmadınız!",Toast.LENGTH_SHORT).show();
        }else {
            for(int i=0; i<array.size(); i++){
                System.out.println("EKLENEN:-----  "+array.get(i).mno +" - "+array.get(i).model);
                rows.add(array.get(i).mno +" - "+array.get(i).model);
            }
            Intent intent = new Intent(this, ZimmetleOnayActivity.class);
            intent.putExtra("onay_array", rows);
            startActivity(intent);
        }
    }
}

