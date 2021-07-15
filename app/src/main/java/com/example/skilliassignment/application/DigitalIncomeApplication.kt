package com.digitalincome.revolution.application

import android.app.Application
import android.content.Context
import com.app.api.ApiClient


class SkilliApplication :Application() {
    companion object {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        // init api client for all network call
        ApiClient.init()
    }
}