package com.skillbox.github.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.skillbox.github.data.repo.CurrentUserRepository
import com.skillbox.github.data.User


class CurrentUserViewModel : ViewModel() {

    private val repository = CurrentUserRepository
    private val userInfoLiveData = MutableLiveData<User>()

    val userInfo: LiveData<User>
        get() = userInfoLiveData

    fun getUserInfo() {
        repository.getUserInfo(
            onComplete = { user ->
                userInfoLiveData.postValue(user)
            })
    }
}