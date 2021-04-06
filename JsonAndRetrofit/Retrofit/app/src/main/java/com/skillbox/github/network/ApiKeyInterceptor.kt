package com.skillbox.github.network

import okhttp3.Interceptor
import okhttp3.Response


class ApiKeyInterceptor : Interceptor {
    private val headerName = "Authorization"
    private val token = AccessToken.token
    override fun intercept(chain: Interceptor.Chain): Response {
        val modifiedRequest =
            chain.request().newBuilder().addHeader(headerName, "token $token").build()
        return chain.proceed(modifiedRequest)
    }
}