package com.app.api


import android.content.Context
import android.net.ConnectivityManager

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Class is used to passing user token at central level.
 */
class ApiInterceptor : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()



        // build request
        val originalRequest = requestBuilder.build()

        return chain.proceed(originalRequest)


    }

}
