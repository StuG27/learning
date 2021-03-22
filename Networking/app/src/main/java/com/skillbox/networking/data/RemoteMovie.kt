package com.skillbox.networking.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovie(
        @Json(name = "Title")
        val title: String,
        @Json(name = "Year")
        val year: String,
        @Json(name = "imdbID")
        val id: String,
        @Json(name = "Type")
        val type: String,
        @Json(name = "Poster")
        val poster: String
)

