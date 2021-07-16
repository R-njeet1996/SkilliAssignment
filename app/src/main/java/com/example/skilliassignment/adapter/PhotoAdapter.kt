package com.example.skilliassignment.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.skilliassignment.R
import com.example.skilliassignment.adapter.PhotoAdapter.CustomViewHolder
import com.example.skilliassignment.modal.PhotoResponse
import com.example.skilliassignment.utils.ItemClickListener
import java.util.*


class PhotoAdapter(
    var context: Context,
    var photoList: ArrayList<PhotoResponse>,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (photoList[position].getIsPhoto()!!) {
            if (photoList[position].getPhoto() != null) {
                val imageAsBytes = Base64.decode(
                    photoList[position].getPhoto()!!.toByteArray(), Base64.DEFAULT
                )


             var bitmap=   BitmapFactory.decodeByteArray(
                    imageAsBytes,
                    0,
                    imageAsBytes.size
                )
                bitmap = Bitmap.createScaledBitmap(bitmap,500, 200, true);

                holder.ivPost.setImageBitmap(
                bitmap
                )
            }
        } else {
            if (photoList[position].getPicture() != null) {
                Glide.with(context)
                    .load(photoList[position].getPicture())
                    .placeholder(R.color.black)
                    .into(holder.ivPost)
            }
        }
        holder.ivPost.setOnClickListener { v -> itemClickListener.onItemCLickListener(v, position) }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPost: ImageView

        init {
            ivPost = itemView.findViewById(R.id.iv_post)
        }
    }
}