package com.skillbox.generics

import kotlin.random.Random


sealed class Result<out T, R>

data class Success<T, R>(var success: T) : Result<T, R>()

data class MyError<T, R>(var error: R) : Result<T, R>()

fun returned(): Result<Int,String>{
    var success: Result<Int,String> = Success(10)
    var error: Result<Int,String> = MyError("error")
    return if (Random.nextBoolean()) success else error
}




