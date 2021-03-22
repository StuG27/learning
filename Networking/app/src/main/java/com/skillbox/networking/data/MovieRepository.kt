package com.skillbox.networking.data

import android.util.Log
import com.skillbox.networking.network.Network
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


object MovieRepository {

    fun searchMovie(
        text: String,
        year: String,
        type: String?,
        page: Int,
        callback: (List<RemoteMovie>) -> Unit
    ): Call {
//        Network.getSearchMovieCall(text).execute() // К заданию 8 - NetworkOnMainThreadException
        return Network.getSearchMovieCall(text, year, type, page).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Server", "execute request error = ${e.message}", e)
                    callback(
                        listOf(
                            RemoteMovie(
                                "${e.message}",
                                "",
                                "error",
                                "",
                                ""
                            )
                        )
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
                        val movies = parseMovieResponse(responseString)
                        callback(movies)
                    } else {
                        callback(
                            listOf(
                                RemoteMovie(
                                    "response.isSuccessful = false",
                                    "",
                                    "error",
                                    "",
                                    ""
                                )
                            )
                        )
                    }
                }
            })
        }
    }

    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie> {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Movies::class.java).nonNull()
        return try {
            val movies = adapter.fromJson(responseBodyString) ?: Movies(
                listOf(
                    RemoteMovie(
                        "error",
                        "",
                        "error",
                        "",
                        ""
                    )
                )
            )
            movies.list
        } catch (e: Exception) {
            listOf(RemoteMovie("${e.message}", "", "error", "", ""))
        }
    }
}