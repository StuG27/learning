package com.skillbox.multithreading.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    private val repository = MovieRepository
    private val movieListLiveData = MutableLiveData<MutableList<Movie?>>()
    private val ids = listOf(
        "tt0111161",
        "tt0068646",
        "tt0071562",
        "tt0468569",
        "tt0050083",
        "tt0108052",
        "tt0167260",
        "tt0110912",
        "tt0060196",
        "tt0120737"
    )

    val movieList: LiveData<MutableList<Movie?>>
        get() = movieListLiveData

    fun search() {
        repository.fetchMoviesThreadPool(ids) { movies -> // Пул потоков
//        repository.fetchMoviesThread(ids) { movies -> // Просто потоки
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                movieListLiveData.postValue(movies)
            }
        }
    }
}