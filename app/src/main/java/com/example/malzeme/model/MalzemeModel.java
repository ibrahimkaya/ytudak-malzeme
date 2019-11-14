package com.example.malzeme.model;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.malzeme.activty.Zimmetleme;

import java.util.ArrayList;

//model kısmı ve constructor
public class MalzemeModel {
        public String mno ;
        public String not;
        public String model = "bos";
       static ArrayList<MalzemeModel> malzemeList = new ArrayList<MalzemeModel>();

    public MalzemeModel(String mno, String model,String not) {
        this.mno = mno;
        this.model = model;
        this.not = not;

    }

    public static ArrayList<MalzemeModel> getData(){

        //datayı internetten alıp burada basıcam
        //alttakiler örnek eklemeler


        return  malzemeList;
    }

    public  void setData(ArrayList<MalzemeModel> list){
        this.malzemeList = list;

    }


}
