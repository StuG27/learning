package com.skillbox.networking.data

import android.util.Log
import com.skillbox.networking.network.Network
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.Currency


object MovieRepository {

    var movies: List<RemoteMovie> = emptyList()

    fun searchMovie(text: String, page: Int, callback: (List<RemoteMovie>) -> Unit): Call {

//        Network.getSearchMovieCall(text).execute() // К заданию 8 - NetworkOnMainThreadException
        return Network.getSearchMovieCall(text, page).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Server", "execute request error = ${e.message}", e)
                    callback(emptyList())
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("Server", "response successful = ${response.isSuccessful}")
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
                        val movies = parseMovieResponse(responseString)
                        callback(movies)
                        Log.d("Server", "response string = $responseString")
                    } else {
                        callback(emptyList())
                    }
                }
            })
        }
    }

    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie> {
        try {
            val jsonObject = JSONObject(responseBodyString)
            val movieArray = jsonObject.getJSONArray("Search")

            movies = (0 until movieArray.length()).map { index -> movieArray.getJSONObject(index) }
                    .map { movieJsonObject ->
                        val title = movieJsonObject.getString("Title")
                        val year = movieJsonObject.getString("Year")
                        val id = movieJsonObject.getString("imdbID")
                        val type = movieJsonObject.getString("Type")
                        val poster = movieJsonObject.getString("Poster")
                        RemoteMovie(id, title, year, type, poster)
                    }
            return movies
        } catch (e: JSONException) {
            Log.d("Server", "parse response error = ${e.message}", e)
            movies = emptyList()
            return movies
        }
    }
}