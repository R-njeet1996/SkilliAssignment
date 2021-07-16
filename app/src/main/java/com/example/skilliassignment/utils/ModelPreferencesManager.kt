package com.example.skilliassignment.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.skilliassignment.modal.PhotoResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


object ModelPreferencesManager {

    lateinit var preferences: SharedPreferences

    private const val PREFERENCES_FILE_NAME = "PREFERENCES_FILE_NAME"


    fun with(application: Application) {
        preferences = application.getSharedPreferences(
            PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }








    fun saveListInLocal(list: ArrayList<PhotoResponse>, key: String?) {

        val editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }

    fun getListFromLocal(key: String?): ArrayList<PhotoResponse> {

        val gson = Gson()
        val json = preferences.getString(key, null)
        val type: Type = object : TypeToken<ArrayList<PhotoResponse>>() {}.type
        return gson.fromJson<ArrayList<PhotoResponse>>(json, type)
    }


}