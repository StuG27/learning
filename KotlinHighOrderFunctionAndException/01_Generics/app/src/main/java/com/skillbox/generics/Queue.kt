package com.skillbox.generics

class Queue<T> {
    var queueList = mutableListOf<T>()
    fun enqueue(item: T){
        queueList.add(item)
    }
    fun dequeue(): T?{
        val item: T? = if (queueList.isEmpty()) null
        else queueList.first()
        val temp = queueList.drop(1)
        queueList = temp.toMutableList()
        return item
    }
}