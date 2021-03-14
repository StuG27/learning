package com.skillbox.multithreading.data

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: Int
)