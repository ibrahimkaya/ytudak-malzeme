package com.example.malzeme.activty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.malzeme.R;

import java.util.ArrayList;

public class ZimmetleOnayActivity extends AppCompatActivity {
    ListView list;
    Button btn_onay;

    ArrayList<String> array = new ArrayList<>();
    ArrayList<Integer> malzemeno = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zimmetle_onay);
        btn_onay = findViewById(R.id.buton_zimmetle_onay);
        Intent intent = getIntent();
        list = findViewById(R.id.secilen_listview);
        array = intent.getStringArrayListExtra("onay_array");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array);
        list.setAdapter(adapter);


        btn_onay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int x : malzemeno) {
                    Log.e("sayilar", String.valueOf(malzemeno));
                    //json array oluşturulcak
                    //array içinde her bir malzeme için:
                    //[malzemeno, zimmet alan kişinin adı, veren malzemeci]
                    //json arrayi yapılarak gönderilecek
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
