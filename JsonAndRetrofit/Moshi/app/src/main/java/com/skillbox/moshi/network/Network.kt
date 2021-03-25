package com.skillbox.moshi.network

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request


object Network {

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(ApiKeyInterceptor())
        .build()

    fun getSearchMovieCall(title: String): Call {
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("t", title)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        return client.newCall(request)
    }
}