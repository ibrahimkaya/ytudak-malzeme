package com.example.malzeme.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.malzeme.R;

import java.util.ArrayList;

public class ZimmetleOnayActivity extends AppCompatActivity {
    ListView list;
    Button btn_onay;

    ArrayList<String> array;

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
                ArrayList<Integer> malzemeno = new ArrayList<>();

            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        ArrayList<Integer> malzemeno = new ArrayList<>();

        for(int i=0; i<array.size(); i++){
            String parca[] = array.get(i).split(" ");
            malzemeno.add(Integer.parseInt(parca[0]));
        }
    }


}
