package com.skillbox.moshi.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

class CustomAdapter {

    @FromJson
    fun fromJson(customMovie: CustomMovie): Movie {
        return Movie(
            customMovie.mainInfoWrapper.id,
            customMovie.mainInfoWrapper.title,
            customMovie.mainInfoWrapper.year,
            customMovie.additionalInfoWrapper.rating,
            customMovie.additionalInfoWrapper.scores,
        )
    }

    @ToJson
    fun toJson(movie: Movie): CustomMovie {
        return CustomMovie(
            MainInfoWrapper(
                movie.id,
                movie.title,
                movie.year
            ),
            AdditionalInfoWrapper(
                movie.rating,
                movie.scores
            )
        )
    }

    @JsonClass(generateAdapter = true)
    data class MainInfoWrapper(
        @Json(name = "imdb_id")
        val id: String,
        val title: String,
        val year: Int,
    )

    @JsonClass(generateAdapter = true)
    data class AdditionalInfoWrapper(
        val rating: MovieRating,
        val scores: List<Score>
    )

    @JsonClass(generateAdapter = true)
    data class CustomMovie(
        @Json(name = "main_info")
        val mainInfoWrapper: MainInfoWrapper,
        @Json(name = "additional_info")
        val additionalInfoWrapper: AdditionalInfoWrapper
    )
}