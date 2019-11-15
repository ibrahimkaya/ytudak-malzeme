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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.malzeme.R;
//import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

public class ZimmetleOnayActivity extends AppCompatActivity {
    ListView list;
    EditText alankisi;
    Spinner malzemeci_spinner;
    Button btn_onay;
    Toast hataToast;
    JSONArray jsonArray = new JSONArray();
    ArrayList<String> array = new ArrayList<>(); // intent'ten gelen array
    ArrayList<Integer> malzemeno = new ArrayList<>(); // secilenlerin malzeme numaraları bu listenin icinde
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetle_onay);
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
                    for (int no : malzemeno) {
                        //json array oluşturulcak array içinde her bir malzeme için: [malzemeno, zimmet alan kişinin adı, veren malzemeci] json arrayi yapılarak gönderilecek
                    //   JsonObject obj = new JsonObject();
                    //   obj.addProperty("mno", String.valueOf(no));
                    //   obj.addProperty("zimmetalan", alankisi.getText().toString());
                    //   obj.addProperty("verenmalzemecino", malzemeci_spinner.getSelectedItem().toString());
                    //   jsonArray.put(obj);
                    }
                    Log.e("jsonarray", jsonArray.toString());
                    //burada sunucuya request gonderilecek
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
}
