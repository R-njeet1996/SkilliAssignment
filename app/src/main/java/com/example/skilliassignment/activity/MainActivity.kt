package com.example.skilliassignment.activity

import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call

class MainActivity : BaseActivity(),ItemClickListener {
    private  var photoList :ArrayList<PhotoResponse> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApi()
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
        var photoAdapter = PhotoAdapter(this,photoList,this)
        rv_post.adapter = photoAdapter
    }

    override fun onItemCLickListener(view: View?, pos: Int) {

    }
}