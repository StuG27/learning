package com.skillbox.moshi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModel : ViewModel() {

    private val repository = MovieRepository
    private val movieListLiveData = MutableLiveData<RemoteMovie>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val movieList: LiveData<RemoteMovie>
        get() = movieListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(text: String) {
        isLoadingLiveData.postValue(true)
        repository.searchMovie(text) { movie ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movie)
        }
    }
}