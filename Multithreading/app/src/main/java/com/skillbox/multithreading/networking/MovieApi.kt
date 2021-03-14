package com.skillbox.multithreading.networking

import com.skillbox.multithreading.data.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    fun getMovieById(
        @Query("i") id: String,
        @Query("apikey") apiKey: String
    ): Call<Movie>
}