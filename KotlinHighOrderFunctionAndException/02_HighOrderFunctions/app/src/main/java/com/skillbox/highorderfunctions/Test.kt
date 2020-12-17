package com.skillbox.highorderfunctions


fun main() {
    var testClass = Queue<Int>()
    testClass.enqueue(1)
    testClass.enqueue(2)
    testClass.enqueue(4)
    testClass.enqueue(2)
    testClass.enqueue(1)
    testClass.enqueue(3)
    println(testClass.queueList)
    testClass = testClass.myFilter {  it >= 2 }
    println(testClass.queueList)

}