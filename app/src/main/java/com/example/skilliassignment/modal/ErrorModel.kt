package com.example.skilliassignment.modal

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ErrorModel {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("result")
    @Expose
    var result: String? = null
}