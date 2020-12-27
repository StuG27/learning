package com.skillbox.generics

class Queue<T> {
    var queueList = mutableListOf<T>()
    fun enqueue(item: T){
        queueList.add(item)
    }
    fun dequeue(): T?{
//        queueList.removeAt(1)
//        val temp = queueList.drop(1)
//        queueList = temp.toMutableList()
        return if (queueList.isEmpty()) null
        else queueList.removeAt(0)
    }
}