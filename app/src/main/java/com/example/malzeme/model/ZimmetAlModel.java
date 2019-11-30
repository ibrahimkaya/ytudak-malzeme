package com.example.malzeme.model;

import java.util.ArrayList;


public class ZimmetAlModel {
    public String teslim_edilen;
    public String malzeme_no;
    public String teslim_tarihi;


    public ZimmetAlModel(String teslimedilen, String teslimtarihi, String mno) {
        this.teslim_edilen = teslimedilen;
        this.teslim_tarihi = teslimtarihi;
        this.malzeme_no = mno;
    }

    public static ArrayList<ZimmetAlModel> getData(){
        ArrayList<ZimmetAlModel> zimmelal_list = new ArrayList<ZimmetAlModel>();
        //datayı internetten alıp burada basıcam
        //alttakiler örnek eklemeler
        zimmelal_list.add(new ZimmetAlModel("alankisi1", "2-11-2019", "44"));
        zimmelal_list.add(new ZimmetAlModel("alankisi2", "4-11-2019", "57"));
        zimmelal_list.add(new ZimmetAlModel("alankisi3", "5-11-2019", "8"));
        zimmelal_list.add(new ZimmetAlModel("alankisi4", "11-11-2019", "13"));

        return  zimmelal_list;
    }
}
