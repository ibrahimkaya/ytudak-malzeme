package com.example.malzeme.activty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.malzeme.R;
import com.example.malzeme.activty.ZimmetleOnayActivity;
import com.example.malzeme.adapter.MyRecyclerAdapter;
import com.example.malzeme.model.MalzemeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Zimmetleme extends AppCompatActivity implements java.io.Serializable{
    MyRecyclerAdapter adapter_zimmetle;
    RecyclerView recyclerView;


    static MalzemeModel gelenMmodel ;
    public static ArrayList<MalzemeModel> gelenArray;

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

        MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(this, MalzemeModel.getData());
        recyclerView.setAdapter(myRecyclerAdapter);





    }

    public void fetch(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ufukglr.com/ytudak/malzemeler.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());
                        Log.d("oramakomaburamako","try1");
                        // Process the JSON
                        try{
                            Log.d("oramakomaburamako","try");
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject malzeme = response.getJSONObject(i);
                                if(malzeme.getString("aktif").equals("1")){
                                    String tempMno = malzeme.getString("mno");
                                    String tempModel = malzeme.getString("model");
                                    Log.d("oramakomaburamako",tempModel);
                                    Log.d("oramakomaburamako",tempMno);

                                   // gelenMmodel = new MalzemeModel(tempMno,tempModel,"");
                                    //gelenArray.add(new MalzemeModel(tempMno,tempModel,""));
                                }
                            }
                          //  gelenMmodel.setData(gelenArray);


                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }
        );

        // Add JsonArrayRequest to the RequestQueue
        queue.add(jsonArrayRequest);
        Log.d("oramakomaburamako","fe2");

    }


    public void itemSearchOnclick(View view) {
        fetch();
        Log.d("oramakomaburamako","fe");


    }

    public void onayOnclick(View view) {
        ArrayList<MyRecyclerAdapter.SecilenItemler> array = adapter_zimmetle.getSecilen();
        ArrayList<String> rows = new ArrayList<>();

        if(array.size() == 0){
            Toast.makeText(this,"Seçim Yapmadınız!",Toast.LENGTH_SHORT).show();
        }else {
            for(int i=0; i<array.size(); i++){
                System.out.println("EKLENEN:-----  "+array.get(i).mno +" - "+array.get(i).model);
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



