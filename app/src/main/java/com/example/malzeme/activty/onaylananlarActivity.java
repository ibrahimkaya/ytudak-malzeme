package com.example.malzeme.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.malzeme.R;

import org.json.JSONArray;
import org.json.JSONException;

public class onaylananlarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onaylananlar);

        JSONArray onaylanan = new JSONArray();
        Intent intent = getIntent();
        try {
             onaylanan = new JSONArray (intent.getStringExtra("oynaylananExtra"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("postJson","onaylanan   " + onaylanan.toString());
    }

    //listwiew oluştur
    //listwievde her succes olan satırı görüntüle
    //mümkünse satırların renklerini değiştir
    //back navigationda parentini mainactiviy yap
}
