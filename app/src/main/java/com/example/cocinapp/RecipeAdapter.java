package com.example.cocinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private List<Recipe> mData;
    private LayoutInflater mInflater;
    private Context context;
    final RecipeAdapter.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Recipe item);
    }

    public RecipeAdapter(List<Recipe> itemList, Context context, RecipeAdapter.OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.context   = context;
        this.mData     = itemList;
        this.listener  = listener;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.recipe_card, null);
        return new RecipeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, time;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tvMealName);
            time = itemView.findViewById(R.id.tvMealTime);


        }

        void bindData(final Recipe item){
            name.setText(item.getName());
            time.setText(item.getTime());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    listener.onItemClick(item);
                }                                        }
            );
        }

    }
}