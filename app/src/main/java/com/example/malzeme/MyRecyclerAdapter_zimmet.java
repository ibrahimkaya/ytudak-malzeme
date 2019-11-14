package com.example.malzeme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter_zimmet extends RecyclerView.Adapter <MyRecyclerAdapter_zimmet.MyViewHolder> {

    ArrayList<ZimmetAlModelActivity> itemList;
    LayoutInflater inflater;

    public MyRecyclerAdapter_zimmet (Context context, ArrayList<ZimmetAlModelActivity> items){
        inflater = LayoutInflater.from(context);
        this.itemList = items;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.zimmetal_item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
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
            if(!this.clicked ) {
                v.setBackgroundResource(R.color.colorPrimary);
                //kayıt edilecek şeyler için yeni bir model oluştur
                //o modelin bir listini oluştur
                //burada viewdeki bilgileri çekip o listeye ekle
                //ikinci tıklanmada yani vazgeçişte o listeden o elemanı çıkar
            }else{
                v.setBackgroundResource(R.color.colorWhite);
            }
            this.clicked = !clicked;
        }
    }
}
