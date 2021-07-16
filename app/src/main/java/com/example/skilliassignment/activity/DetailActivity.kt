package com.example.skilliassignment.activity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.skilliassignment.R
import com.example.skilliassignment.utils.AppConstant
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setData()

        tv_share.setOnClickListener {
            if(intent.extras?.getBoolean(AppConstant.INTENT_EXTRAS.IS_PHOTO)!!)
            {
                val imageAsBytes = Base64.decode(
                    intent.getStringExtra(AppConstant.INTENT_EXTRAS.PHOTO_URL)!!.toByteArray(), Base64.DEFAULT
                )
                shareImage( BitmapFactory.decodeByteArray(
                    imageAsBytes,
                    0,
                    imageAsBytes.size
                ),"Clicked Image")
            }
            else {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(
                    Intent.EXTRA_TEXT,
                    "Photo Url"+"\n" + intent.getStringExtra(AppConstant.INTENT_EXTRAS.PHOTO_URL)
                );
                startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
            }

        }
    }

    fun shareImage(bitmap: Bitmap?, text: String?) {

        val pathofBmp = MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap, "title", null
        )
        val uri = Uri.parse(pathofBmp)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "image/*"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Star App")
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(shareIntent, "hello hello"))
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










