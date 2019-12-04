package com.example.malzeme.model;

import java.util.ArrayList;

//model kısmı ve constructor
public class ZimmetVerModel {
        public String mno ;
        public String not;
        public String model;
        public String kategori;
        static ArrayList<ZimmetVerModel> malzemeList = new ArrayList<ZimmetVerModel>();

    public ZimmetVerModel(String mno, String model,String kategori, String not) {
        this.mno = mno;
        this.model = model;
        this.not = not;
        this.kategori = kategori;
    }

    public static ArrayList<ZimmetVerModel> getData(){
        return  malzemeList;
    }

    public  void setData(ArrayList<ZimmetVerModel> list){
        malzemeList = list;
    }
}
