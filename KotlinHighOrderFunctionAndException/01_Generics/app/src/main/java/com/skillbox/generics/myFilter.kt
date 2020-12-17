package com.skillbox.generics

fun <T: Number> myFilter(list: List<T>): List<T>{
    var newList = mutableListOf<T>()
    for (i in list){
        if ((i.toDouble() % 2) == 0.0){
            newList.add(i)
        }
    }
    return newList.toList()
}




