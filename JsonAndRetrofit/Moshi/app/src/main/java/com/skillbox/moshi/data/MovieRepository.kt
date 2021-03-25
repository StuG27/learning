package com.skillbox.moshi.data

import com.skillbox.moshi.network.CustomMoshiMovieAdapter
import com.skillbox.moshi.network.Network
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


object MovieRepository {

    fun searchMovie(
        text: String,
        callback: (RemoteMovie) -> Unit
    ): Call {
        return Network.getSearchMovieCall(text).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    callback(
                        RemoteMovie(
                            "${e.message}",
                            "error",
                            CustomMoshiMovieAdapter.MovieRating.GENERAL,
                            "",
                            "",
                            mutableMapOf()
                        )
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
                        val movie = parseMovieResponse(responseString)
                        callback(movie)
                    } else {
                        callback(
                            RemoteMovie(
                                "response.isSuccessful = false",
                                "error",
                                CustomMoshiMovieAdapter.MovieRating.GENERAL,
                                "",
                                "",
                                mutableMapOf()
                            )
                        )
                    }
                }
            })
        }
    }

    private fun parseMovieResponse(responseBodyString: String): RemoteMovie {
        val moshi = Moshi.Builder().add(CustomMoshiMovieAdapter()).build()
        val adapter = moshi.adapter(RemoteMovie::class.java).nonNull()
        try {
            return adapter.fromJson(responseBodyString)
                ?: RemoteMovie(
                    "error",
                    "error",
                    CustomMoshiMovieAdapter.MovieRating.GENERAL,
                    "",
                    "",
                    mutableMapOf()
                )
        } catch (e: Exception) {
            return RemoteMovie(
                "${e.message}",
                "error",
                CustomMoshiMovieAdapter.MovieRating.GENERAL,
                "",
                "",
                mutableMapOf()
            )
        }
    }

}