package com.digitalincome.revolution.application

import android.app.Application
import android.content.Context
import com.app.api.ApiClient
import com.example.skilliassignment.utils.ModelPreferencesManager


class SkilliApplication :Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ModelPreferencesManager.with(this)

        // init api client for all network call
        ApiClient.init()
    }
}