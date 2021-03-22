package com.skillbox.networking.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModel : ViewModel() {

    private val repository = MovieRepository
    private val movieListLiveData = MutableLiveData<List<RemoteMovie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val movieList: LiveData<List<RemoteMovie>>
        get() = movieListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(text: String, year: String, type: String?, page: Int) {
        isLoadingLiveData.postValue(true)
        repository.searchMovie(text, year, type, page) { movies ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movies)
        }
    }
}