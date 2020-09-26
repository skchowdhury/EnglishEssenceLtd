package com.mustofa.englishessence.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mustofa.englishessence.R;
import com.mustofa.englishessence.interfaces.ItemClickInterface;
import com.mustofa.englishessence.model.ItemsModel;
import com.mustofa.englishessence.model.Posts;

import java.util.ArrayList;

public class FbpostAdapter extends RecyclerView.Adapter<FbpostAdapter.MyViewHolder> {
    private ArrayList<Posts> productList;
    private static ItemClickInterface itemClickInterface;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView postTitle;
        public ImageView postImage;
        public TextView postbody;
        public TextView comments;
        public EditText edit_comment;
        public MyViewHolder(View v) {
            super(v);
            postTitle = v.findViewById(R.id.post_title);
            postImage = v.findViewById(R.id.post_image);
            postbody = v.findViewById(R.id.post_body);
            comments = v.findViewById(R.id.comment);
            edit_comment = v.findViewById(R.id.edit_comment);

            comments.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  if(edit_comment.getVisibility() == View.VISIBLE)
                  {
                      edit_comment.setVisibility(View.GONE);
                  }
                  else
                  {
                      edit_comment.setVisibility(View.VISIBLE);
                  }
                }
            });

        }
    }


    public FbpostAdapter(ItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;

    }


    @Override
    public FbpostAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_layout, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.postTitle.setText(productList.get(position).getTitle());
        holder.postbody.setText(productList.get(position).getBody());
        Glide.with(holder.postImage.getContext()).load(R.drawable.dummy).into(holder.postImage);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void productList(ArrayList<Posts> items)
    {
        this.productList = items;
        notifyDataSetChanged();

    }

}
