package com.skillbox.networking.network

import okhttp3.Interceptor
import okhttp3.Response


class ApiKeyInterceptor : Interceptor {
    private val name = "apikey"
    private val value = "b2fa4e67"
    override fun intercept(chain: Interceptor.Chain): Response {
        val modifiedUrl = chain.request().url.newBuilder().addQueryParameter(name, value).build()
        val modifiedRequest = chain.request().newBuilder().url(modifiedUrl).build()
        return chain.proceed(modifiedRequest)
    }
}