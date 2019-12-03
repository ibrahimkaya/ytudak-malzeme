package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.malzeme.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ZimmetAlOnayActivity extends AppCompatActivity {

    ListView list;
    Spinner malzemeci_spinner;
    Button btn_onay;
    ArrayList<String> array = new ArrayList<>(); // intent'ten gelen arrayOnayList
    ArrayList<Integer> malzemeno = new ArrayList<>(); // secilenlerin malzeme numaraları bu listenin icinde
    JSONArray jsonArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmet_al_onay);

        malzemeci_spinner = findViewById(R.id.zimmetal_spinner);
        btn_onay = findViewById(R.id.zimmetal_buton_onay);
        list = findViewById(R.id.zimmetal_secilen_listview);

        Intent intent = getIntent();
        array = intent.getStringArrayListExtra("zimmet_al_onay_array");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
        list.setAdapter(adapter);

        btn_onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonArray = new JSONArray(new ArrayList<String>()); //json arrayOnayList içeriğini temizlemek için

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                // request icin json arrayOnayList olusturuyorum
                for (int mno : malzemeno) {
                    JsonObject obj = new JsonObject();
                    obj.addProperty("mno", String.valueOf(mno));
                    obj.addProperty("malzemeci", malzemeci_spinner.getSelectedItem().toString());
                    obj.addProperty("tarih", formatter.format(calendar.getTime()));
                    jsonArray.put(obj);
                }
                //burada sunucuya request gonderilecek
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        for (int i = 0; i < array.size(); i++) {
            String parca[] = array.get(i).split(" ");
            malzemeno.add(Integer.parseInt(parca[0]));
        }
    }
}
