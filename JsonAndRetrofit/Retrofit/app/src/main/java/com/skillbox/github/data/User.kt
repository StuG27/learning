package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class User(
    val login: String,
    val id: Int,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "html_url")
    val url: String,
)
