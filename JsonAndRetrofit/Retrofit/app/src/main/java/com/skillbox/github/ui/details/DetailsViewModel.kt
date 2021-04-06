package com.skillbox.github.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.repo.DetailsRepository


class DetailsViewModel : ViewModel() {

    private val repository = DetailsRepository
    private val starInfoLiveData = MutableLiveData<String>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val addStarLiveData = MutableLiveData<String>()
    private val deleteStarLiveData = MutableLiveData<String>()

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val starInfo: LiveData<String>
        get() = starInfoLiveData

    val addStarInfo: LiveData<String>
        get() = addStarLiveData

    val deleteStarInfo: LiveData<String>
        get() = deleteStarLiveData

    fun getStarInfo(
        owner: String,
        name: String
    ) {
        isLoadingLiveData.postValue(true)
        repository.getStarInfo(
            owner,
            name,
            onComplete = { code ->
                starInfoLiveData.postValue(code)
                isLoadingLiveData.postValue(false)
            })
    }

    fun addStar(
        owner: String,
        name: String
    ) {
        isLoadingLiveData.postValue(true)
        repository.addStar(
            owner,
            name,
            onComplete = { code ->
                addStarLiveData.postValue(code)
                isLoadingLiveData.postValue(false)
            })
    }

    fun deleteStar(
        owner: String,
        name: String
    ) {
        isLoadingLiveData.postValue(true)
        repository.deleteStar(
            owner,
            name,
            onComplete = { code ->
                deleteStarLiveData.postValue(code)
                isLoadingLiveData.postValue(false)
            })
    }
}