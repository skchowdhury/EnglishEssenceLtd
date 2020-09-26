package com.mustofa.englishessence.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.mustofa.englishessence.R;
import com.mustofa.englishessence.interfaces.ItemClickInterface;
import com.mustofa.englishessence.model.ItemsModel;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder> {
    private ArrayList<ItemsModel> productList;
    private static ItemClickInterface itemClickInterface;


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemName;
        public ImageView itemimage;
        public TextView itemPrice;
        public MaterialButton addtoCart;
        public View itemview;
        public MyViewHolder(View v) {
            super(v);
            this.itemview = v;
            itemName = v.findViewById(R.id.item_name);
            itemimage = v.findViewById(R.id.item_image);
            itemPrice = v.findViewById(R.id.item_price);
            addtoCart = v.findViewById(R.id.item_buybt);

            itemview.setOnClickListener(this);
            addtoCart.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            if(v.getId() == itemview.getId())
            {
                itemClickInterface.onPositionClicked(getAdapterPosition(),1);
            }
            if(v.getId() == addtoCart.getId())
            {
                itemClickInterface.onPositionClicked(getAdapterPosition(),0);
            }
        }
    }


    public ItemsAdapter(ItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;

    }


    @Override
    public ItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.itemName.setText(productList.get(position).getProductName());
        holder.itemPrice.setText(String.format("%s %d","$",productList.get(position).getProductPrice()));
        Glide.with(holder.itemimage.getContext()).load(R.drawable.dummy).into(holder.itemimage);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void productList(ArrayList<ItemsModel> items)
    {
        this.productList = items;

    }
}
