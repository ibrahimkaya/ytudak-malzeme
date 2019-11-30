package com.example.malzeme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.malzeme.R;
import com.example.malzeme.model.ZimmetVerModel;

import java.util.ArrayList;

public class MyRecyclerAdapter_Zver extends RecyclerView.Adapter<MyRecyclerAdapter_Zver.MyViewHolder> implements java.io.Serializable {

    private static ArrayList<ZimmetVerModel> itemList;
    private static ArrayList<SecilenItemler> secilen = new ArrayList<>();
    private LayoutInflater inflater;

    public MyRecyclerAdapter_Zver(Context context, ArrayList<ZimmetVerModel> items) {
        inflater = LayoutInflater.from(context);
        itemList = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.one_item_layout, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //secilmediyse sec arka planı yeşil yap ve secilen arrayina ekle
                if (!v.isSelected()) {
                    v.setBackgroundResource(R.color.colorPrimary);
                    String malzemeno = String.valueOf(itemList.get(holder.getAdapterPosition()).mno);
                    String model = String.valueOf(itemList.get(holder.getAdapterPosition()).model);
                    String not = String.valueOf(itemList.get(holder.getAdapterPosition()).not);
                    secilen.add(new SecilenItemler(malzemeno, model, not));
                } else {
                    v.setBackgroundResource(R.color.colorWhite);
                    String malzemeno = String.valueOf(itemList.get(holder.getAdapterPosition()).mno);
                    String model = String.valueOf(itemList.get(holder.getAdapterPosition()).model);
                    String not = String.valueOf(itemList.get(holder.getAdapterPosition()).not);
                    //tekrar tıklananların arka planını beyaz yap ve o malzeme malzeme noları uyuşan bilgiyi çıkar
                    for (int i = 0; i < secilen.size(); i++) {
                        if (malzemeno.equals(secilen.get(i).mno)) {
                            secilen.remove(i);
                        }
                    }
                }
                v.setSelected(!v.isSelected());

            }
        });

        return holder;
    }

    public class SecilenItemler {
        public String mno;
        public String model;
        public String not;

        public SecilenItemler(String mno, String model, String not) {
            this.mno = mno;
            this.model = model;
            this.not = not;
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

        ZimmetVerModel selectedItem = itemList.get(position);
        holder.setData(selectedItem, position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mnotv, modeltv, nottv;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mnotv =  itemView.findViewById(R.id.mNoZimmetVerTV);
            modeltv = itemView.findViewById(R.id.zVerilenModelTV);
            nottv =  itemView.findViewById(R.id.zVerilenNotTV);
        }

        public void setData(ZimmetVerModel selectedItem, int position) {
            this.mnotv.setText(String.valueOf(selectedItem.mno));
            this.modeltv.setText(String.valueOf(selectedItem.model));
            this.nottv.setText(String.valueOf(selectedItem.not));
        }

        @Override
        public void onClick(View v) {
        }
    }
}
