package com.skillbox.github.data.repo

import com.skillbox.github.network.Networking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object DetailsRepository {

    fun getStarInfo(
        owner: String,
        name: String,
        onComplete: (String) -> Unit
    ) {
        Networking.gitHubApi.getStarInfo(owner, name).enqueue(
            object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    val code: String
                    if (response.isSuccessful) {
                        code = response.code().toString()
                        onComplete(code)
                    } else {
                        code = response.code().toString()
                        onComplete(code)
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    onComplete("error")
                }
            }
        )
    }

    fun addStar(
        owner: String,
        name: String,
        onComplete: (String) -> Unit
    ) {
        Networking.gitHubApi.addStar(owner, name).enqueue(
            object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    val code: String
                    if (response.isSuccessful) {
                        code = response.code().toString()
                        onComplete(code)
                    } else {
                        code = response.code().toString()
                        onComplete(code)
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    onComplete("error")
                }
            }
        )
    }

    fun deleteStar(
        owner: String,
        name: String,
        onComplete: (String) -> Unit
    ) {
        Networking.gitHubApi.deleteStar(owner, name).enqueue(
            object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    val code: String
                    if (response.isSuccessful) {
                        code = response.code().toString()
                        onComplete(code)
                    } else {
                        code = response.code().toString()
                        onComplete(code)
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    onComplete("error")
                }
            }
        )
    }
}