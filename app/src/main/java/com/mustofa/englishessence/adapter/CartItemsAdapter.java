package com.mustofa.englishessence.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mustofa.englishessence.R;
import com.mustofa.englishessence.interfaces.ItemClickInterface;
import com.mustofa.englishessence.model.ItemsModel;

import java.util.ArrayList;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.MyViewHolder> {
    private ArrayList<ItemsModel> productList;
    private static ItemClickInterface itemClickInterface;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView itemName;
        public ImageView itemimage;
        public TextView itemPrice;
        public ImageButton delete;
        public MyViewHolder(View v) {
            super(v);
            itemName = v.findViewById(R.id.item_name);
            itemimage = v.findViewById(R.id.item_image);
            itemPrice = v.findViewById(R.id.item_price);
            delete = v.findViewById(R.id.item_deletebt);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickInterface.onPositionClicked(getAdapterPosition(), v.getId());

                }
            });

        }
    }


    public CartItemsAdapter(ItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;

    }


    @Override
    public CartItemsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_layout, parent, false);

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
