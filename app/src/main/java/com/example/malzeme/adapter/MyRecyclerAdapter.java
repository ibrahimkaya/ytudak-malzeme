package com.example.malzeme.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import com.example.malzeme.model.MalzemeModel;
import com.example.malzeme.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter <MyRecyclerAdapter.MyViewHolder> implements java.io.Serializable {

    static ArrayList<MalzemeModel> itemList;
    static boolean clicked = false;
    public static ArrayList<SecilenItemler> secilen = new ArrayList<SecilenItemler>();
    LayoutInflater inflater;

    public MyRecyclerAdapter (Context context, ArrayList<MalzemeModel> items){
        inflater = LayoutInflater.from(context);
        this.itemList = items;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.one_item_layout, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("adapteronclicktiklandi", "buradamalzemenolarınıalmayacalis");
                if (!v.isSelected()) {
                    v.setBackgroundResource(R.color.colorPrimary);
                    Log.d("adapteronclicktiklandi", "yesil oldu arraye eklicem");
                    String malzemeno = String.valueOf(itemList.get(holder.getAdapterPosition()).mno);
                    String model = String.valueOf(itemList.get(holder.getAdapterPosition()).model);
                    String not = String.valueOf(itemList.get(holder.getAdapterPosition()).not);
                    secilen.add(new SecilenItemler(malzemeno, model, not));
                    Log.e("position",String.valueOf(holder.getAdapterPosition()));
                    Log.e("Arraye eklenen---->",malzemeno+" "+model+" "+not);
                } else {
                    v.setBackgroundResource(R.color.colorWhite);
                    Log.d("adapteronclicktiklandi", "beyaz oldu");
                    String malzemeno = String.valueOf(itemList.get(holder.getAdapterPosition()).mno);
                    String model = String.valueOf(itemList.get(holder.getAdapterPosition()).model);
                    String not = String.valueOf(itemList.get(holder.getAdapterPosition()).not);
                    for (int i = 0; i < secilen.size(); i++) {
                        if (malzemeno == secilen.get(i).mno.toString()) {
                            secilen.remove(i);
                            Log.e("Arrayden silinen---->",malzemeno+" "+model+" "+not);
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
        public String  not;
        public SecilenItemler(String mno,String model,String not){
            this.mno = mno;
            this.model = model;
            this.not = not;
        }
    }

    public static ArrayList<SecilenItemler> getSecilen() {
        return secilen;
    }


    @Override
    public void onBindViewHolder( MyViewHolder holder, int position) {

        MalzemeModel selectedItem = itemList.get(position);
        holder.setData(selectedItem, position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    boolean clicked = false;

    TextView mnotv, modeltv, nottv;

    public MyViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        mnotv = (TextView) itemView.findViewById(R.id.mNoZimmetVerTV);
        modeltv = (TextView) itemView.findViewById(R.id.zVerilenModelTV);
        nottv = (TextView) itemView.findViewById(R.id.zVerilenNotTV);
    }

    public void setData(MalzemeModel selectedItem, int position) {
        this.mnotv.setText(String.valueOf(selectedItem.mno));
        this.modeltv.setText(String.valueOf(selectedItem.model));
        this.nottv.setText(String.valueOf(selectedItem.not));
    }

    @Override
    public void onClick(View v) {
/*
        Log.d("adapteronclicktiklandi", "buradamalzemenolarınıalmayacalis");
        if (!this.clicked) {
            v.setBackgroundResource(R.color.colorPrimary);
            Log.d("adapteronclicktiklandi", "yesil oldu arraye eklicem");
            String malzemeno = String.valueOf(itemList.get(holder.getAdapterPosition()).mno);
            String model = String.valueOf(itemList.get(holder.getAdapterPosition()).model);
            String not = String.valueOf(itemList.get(holder.getAdapterPosition()).not);
            secilen.add(new SecilenItemler(malzemeno, model, not));
            Log.e("position",String.valueOf(holder.getAdapterPosition()));
            Log.e("Arraye eklenen---->",malzemeno+" "+model+" "+not);
        } else {
            v.setBackgroundResource(R.color.colorWhite);
            Log.d("adapteronclicktiklandi", "beyaz oldu");
            String malzemeno = String.valueOf(itemList.get(holder.getAdapterPosition()).mno);
            String model = String.valueOf(itemList.get(holder.getAdapterPosition()).model);
            String not = String.valueOf(itemList.get(holder.getAdapterPosition()).not);
            for (int i = 0; i < secilen.size(); i++) {
                if (malzemeno == secilen.get(i).mno.toString()) {
                    secilen.remove(i);
                    Log.e("Arrayden silinen---->",malzemeno+" "+model+" "+not);
                }
            }
        }
        this.clicked = !clicked;
*/
    }
    }


}
