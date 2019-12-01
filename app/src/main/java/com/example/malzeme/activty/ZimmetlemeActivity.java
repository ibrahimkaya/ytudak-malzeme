package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
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
import com.example.malzeme.adapter.MyRecyclerAdapter_Zver;
import com.example.malzeme.model.ZimmetVerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ZimmetlemeActivity extends AppCompatActivity implements Serializable, AdapterView.OnItemSelectedListener {
    private MyRecyclerAdapter_Zver adapter_zimmetle;
    private RecyclerView recyclerView;
    ProgressBar fetchProgresBar;
    Spinner kategoriSpinner;

    public static ArrayList<ZimmetVerModel> gelenArray = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    private int kategoriNo = 0;
    JSONObject kNoJson;
    JSONArray kNoJsonArray;
    String kategoriName;

    public String base_url = "http://ufukglr.com/ytudak/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetleme);

        kategoriSpinner = findViewById(R.id.kategori);
        ArrayAdapter<CharSequence> adapterKategori = ArrayAdapter.createFromResource(this, R.array.kategori, android.R.layout.simple_spinner_item);
        adapterKategori.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kategoriSpinner.setAdapter(adapterKategori);

        kategoriSpinner.setOnItemSelectedListener(this);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        fetchProgresBar = findViewById(R.id.fetchSpinner);
        fetchProgresBar.setVisibility(View.GONE);


    }

    public void fetchItems() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = base_url + "malzemeler.php";

        JsonArrayRequest itemListRequest = new JsonArrayRequest(Request.Method.POST, url, kNoJsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    gelenArray.clear();
                    Log.d("gelen", String.valueOf(response));
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject malzeme = response.getJSONObject(i);

                        if (malzeme.getString("aktif").equals("1")) {
                            String tempMno = malzeme.getString("mno");
                            String tempModel = malzeme.getString("model");
                            String tempNot = malzeme.getString("malzeme_not");

                            gelenArray.add(new ZimmetVerModel(tempMno, tempModel,tempNot));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("request Error", e.getMessage());
                }
                fetchProgresBar.setVisibility(View.GONE);
                //sunucudan cevaplar geldiğinde recycler wiev adapterini bağla
                setMyAdapter();
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
        queue.add(itemListRequest);
    }

    /*
    fetch süresince progres için onu aktivite ediyorum
    kategorilerdeki bilgileri setKategoriJson ile bir json arraya ekliyorum
    bu bilgiler ile request atıyorum
     */
    public void itemSearchOnclick(View view) {
        fetchProgresBar.setVisibility(View.VISIBLE);
        setKategoriJson();
        fetchItems();
    }

    public void setMyAdapter(){
        MyRecyclerAdapter_Zver myRecyclerAdapterZver = new MyRecyclerAdapter_Zver(this, gelenArray);
        recyclerView.setAdapter(myRecyclerAdapterZver);
    }

    public void onayOnclick(View view) {
        ArrayList<MyRecyclerAdapter_Zver.SecilenItemler> array = adapter_zimmetle.getSecilen();
        ArrayList<String> rows = new ArrayList<>();

        if (array.size() == 0) {
            Toast.makeText(this, "Seçim Yapmadınız!", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < array.size(); i++) {
                rows.add(array.get(i).mno + " " +" " + kategoriName + " " + array.get(i).model + " " +array.get(i).not);
            }
            Intent intent = new Intent(this, ZimmetleOnayActivity.class);
            intent.putExtra("onay_array", rows);
            startActivity(intent);
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        MyRecyclerAdapter_Zver.clearSecilen();

        //for deleting previous configurations
        MyRecyclerAdapter_Zver myRecyclerAdapterZver = new MyRecyclerAdapter_Zver(this, gelenArray);
        recyclerView.setAdapter(myRecyclerAdapterZver);
        //alınan malzemeleri tekrar listelememesi için listeyi güncelle
        fetchItems();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyRecyclerAdapter_Zver.clearSecilen();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("kategorispinner", String.valueOf(position));
        kategoriNo = position;
        kategoriName = kategoriSpinner.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    /*
    önce seçilen kategorimi alıyorum bir json objesine dönüştürüyorum
    daha sonra bu objeyi bir jsonArray içine koyuyorum
     */
    void setKategoriJson(){
        kNoJson = new JSONObject();
        kNoJsonArray = new JSONArray();
        try {
            kNoJson.put("kategori",kategoriNo);
            kNoJsonArray.put(kNoJson);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}


