package com.example.malzeme;

import java.util.ArrayList;

//model kısmı ve constructor
public class MalzemeModel {
        public int mno = 0;
        public String not;
        public String model = "bos";


    public MalzemeModel(int mno, String model,String not) {
        this.mno = mno;
        this.model = model;
        this.not = not;

    }

    public static ArrayList<MalzemeModel> getData(){
        ArrayList<MalzemeModel> malzemeList = new ArrayList<MalzemeModel>();
        //datayı internetten alıp burada basıcam
        //alttakiler örnek eklemeler
        MalzemeModel temp = new MalzemeModel(1,"kazma","dasdasdasd");
        malzemeList.add(temp);

        MalzemeModel temp2 = new MalzemeModel(2,"krampon","dasdasdasd");
        malzemeList.add(temp2);

        MalzemeModel temp3 = new MalzemeModel(3,"petzlEkspres","dasdasdasd");
        malzemeList.add(temp3);

        MalzemeModel temp4 = new MalzemeModel(4,"deutercanta","dasdasdasd");
        malzemeList.add(temp4);

        MalzemeModel temp5 = new MalzemeModel(5,"lsakmdlkasdklşasdklasdkasd","dasdasdasd");
        malzemeList.add(temp5);

        MalzemeModel temp6 = new MalzemeModel(6,"bd cam","dasdasdasd");;
        malzemeList.add(temp6);

        MalzemeModel temp7 = new MalzemeModel(7,"cam 0.3 ","dasdasdasd");
        malzemeList.add(temp7);





        return  malzemeList;
    }
}
