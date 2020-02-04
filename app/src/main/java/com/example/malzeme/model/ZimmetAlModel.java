package com.example.malzeme.model;

import java.util.ArrayList;


public class ZimmetAlModel {
    public String zimmetAlan;
    public String teslimEden;
    public String zimmetVerenMalzemeci;
    public String zimmetAlanMalzemeci;
    public String malzemeNo;
    public String malzemeKategori;
    public String malzemeModel;
    public String zimmetAlınanTarih;
    public String zimmetVerilmeTarihi;
    public String zimmetVerilmeNotu;
    public String zimmetAlınmaNotu;

    public ZimmetAlModel(String zimmetAlan, String teslimEden, String zimmetVerenMalzemeci, String zimmetAlanMalzemeci, String malzemeNo, String malzemeKategori, String malzemeModel, String zimmetAlınanTarih, String zimmetVerilmeTarihi, String zimmetVerilmeNotu, String zimmetAlınmaNotu) {
        this.zimmetAlan = zimmetAlan;
        this.teslimEden = teslimEden;
        this.zimmetVerenMalzemeci = zimmetVerenMalzemeci;
        this.zimmetAlanMalzemeci = zimmetAlanMalzemeci;
        this.malzemeNo = malzemeNo;
        this.malzemeKategori = malzemeKategori;
        this.malzemeModel = malzemeModel;
        this.zimmetAlınanTarih = zimmetAlınanTarih;
        this.zimmetVerilmeTarihi = zimmetVerilmeTarihi;
        this.zimmetVerilmeNotu = zimmetVerilmeNotu;
        this.zimmetAlınmaNotu = zimmetAlınmaNotu;
    }


    public static ArrayList<ZimmetAlModel> getData(){
        ArrayList<ZimmetAlModel> zimmelal_list = new ArrayList<ZimmetAlModel>();

        return  zimmelal_list;
    }
}
