package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.malzeme.R;
import com.example.malzeme.adapter.MyRecyclerAdapter_zimmetAl;
import com.example.malzeme.model.ZimmetAlModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ZimmetAlActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerAdapter_zimmetAl adapter_al;
    public static ArrayList<ZimmetAlModel> gelenArray = new ArrayList<>();
    public String base_url = "http://ufukglr.com/ytudak/";

    ProgressBar fetchProgresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetal);

        recyclerView = findViewById(R.id.recyclerview_zimmetal);
        recyclerView.setHasFixedSize(true);

        fetchProgresBar = findViewById(R.id.fetchSpinner);
        fetchProgresBar.setVisibility(View.GONE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyRecyclerAdapter_zimmetAl RecyclerAdapter = new MyRecyclerAdapter_zimmetAl(this, ZimmetAlModel.getData());
        recyclerView.setAdapter(RecyclerAdapter);

    }

    public void fetchItems() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = base_url + "zimmetal.php"; //server tarafi henuz yapilmadi

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    gelenArray.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject malzeme = response.getJSONObject(i);

                        if (malzeme.getString("aktif").equals("1")) {
                            String tempMno = malzeme.getString("mno");
                            String tempModel = malzeme.getString("model");

                            gelenArray.add(new ZimmetAlModel(tempMno, tempModel, ""));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("request Error", e.getMessage());
                }
                fetchProgresBar.setVisibility(View.GONE);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("request Error", error.getMessage());
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        queue.add(jsonArrayRequest);
    }
    public void itemSearchOnclick(View view) {
        fetchProgresBar.setVisibility(View.VISIBLE);
        MyRecyclerAdapter_zimmetAl myRecyclerAdapter_zimmetAl = new MyRecyclerAdapter_zimmetAl(this, gelenArray);
        recyclerView.setAdapter(myRecyclerAdapter_zimmetAl);
        fetchItems();
    }

    public void onayOnclick(View view) {
        ArrayList<MyRecyclerAdapter_zimmetAl.ZimmetALSecilenItemler> array = adapter_al.getZimmetAlinacakItemler();
        ArrayList<String> rows = new ArrayList<>();
        if (array.size() == 0) {
            Toast.makeText(this, "Seçim Yapmadınız!", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < array.size(); i++) {
                rows.add(array.get(i).mno + " " + array.get(i).alan_kisi + " " + array.get(i).alindigi_tarih);
                Log.e("arrayOnayList", rows.get(i));
            }
            Intent intent = new Intent(this, ZimmetAlOnayActivity.class);
            intent.putExtra("zimmet_al_onay_array", rows);
            Log.e("zimmetalonayarray.size", String.valueOf(rows.size()));
            startActivity(intent);
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        adapter_al.setZimmetAlinacakItemler();

        //for deleting previous configurations
        MyRecyclerAdapter_zimmetAl myRecyclerAdapter = new MyRecyclerAdapter_zimmetAl(this, gelenArray);
        recyclerView.setAdapter(myRecyclerAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter_al.setZimmetAlinacakItemler();

    }


}

