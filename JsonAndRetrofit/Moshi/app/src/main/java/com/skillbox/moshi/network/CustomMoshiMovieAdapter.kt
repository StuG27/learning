package com.skillbox.moshi.network

import com.skillbox.moshi.data.RemoteMovie
import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson


class CustomMoshiMovieAdapter {

    @FromJson
    fun fromJson(rawMovie: RawMovie): RemoteMovie {
        val scores: MutableMap<String, String> = mutableMapOf()
        rawMovie.scores.forEach { scores[it.source] = it.value }
        return RemoteMovie(
            rawMovie.title,
            rawMovie.year,
            rawMovie.rating,
            rawMovie.genre,
            rawMovie.poster,
            scores
        )
    }

    @ToJson
    fun toJson(remoteMovie: RemoteMovie): RawMovie {
        val scores: MutableList<Score> = mutableListOf()
        remoteMovie.scores.forEach { (k, v) -> scores.add(Score(k, v)) }
        return RawMovie(
            remoteMovie.title,
            remoteMovie.year,
            remoteMovie.rating,
            remoteMovie.genre,
            remoteMovie.poster,
            scores
        )
    }

    @JsonClass(generateAdapter = true)
    data class RawMovie(
        @Json(name = "Title")
        val title: String,
        @Json(name = "Year")
        val year: String,
        @Json(name = "Rated")
        val rating: MovieRating,
        @Json(name = "Genre")
        val genre: String,
        @Json(name = "Poster")
        val poster: String,
        @Json(name = "Ratings")
        val scores: List<Score>
    )

    @JsonClass(generateAdapter = true)
    data class Score(
        @Json(name = "Source")
        val source: String,
        @Json(name = "Value")
        val value: String
    )

    enum class MovieRating {
        @Json(name = "G")
        GENERAL,
        PG,

        @Json(name = "PG-13")
        PG_13,
        R,

        @Json(name = "NC-17")
        NC_17
    }
}