package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Repository(
    val id: Int,
    val name: String,
    val owner: Owner,
    val private: Boolean
) {
    @JsonClass(generateAdapter = true)
    data class Owner(
        val login: String,
        @Json(name = "html_url")
        val url: String
    )
}

