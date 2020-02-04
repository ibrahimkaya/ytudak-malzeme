package com.example.malzeme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.malzeme.R;
import com.example.malzeme.activty.ZimmetAlActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MyRecyclerAdapter_zimmetAl extends RecyclerView.Adapter <MyRecyclerAdapter_zimmetAl.MyViewHolder> {

    private ArrayList<ZimmetAlActivity.GelenRequestModel> itemList;
    private static ArrayList<ZimmetAlActivity.GelenRequestModel> secilen = new ArrayList<>();
    private LayoutInflater inflater;
    boolean[] indexOfSelectedItems;


    public static ArrayList<ZimmetAlActivity.GelenRequestModel> getSecilen() {
        return secilen;
    }

    public static void clearSecilen() {
        secilen.clear();
    }


    public MyRecyclerAdapter_zimmetAl(Context context, ArrayList<ZimmetAlActivity.GelenRequestModel> items) {
        inflater = LayoutInflater.from(context);
        this.itemList = items;

        indexOfSelectedItems = new boolean[getItemCount()];
        Arrays.fill(indexOfSelectedItems, false);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.zimmetal_item_layout, parent, false);
        final MyViewHolder holder = new MyViewHolder(view, this);
        return holder;
    }



    @Override
    public void onBindViewHolder(MyRecyclerAdapter_zimmetAl.MyViewHolder holder, int position) {
        //bu noktadaki holderi bağlarken itemlistten pozisyondaki itemi al
        ZimmetAlActivity.GelenRequestModel selectedItem = itemList.get(position);
        //holdere set data diye bas
        holder.setData(selectedItem, position);

        //viewlara datayı bağlarken (scroll yapıldığında veya yaratıldığında )
        // kullanılacak viewi daha önce seçmiş isem arka planını yeşil seçmemiş isem beyaz yap
        if (indexOfSelectedItems[holder.getAdapterPosition()]) {
            holder.itemView.setBackgroundColor(Color.GREEN);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView isimtv, mnotv, modeltv, nottv, kategoritv, tarihtv, malzemecinotv;

        public MyViewHolder(View itemView, MyRecyclerAdapter_zimmetAl adapter) {
            super(itemView);

            mnotv = itemView.findViewById(R.id.zal_zimmet_mno);
            modeltv = itemView.findViewById(R.id.zal_model);
            nottv = itemView.findViewById(R.id.zal_zimmet_not);
            isimtv = itemView.findViewById(R.id.zal_adi);
            kategoritv = itemView.findViewById(R.id.zal_kategori);
            tarihtv = itemView.findViewById(R.id.zal_tarih);
            malzemecinotv = itemView.findViewById(R.id.zal_malzemencino);

            //viweholderimin tuttuğu viewe onclick ekledim
            itemView.setOnClickListener(this);
        }

        public void setData(ZimmetAlActivity.GelenRequestModel selectedItem, int position) {
            this.mnotv.setText(String.valueOf(selectedItem.mno));
            this.modeltv.setText(String.valueOf(selectedItem.model));
            this.nottv.setText(String.valueOf(selectedItem.zimmet_not));
            this.isimtv.setText(String.valueOf(selectedItem.zimmetalan));
            this.kategoritv.setText(String.valueOf(selectedItem.kategori));
            this.tarihtv.setText(String.valueOf(selectedItem.zimmet_alinma_tarih));
            this.malzemecinotv.setText(String.valueOf(selectedItem.malzemecino));
        }

        @Override
        public void onClick(View v) {
            int currentAdapterPos = getAdapterPosition();

            //eğer seçilmemiş ise
            if (!indexOfSelectedItems[currentAdapterPos]) {
                //seç ve arka planını yeşil yap
                indexOfSelectedItems[currentAdapterPos] = true;
                v.setBackgroundColor(Color.GREEN);

                //item listesinden o itemi seçilen itemlere ekle
                String mno = String.valueOf(itemList.get(currentAdapterPos).mno);
                String model = String.valueOf(itemList.get(currentAdapterPos).model);
                String not = String.valueOf(itemList.get(currentAdapterPos).zimmet_not);
                String kategori = String.valueOf(itemList.get(currentAdapterPos).kategori);
                String isim = String.valueOf(itemList.get(currentAdapterPos).zimmetalan);
                String tarih = String.valueOf(itemList.get(currentAdapterPos).zimmet_alinma_tarih);
                String malzemeciNo = String.valueOf(itemList.get(currentAdapterPos).malzemecino);

                secilen.add(new ZimmetAlActivity.GelenRequestModel(mno, isim, kategori, model, tarih, not, malzemeciNo));

            } else {
                //seçilmiş ise seçimini kaldır ve arka planı düzel
                indexOfSelectedItems[currentAdapterPos] = false;
                v.setBackgroundColor(Color.WHITE);

                String malzemeno = String.valueOf(itemList.get(currentAdapterPos).mno);
                //tekrar tıklananların  malzeme noları uyuşan bilgiyi çıkar
                for (int i = 0; i < secilen.size(); i++) {
                    if (malzemeno.equals(secilen.get(i).mno)) {
                        secilen.remove(i);
                    }
                }
            }
        }
    }
}
