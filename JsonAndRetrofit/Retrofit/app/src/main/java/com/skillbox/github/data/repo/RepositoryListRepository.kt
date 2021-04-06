package com.skillbox.github.data.repo

import com.skillbox.github.data.Repository
import com.skillbox.github.network.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object RepositoryListRepository {

    fun getRepositoriesList(
        onComplete: (List<Repository>) -> Unit
    ) {
        Networking.gitHubApi.getRepositoriesList().enqueue(
            object : Callback<List<Repository>> {
                override fun onResponse(
                    call: Call<List<Repository>>,
                    response: Response<List<Repository>>
                ) {
                    if (response.isSuccessful) {
                        onComplete(
                            response.body() ?: listOf(
                                Repository(
                                    0,
                                    "Ошибка",
                                    Repository.Owner("", ""),
                                    false
                                )
                            )
                        )
                    } else {
                        onComplete(
                            listOf(
                                Repository(
                                    0,
                                    "Ошибка в запросе",
                                    Repository.Owner("", ""),
                                    false
                                )
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    onComplete(
                        listOf(
                            Repository(
                                0,
                                "Ошибка в запросе",
                                Repository.Owner("", ""),
                                false
                            )
                        )
                    )
                }
            }
        )
    }
}
