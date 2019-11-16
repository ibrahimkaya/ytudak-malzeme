package com.example.malzeme.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.malzeme.R;
import com.example.malzeme.model.ZimmetAlModelActivity;

import java.util.ArrayList;

public class MyRecyclerAdapter_zimmet extends RecyclerView.Adapter <MyRecyclerAdapter_zimmet.MyViewHolder> {

    ArrayList<ZimmetAlModelActivity> itemList;
    LayoutInflater inflater;
    static ArrayList<ZimmetALSecilenItemler> ZimmetAlinacakItemler = new ArrayList<>();
    public MyRecyclerAdapter_zimmet (Context context, ArrayList<ZimmetAlModelActivity> items){
        inflater = LayoutInflater.from(context);
        this.itemList = items;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.zimmetal_item_layout, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("zimmetal_view_onclik", "zimmetalma item tiklandi");
                if (!v.isSelected()) {
                    v.setBackgroundResource(R.color.colorPrimary);
                    Log.d("zimmetaladapterTiklandi", "yesil oldu arraye eklicem");
                    //arraye ekleme islemi
                    String alindigi_tarih = String.valueOf(itemList.get(holder.getAdapterPosition()).teslim_tarihi);
                    String alan_kisi = String.valueOf(itemList.get(holder.getAdapterPosition()).teslim_edilen);
                    String mno = String.valueOf(itemList.get(holder.getAdapterPosition()).malzeme_no);
                    ZimmetAlinacakItemler.add(new ZimmetALSecilenItemler(mno, alan_kisi, alindigi_tarih));

                } else {
                    v.setBackgroundResource(R.color.colorWhite);
                    Log.d("zimmetaladapterTiklandi", "beyaz oldu");
                    // arrayden silinecek
                    String alindigi_tarih = String.valueOf(itemList.get(holder.getAdapterPosition()).teslim_tarihi);
                    String alan_kisi = String.valueOf(itemList.get(holder.getAdapterPosition()).teslim_edilen);
                    String mno = String.valueOf(itemList.get(holder.getAdapterPosition()).malzeme_no);
                    for (int i = 0; i < ZimmetAlinacakItemler.size(); i++) {
                        if (mno == ZimmetAlinacakItemler.get(i).mno.toString()) {
                            ZimmetAlinacakItemler.remove(i);
                            Log.e("Arrayden silinen---->", mno + " " + alan_kisi + " " + alindigi_tarih);
                        }
                    }
                }
                v.setSelected(!v.isSelected());
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        ZimmetAlModelActivity selectedItem = itemList.get(position);
        holder.teslim_tarihi.setText(String.valueOf(selectedItem.teslim_tarihi));
        holder.teslimeden.setText(String.valueOf(selectedItem.teslim_edilen));
        holder.mno.setText(String.valueOf(selectedItem.malzeme_no));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ZimmetALSecilenItemler {
        public String mno;
        public String alan_kisi;
        public String alindigi_tarih;

        public ZimmetALSecilenItemler(String mno, String alan_kisi, String alindigi_tarih) {
            this.mno = mno;
            this.alan_kisi = alan_kisi;
            this.alindigi_tarih = alindigi_tarih;
        }
    }

    public static ArrayList<ZimmetALSecilenItemler> getZimmetAlinacakItemler() {
        return ZimmetAlinacakItemler;
    }

    public static void setZimmetAlinacakItemler() {
        ZimmetAlinacakItemler.clear();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        boolean clicked = false;

        TextView mno ,teslimeden,teslim_tarihi;
        public MyViewHolder(View itemView){
            super(itemView);

            mno = itemView.findViewById(R.id.mnoText_zimmetal);
            teslimeden = itemView.findViewById(R.id.teslimedenText);
            teslim_tarihi = itemView.findViewById(R.id.teslim_tarihText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){

        }
    }
}
