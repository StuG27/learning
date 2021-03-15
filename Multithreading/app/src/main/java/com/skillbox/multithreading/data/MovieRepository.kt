package com.skillbox.multithreading.data

import android.util.Log
import com.skillbox.multithreading.networking.Network
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

object MovieRepository {

    fun fetchMoviesThreadPool(

        ids: List<String>,
        onMoviesFetched: (movies: MutableList<Movie?>) -> Unit
    ) {
        val latch = CountDownLatch(ids.size)
        val cores = Runtime.getRuntime().availableProcessors()
        Log.d("Pool", "$cores")
        val pool = Executors.newFixedThreadPool(cores)
        val allMovies = mutableListOf<Movie?>()
        ids.forEach { movieId ->
            pool.execute {
                allMovies.add(Network.getMovieById(movieId))
                Log.d("Pool", "${Thread.currentThread()}")
                latch.countDown()
            }
        }
        Thread {
            latch.await()
            onMoviesFetched(allMovies)
        }.start()
    }

    fun fetchMoviesThread(
        ids: List<String>,
        onMoviesFetched: (movies: MutableList<Movie?>) -> Unit
    ) {
        Thread {
            val allMovies = Collections.synchronizedList(mutableListOf<Movie>())
            val threads = ids.chunked(1).map { movieChunk ->
                Thread {
                    val movies = movieChunk.mapNotNull { movieId ->
                        Network.getMovieById(movieId)
                    }
                    allMovies.addAll(movies)
                }
            }
            threads.forEach { it.start() }
            threads.forEach { it.join() }
            onMoviesFetched(allMovies)
        }.start()
    }
}