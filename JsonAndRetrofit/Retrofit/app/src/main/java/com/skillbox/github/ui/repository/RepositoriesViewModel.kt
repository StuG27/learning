package com.skillbox.github.ui.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.Repository
import com.skillbox.github.data.repo.RepositoryListRepository


class RepositoriesViewModel : ViewModel() {

    private val repository = RepositoryListRepository
    private val repositoryInfoLiveData = MutableLiveData<List<Repository>>()
    private val isLoadingLiveData = MutableLiveData<Boolean>()

    val isLoading: LiveData<Boolean>
        get() = isLoadingLiveData

    val repositoryInfo: LiveData<List<Repository>>
        get() = repositoryInfoLiveData

    fun getRepositoriesList() {
        isLoadingLiveData.postValue(true)
        repository.getRepositoriesList(
            onComplete = { list ->
                repositoryInfoLiveData.postValue(list)
                isLoadingLiveData.postValue(false)
            })
    }
}