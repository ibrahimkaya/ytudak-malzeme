package com.example.malzeme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.malzeme.R;
import com.example.malzeme.model.ZimmetVerModel;

import java.util.ArrayList;
import java.util.Arrays;


public class MyRecyclerAdapter_Zver extends RecyclerView.Adapter<MyRecyclerAdapter_Zver.MyViewHolder> implements java.io.Serializable {

    private static ArrayList<ZimmetVerModel> itemList;
    private static ArrayList<SecilenItemler> secilen = new ArrayList<>();
    private LayoutInflater inflater;
    boolean[] indexOfSelectedItems;

    public MyRecyclerAdapter_Zver(Context context, ArrayList<ZimmetVerModel> items) {
        inflater = LayoutInflater.from(context);
        itemList = items;

        //hangi itemin secildiğini tutmak için item listesi kadar false olan bir index arrayı oluşturuyorum
        indexOfSelectedItems = new boolean[getItemCount()];
        Arrays.fill(indexOfSelectedItems,false);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //view oluştur
        View view = inflater.inflate(R.layout.one_item_layout, parent, false);
        final MyViewHolder holder = new MyViewHolder(view,this);

        return holder;
    }

    public class SecilenItemler {
        public String mno;
        public String model;
        public String not;
        public String kategori;

        public SecilenItemler(String mno, String model,String kategori, String not) {
            this.mno = mno;
            this.model = model;
            this.not = not;
            this.kategori = kategori;
        }
    }

    public static ArrayList<SecilenItemler> getSecilen() {
        return secilen;
    }

    public static void clearSecilen() {
        secilen.clear();
    }




    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //bu noktadaki holderi bağlarken itemlistten pozisyondaki itemi al
        ZimmetVerModel selectedItem = itemList.get(position);
        //holdere set data diye bas
        holder.setData(selectedItem, position);

        //viewlara datayı bağlarken (scroll yapıldığında veya yaratıldığında )
        // kullanılacak viewi daha önce seçmiş isem arka planını yeşil seçmemiş isem beyaz yap
        if(indexOfSelectedItems[holder.getAdapterPosition()]){
            holder.itemView.setBackgroundColor(Color.GREEN);}
        else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mnotv, modeltv, nottv;

        public MyViewHolder(View itemView, MyRecyclerAdapter_Zver adapter) {
            super(itemView);

            mnotv =  itemView.findViewById(R.id.mNoZimmetVerTV);
            modeltv = itemView.findViewById(R.id.zVerilenModelTV);
            nottv =  itemView.findViewById(R.id.zVerilenNotTV);
            //viweholderimin tuttuğu viewe onclick ekledim
            itemView.setOnClickListener(this);
        }

        public void setData(ZimmetVerModel selectedItem, int position) {
            this.mnotv.setText(String.valueOf(selectedItem.mno));
            this.modeltv.setText(String.valueOf(selectedItem.model));
            this.nottv.setText(String.valueOf(selectedItem.not));
        }

        @Override
        public void onClick(View v) {
            int currentAdapterPos = getAdapterPosition();

            //eğer seçilmemiş ise
            if(!indexOfSelectedItems[currentAdapterPos]){
                //seç ve arka planını yeşil yap
                indexOfSelectedItems[currentAdapterPos] = true;
                v.setBackgroundColor(Color.GREEN);

                //item listesinden o itemi seçilen itemlere ekle
                String malzemeno = String.valueOf(itemList.get(currentAdapterPos).mno);
                String model = String.valueOf(itemList.get(currentAdapterPos).model);
                String not = String.valueOf(itemList.get(currentAdapterPos).not);
                String kategori = String.valueOf(itemList.get(currentAdapterPos).kategori);
                secilen.add(new SecilenItemler(malzemeno, model,kategori, not));

            }else{
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


}}