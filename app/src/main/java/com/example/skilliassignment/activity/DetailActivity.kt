package com.example.skilliassignment.activity

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.bumptech.glide.Glide
import com.example.skilliassignment.R
import com.example.skilliassignment.utils.AppConstant
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var url :String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setData()
    }

    private fun setData() {
        tv_title.setText(intent.getStringExtra(AppConstant.INTENT_EXTRAS.PHOTO_HEAD))
        tv_comment.setText(intent.getStringExtra(AppConstant.INTENT_EXTRAS.PHOTO_TEXT))
        
        if(intent.extras?.getBoolean(AppConstant.INTENT_EXTRAS.IS_PHOTO)!!)
        {
            val imageAsBytes = Base64.decode(
                intent.getStringExtra(AppConstant.INTENT_EXTRAS.PHOTO_URL)!!.toByteArray(), Base64.DEFAULT
            )
          iv_post.setImageBitmap(
                BitmapFactory.decodeByteArray(
                    imageAsBytes,
                    0,
                    imageAsBytes.size
                )
            )
        }
        else
        {
            Glide.with(this)
                .load( intent.getStringExtra(AppConstant.INTENT_EXTRAS.PHOTO_URL))
                .placeholder(R.color.black)
                .into(iv_post)
        }
    }
}








