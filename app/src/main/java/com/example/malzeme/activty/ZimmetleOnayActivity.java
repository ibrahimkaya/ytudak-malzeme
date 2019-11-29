package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.malzeme.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ZimmetleOnayActivity extends AppCompatActivity {
    ListView list;
    EditText alankisi;
    Spinner malzemeci_spinner;
    Button btn_onay;
    Toast hataToast;
    TextView onayDurum;
    public static JSONArray jsonArray = new JSONArray();
    ArrayList<String> array = new ArrayList<>(); // intent'ten gelen array
    ArrayList<Integer> malzemeno = new ArrayList<>(); // secilenlerin malzeme numaraları bu listenin icinde

    ArrayList<String> succesRespons = new ArrayList<>();

    static String postUrl = "http://ufukglr.com/ytudak/zimmetle.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetle_onay);

        onayDurum = findViewById(R.id.responsTv);
        malzemeci_spinner = findViewById(R.id.malzemeci_spinner);
        hataToast = Toast.makeText(this, "Alan kişi adını giriniz!", Toast.LENGTH_SHORT);
        btn_onay = findViewById(R.id.buton_zimmetle_onay);
        alankisi = findViewById(R.id.alankisi_edt);
        Intent intent = getIntent();
        list = findViewById(R.id.secilen_listview);
        array = intent.getStringArrayListExtra("onay_array");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
        list.setAdapter(adapter);

        btn_onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(alankisi.getText().toString())) {
                    hataToast.show();
                } else {
                    jsonArray = new JSONArray(new ArrayList<String>()); //json array içeriğini temizlemek için
                    //for (int no : malzemeno) {
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put("mno", malzemeno);
                        obj.put("zimmetalan", alankisi.getText().toString());
                        obj.put("verenmalzemecino", malzemeci_spinner.getSelectedItem().toString());
                        jsonArray.put(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    postZimmetlenen();

                }
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        for(int i=0; i<array.size(); i++){
            String parca[] = array.get(i).split(" ");
            malzemeno.add(Integer.parseInt(parca[0]));
        }
    }

    public void postZimmetlenen(){
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.d("postJson"," ilk "+jsonArray.toString());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, postUrl, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //response handling

                afterRespons(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //error handling
                Log.d("postJson"," error " + error.toString());
            }
        });

        queue.add(jsonArrayRequest);
    }

    protected void afterRespons(JSONArray res)  {
        JSONObject temp;
        //donen verileri arraya istediğim formatta alıyorum
        try {
            for (int i = 0; i < res.length(); i++) {
                temp = res.getJSONObject(i);
                if(temp.get("status").equals("success"))
                succesRespons.add("istek başarılı  " + temp.get("mno").toString()+"  " + temp.get("verenmalzemecino").toString()+"  " + temp.get("zimmetalan").toString());
            }
        }catch (JSONException e){
            Log.d("postJson","after Respons err"+ e.toString());
        }

        //yeni bilgilerimle beraber farklı layout ile listwiewi tekrar oluşuturuyorum
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_list_item_for_response, android.R.id.text1, succesRespons);
        list.setAdapter(adapter);

        //ui güncelle
        afterResponsUi();

    }

    protected void afterResponsUi (){
        //durum cubuğunu görünür ve diğer viewleri uidan kaldırmak
        onayDurum.setVisibility(View.VISIBLE);
        btn_onay.setVisibility(View.GONE);
        alankisi.setVisibility(View.GONE);
        malzemeci_spinner.setVisibility(View.GONE);
    }


}

