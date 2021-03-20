package com.skillbox.networking.network

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object Network {

    val networkFlipperPlugin = NetworkFlipperPlugin()

    val client = OkHttpClient.Builder()
            .addNetworkInterceptor(CustomHeaderInterceptor("header", "value"))
            .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

    fun getSearchMovieCall(text: String, page: Int): Call {
        val url = HttpUrl.Builder()
                .scheme("http")
                .host("www.omdbapi.com")
                .addQueryParameter("apikey", "b2fa4e67")
                .addQueryParameter("s", text)
                .addQueryParameter("page", page.toString())
                .build()

        val request = Request.Builder()
                .get()
                .url(url)
                .build()

        return client.newCall(request)
    }
}