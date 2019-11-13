package com.example.malzeme;

import java.util.ArrayList;

//model kısmı ve constructor
public class MalzemeModel {
        public int mno;

        public String model;


    public MalzemeModel(int mno, String model) {
        this.mno = mno;

        this.model = model;

    }

    public static ArrayList<MalzemeModel> getData(){
        ArrayList<MalzemeModel> malzemeList = new ArrayList<MalzemeModel>();
        //datayı internetten alıp burada basıcam
        //alttakiler örnek eklemeler
        MalzemeModel temp = new MalzemeModel(11,"kazma");
        malzemeList.add(temp);

        MalzemeModel temp2 = new MalzemeModel(13,"krampon");
        malzemeList.add(temp2);

        return  malzemeList;
    }
}
