package com.skillbox.networking.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Movies(
    @Json(name = "Search")
    val list: List<RemoteMovie>
)