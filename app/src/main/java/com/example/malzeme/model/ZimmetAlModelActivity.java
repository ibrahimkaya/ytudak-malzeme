package com.example.malzeme.model;

import java.util.ArrayList;


public class ZimmetAlModelActivity {
    public String teslim_edilen;
    public int malzeme_no;
    public String teslim_tarihi;


    public ZimmetAlModelActivity(String teslimedilen,String teslimtarihi,int mno){
        this.teslim_edilen = teslimedilen;
        this.teslim_tarihi = teslimtarihi;
        this.malzeme_no = mno;
    }

    public static ArrayList<ZimmetAlModelActivity> getData(){
        ArrayList<ZimmetAlModelActivity> zimmelal_list = new ArrayList<ZimmetAlModelActivity>();
        //datayı internetten alıp burada basıcam
        //alttakiler örnek eklemeler
        zimmelal_list.add(new ZimmetAlModelActivity("alankisi1","2-11-2019",1));
        zimmelal_list.add(new ZimmetAlModelActivity("alankisi2","4-11-2019",2));
        zimmelal_list.add(new ZimmetAlModelActivity("alankisi3","5-11-2019",3));
        zimmelal_list.add(new ZimmetAlModelActivity("alankisi4","11-11-2019",4));


        return  zimmelal_list;
    }
}
