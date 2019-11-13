package com.example.malzeme;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.malzeme.MalzemeModel.*;
import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends RecyclerView.Adapter <MyRecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<MalzemeModel> itemList;
    LayoutInflater inflater;

    public MyRecyclerAdapter (Context context, ArrayList<MalzemeModel> items){
        inflater = LayoutInflater.from(context);
        this.itemList = items;
        this.context = context;
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

        holder.mnotv.setText(String.valueOf(selectedItem.mno));
        holder.modeltv.setText(String.valueOf(selectedItem.model));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }



class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView mnotv , modeltv;

    public MyViewHolder(View itemView){
        super(itemView);
        mnotv = (TextView) itemView.findViewById(R.id.mnoText);
        modeltv = (TextView) itemView.findViewById(R.id.modelText);
    }


    @Override
    public void onClick(View v){

    }

}
}
