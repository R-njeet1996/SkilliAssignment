package com.app.api




import com.example.skilliassignment.modal.PhotoResponse
import retrofit2.Call
import retrofit2.http.*

interface IApiRequest {






    @GET("60cb2fa78a4cd025b79f18c8")
    fun photoList():Call<List<PhotoResponse>>

}