package com.skillbox.networking.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.Call

class ViewModel: ViewModel() {

    private val repository = MovieRepository
    private var currentCall: Call? = null
    private val movieListLiveData = MutableLiveData<List<RemoteMovie>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val movieList: LiveData<List<RemoteMovie>>
        get() = movieListLiveData

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    fun search(text: String, age: String, type: String, page: Int) {
        isLoadingLiveData.postValue(true)
        repository.searchMovie(text, page){ movies ->
            isLoadingLiveData.postValue(false)
            movieListLiveData.postValue(movies)
            currentCall = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        currentCall?.cancel()
    }

}