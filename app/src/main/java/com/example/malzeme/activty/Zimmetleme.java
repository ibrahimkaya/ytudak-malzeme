package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import com.example.malzeme.adapter.MyRecyclerAdapter;
import com.example.malzeme.model.MalzemeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Zimmetleme extends AppCompatActivity implements java.io.Serializable{
    MyRecyclerAdapter adapter_zimmetle;
    RecyclerView recyclerView;
    public static ArrayList<MalzemeModel> gelenArray = new ArrayList<>();

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetleme);


        Spinner kategoriSpinner = (Spinner) findViewById(R.id.kategori);

        ArrayAdapter<CharSequence> adapterKategori = ArrayAdapter.createFromResource(this, R.array.kategori, android.R.layout.simple_spinner_item);

        adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        kategoriSpinner.setAdapter(adapterKategori);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



    }

    public void fetch(){

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ufukglr.com/ytudak/malzemeler.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("oramakomaburamako","try1");
                        try{
                            gelenArray.clear();
                            Log.d("oramakomaburamako","try");
                            for(int i=0;i<response.length();i++){
                                JSONObject malzeme = response.getJSONObject(i);
                                if(malzeme.getString("aktif").equals("1")){
                                    String tempMno = malzeme.getString("mno");
                                    String tempModel = malzeme.getString("model");
                                    Log.d("oramakomaburamako",tempModel);
                                    Log.d("oramakomaburamako",tempMno);

                                    gelenArray.add(new MalzemeModel(tempMno, tempModel, ""));
                                }
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            Log.d("oramakomaburamako_hata", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("oramakomaburamako_hata", error.getMessage());
                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        queue.add(jsonArrayRequest);

    }


    public void itemSearchOnclick(View view) {
        fetch();
        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(this, gelenArray);
        recyclerView.setAdapter(myRecyclerAdapter);

    }

    public void onayOnclick(View view) {
        ArrayList<MyRecyclerAdapter.SecilenItemler> array = adapter_zimmetle.getSecilen();
        ArrayList<String> rows = new ArrayList<>();

        if(array.size() == 0){
            Toast.makeText(this,"Seçim Yapmadınız!",Toast.LENGTH_SHORT).show();
        }else {
            for(int i=0; i<array.size(); i++){
                rows.add(array.get(i).mno +" "+array.get(i).model);
            }
            Intent intent = new Intent(this, ZimmetleOnayActivity.class);
            intent.putExtra("onay_array", rows);
            startActivity(intent);
        }
    }

    @Override
    public void onRestart(){
        super.onRestart();
        adapter_zimmetle.setSecilen();

    }
}



