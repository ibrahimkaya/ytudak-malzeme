package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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
import com.example.malzeme.adapter.MyRecyclerAdapter_Zver;
import com.example.malzeme.adapter.MyRecyclerAdapter_zimmetAl;
import com.example.malzeme.model.ZimmetAlModel;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ZimmetAlActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecyclerAdapter_zimmetAl adapter_al;
    public static ArrayList<GelenRequestModel> gelenArray = new ArrayList<>();
    public String base_url = "http://ufukglr.com/ytudak/";

    ProgressBar fetchProgresBar;
    JSONObject postIsimJson;
    JSONArray postIsimJsonArray;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetal);

        recyclerView = findViewById(R.id.recyclerview_zimmetal);
        recyclerView.setHasFixedSize(true);

        fetchProgresBar = findViewById(R.id.fetchSpinner_zimmetver);
        fetchProgresBar.setVisibility(View.GONE);

        editText = findViewById(R.id.edt_arama);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyRecyclerAdapter_zimmetAl RecyclerAdapter = new MyRecyclerAdapter_zimmetAl(this, GelenRequestModel.getData());
        recyclerView.setAdapter(RecyclerAdapter);

    }
    public void fetchItems() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = base_url + "arama.php";

        JsonArrayRequest itemListRequest = new JsonArrayRequest(Request.Method.POST, url, postIsimJsonArray, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.d("onResponstakidurum",response.toString());
                try {
                    gelenArray.clear();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject responseJSONObject = response.getJSONObject(i);

                            String mno = responseJSONObject.getString("mno");
                            String zimmetalan = responseJSONObject.getString("zimmetalan");
                            String kategori = responseJSONObject.getString("kategori");
                            String model = responseJSONObject.getString("model");
                            String zimmet_alinma_tarih = responseJSONObject.getString("zimmet_alinma_tarih");
                            String zimmet_not = responseJSONObject.getString("zimmet_not");
                            String malzemecino = responseJSONObject.getString("malzemecino");

                            gelenArray.add(new GelenRequestModel ( mno, zimmetalan, kategori, model, zimmet_alinma_tarih, zimmet_not, malzemecino ));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                fetchProgresBar.setVisibility(View.GONE);
                //sunucudan cevaplar geldiğinde recycler wiev adapterini bağla
                setMyAdapter();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        Log.d("onResponstakidurum","++request++" + String.valueOf(postIsimJson));
        queue.add(itemListRequest);
    }

    /*
    fetch süresince progres için onu aktivite ediyorum
    kategorilerdeki bilgileri setKategoriJson ile bir json arraya ekliyorum
    bu bilgiler ile request atıyorum
     */
    public void itemSearchOnclick(View view) {
        fetchProgresBar.setVisibility(View.VISIBLE);
        setPostIsimJson(String.valueOf(editText.getText()));
        fetchItems();
    }

    public void setMyAdapter(){
        MyRecyclerAdapter_zimmetAl myRecyclerAdapter_zimmetAl = new MyRecyclerAdapter_zimmetAl(this, gelenArray);
        recyclerView.setAdapter(myRecyclerAdapter_zimmetAl);
    }

    public void onayOnclick(View view) {
        ArrayList<GelenRequestModel> array = adapter_al.getSecilen();
        ArrayList<String> rows = new ArrayList<>();

        if (array.size() == 0) {
            Toast.makeText(this, "Seçim Yapmadınız!", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < array.size(); i++) {
                  rows.add(array.get(i).mno +" " + array.get(i).zimmetalan + " " + array.get(i).kategori
                          + " " +array.get(i).model + " " + array.get(i).zimmet_alinma_tarih + " " + array.get(i).zimmet_not + " " + array.get(i).malzemecino);
            }
            Intent intent = new Intent(this, ZimmetAlOnayActivity.class);
            intent.putExtra("zimmetal_onay", rows);
            startActivity(intent);
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        MyRecyclerAdapter_zimmetAl.clearSecilen();

        gelenArray.clear();


        //for deleting previous configurations
        MyRecyclerAdapter_zimmetAl myRecyclerAdapter_zimmetAl = new MyRecyclerAdapter_zimmetAl(this, gelenArray);
        recyclerView.setAdapter(myRecyclerAdapter_zimmetAl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyRecyclerAdapter_Zver.clearSecilen();
    }


    void setPostIsimJson(String isim){

        postIsimJson = new JSONObject();
        postIsimJsonArray = new JSONArray();
        try {
            //önce json sonra jsonarray içerisine koymamın sebebi atılan istek ile gelen responsun aynı olması istediği için
            postIsimJson.put("isim",isim);
            postIsimJsonArray.put(postIsimJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class GelenRequestModel{
       public String mno ;
       public String zimmetalan ;
       public String kategori ;
       public String model ;
       public String zimmet_alinma_tarih;
       public String zimmet_not ;
       public String malzemecino ;

        public GelenRequestModel( String mno, String zimmetalan, String kategori, String model, String zimmet_alinma_tarih, String zimmet_not, String malzemecino) {
            this.mno = mno;
            this.zimmetalan = zimmetalan;
            this.kategori = kategori;
            this.model = model;
            this.zimmet_alinma_tarih = zimmet_alinma_tarih;
            this.zimmet_not = zimmet_not;
            this.malzemecino = malzemecino;
        }

        public static ArrayList<GelenRequestModel> getData(){
            ArrayList<GelenRequestModel> zimmelal_list = new ArrayList<GelenRequestModel>();
            return  zimmelal_list;
        }
    }

}

