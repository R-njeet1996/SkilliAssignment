package com.example.skilliassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.view.CustomTextView;
import com.bumptech.glide.Glide;
import com.example.skilliassignment.R;
import com.example.skilliassignment.modal.PhotoResponse;
import com.example.skilliassignment.utils.ItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PhotoAdapter  extends RecyclerView.Adapter<PhotoAdapter.CustomViewHolder> {
    Context context;
    ArrayList<PhotoResponse> photoList;
    ItemClickListener itemClickListener;
  public   PhotoAdapter(Context context , ArrayList<PhotoResponse> photoList, ItemClickListener itemClickListener)
    {
          this.context = context;
          this.photoList = photoList;
          this.itemClickListener = itemClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new PhotoAdapter.CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CustomViewHolder holder, int position) {

        if(photoList.get(position).getPicture()!=null) {
            Glide.with(context)
                    .load(photoList.get(position).getPicture())
                    .placeholder(R.color.black)
                    .into(holder.ivPost);
        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
      ImageView ivPost;
      CustomTextView tvTitle, tvDesc;
        public CustomViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
             ivPost= itemView.findViewById(R.id.iv_post);
        }
    }
}
