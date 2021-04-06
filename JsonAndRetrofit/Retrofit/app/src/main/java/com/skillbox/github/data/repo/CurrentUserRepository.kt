package com.skillbox.github.data.repo

import com.skillbox.github.data.User
import com.skillbox.github.network.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object CurrentUserRepository {

    fun getUserInfo(
        onComplete: (User) -> Unit
    ) {
        Networking.gitHubApi.getUserInfo().enqueue(
            object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        onComplete(response.body() ?: User("Ошибка", 0, "", ""))
                    } else {
                        onComplete(User("Ошибка в запросе", 0, "", ""))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    onComplete(User("Ошибка сети", 0, "", ""))
                }
            }
        )
    }
}