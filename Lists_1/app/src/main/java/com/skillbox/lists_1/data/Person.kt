package com.skillbox.lists_1.data


sealed class Person{

    data class Actor(
        val name: String,
        val avatarLink: String,
        val age: Int,
        val isHasOscar: Boolean
    ):Person()

    data class Producer(
        val name: String,
        val avatarLink: String,
        val age: Int,
        val isHasOscar: Boolean,
        val bestFilm: String
    ):Person()
}
