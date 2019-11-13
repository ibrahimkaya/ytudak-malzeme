package com.example.malzeme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends RecyclerView.Adapter <MyRecyclerAdapter.MyViewHolder> {

    ArrayList<MalzemeModel> itemList;
    LayoutInflater inflater;

    public MyRecyclerAdapter (Context context, ArrayList<MalzemeModel> items){
        inflater = LayoutInflater.from(context);
        this.itemList = items;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.one_item_layout, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
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



class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    TextView mnotv , modeltv,nottv;
    public MyViewHolder(View itemView){
        super(itemView);

        mnotv = (TextView) itemView.findViewById(R.id.mnoText);
        modeltv = (TextView) itemView.findViewById(R.id.modelText);
        nottv = (TextView) itemView.findViewById(R.id.notText);


    }

    public void setData(MalzemeModel selectedItem, int position){
        this.mnotv.setText(String.valueOf(selectedItem.mno));
        this.modeltv.setText(String.valueOf(selectedItem.model));
        this.nottv.setText(String.valueOf(selectedItem.not));
    }

    @Override
    public void onClick(View v){

    }

}
}
