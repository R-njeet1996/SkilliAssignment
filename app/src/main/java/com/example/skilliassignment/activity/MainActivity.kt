package com.example.skilliassignment.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.api.ApiClient
import com.app.api.IApiRequest
import com.app.base.BaseActivity
import com.example.skilliassignment.R
import com.example.skilliassignment.adapter.PhotoAdapter
import com.example.skilliassignment.api.ApiCallback
import com.example.skilliassignment.modal.ErrorModel
import com.example.skilliassignment.modal.PhotoResponse
import com.example.skilliassignment.utils.ItemClickListener
import com.example.skilliassignment.utils.ModelPreferencesManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import java.io.ByteArrayOutputStream


class MainActivity : BaseActivity(),ItemClickListener {
    private  var photoList :ArrayList<PhotoResponse> = ArrayList()
    private  var photoListStorage :ArrayList<PhotoResponse> = ArrayList()
    private  var CAMERA_REQUEST = 1000
   private lateinit var photoAdapter:PhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            photoListStorage = ModelPreferencesManager.getListFromLocal("KEY_BAG")
            ModelPreferencesManager.saveListInLocal(photoListStorage, "KEY_BAG")
        }
        catch (e:Exception)
        {
            ModelPreferencesManager.saveListInLocal(photoListStorage, "KEY_BAG")
        }

        callApi()

        bt_click.setOnClickListener {

            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }

   private fun callApi()
   {
       showProgressDialog(this)
       val request: IApiRequest = ApiClient.request!!
       val call: Call<List<PhotoResponse>> =
           request.photoList()
       call.enqueue(object : ApiCallback<List<PhotoResponse>>(this) {
           override fun onError(errorModel: ErrorModel?) {
               dismissProgressDialog()
           }

           override fun onSuccess(t: List<PhotoResponse>?) {
               dismissProgressDialog()
               if(t!=null && t.size>0)
               {
                   photoList = ArrayList()
                   photoList.clear()
                   photoList.addAll(t)
                   setAdapter()
               }
           }
       })
   }

    private fun setAdapter() {
        var  layoutManager = LinearLayoutManager(this)
        rv_post.layoutManager = layoutManager

            if (ModelPreferencesManager.getListFromLocal("KEY_BAG") != null)
                photoList.addAll(ModelPreferencesManager.getListFromLocal("KEY_BAG"))

        photoAdapter = PhotoAdapter(this,photoList,this)
        rv_post.adapter = photoAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CAMERA_REQUEST) {

            if(data!=null) {

                if(data?.getExtras()!=null) {
                    val photo = data?.getExtras()?.get("data") as (Bitmap)

                    var photoResponse = PhotoResponse()
                    val baos = ByteArrayOutputStream()
                    photo.compress(Bitmap.CompressFormat.PNG, 100, baos) //bm is the bitmap object
                    val b = baos.toByteArray()
                    val encoded: String = Base64.encodeToString(b, Base64.DEFAULT)
                    photoResponse.setIsPhoto(true)
                    photoResponse.setPhoto(encoded)
                    photoResponse.setTitle("Self Taken Photo")
                    photoResponse.setComment("This image is taken by camera")

                    photoListStorage.add(photoResponse)

                    ModelPreferencesManager.saveListInLocal(photoListStorage, "KEY_BAG")


                    photoList.add(photoResponse)

                    photoAdapter.notifyDataSetChanged()
                }

            }


        }
    }

    override fun onItemCLickListener(view: View?, pos: Int) {

    }
}



