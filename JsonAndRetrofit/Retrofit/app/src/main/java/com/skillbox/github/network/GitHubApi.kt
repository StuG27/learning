package com.skillbox.github.network

import com.skillbox.github.data.Repository
import com.skillbox.github.data.User
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface GitHubApi {

    @GET("user")
    fun getUserInfo(): Call<User>

    @GET("repositories")
    fun getRepositoriesList(): Call<List<Repository>>

    @GET("user/starred/{owner}/{repo}")
    fun getStarInfo(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): Call<String>

    @DELETE("user/starred/{owner}/{repo}")
    fun deleteStar(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): Call<String>

    @PUT("user/starred/{owner}/{repo}")
    fun addStar(
        @Path("owner") owner: String,
        @Path("repo") name: String
    ): Call<String>
}