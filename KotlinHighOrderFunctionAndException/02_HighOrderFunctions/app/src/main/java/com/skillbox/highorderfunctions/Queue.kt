package com.skillbox.highorderfunctions

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

    fun myFilter(predicate: (T) -> Boolean):Queue<T>{
        var newQueue = Queue<T>()
        for (element in this.queueList) if (predicate(element)) newQueue.queueList.add(element)
        return newQueue
    }

}