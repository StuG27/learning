package com.skillbox.moshi.data

import com.skillbox.moshi.network.CustomMoshiMovieAdapter


data class RemoteMovie(
        val title: String,
        val year: String,
        val rating: CustomMoshiMovieAdapter.MovieRating,
        val genre: String,
        val poster: String,
        val scores: MutableMap<String, String>
)

