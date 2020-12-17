package com.skillbox.generics

fun main(){

    val testClass = Queue<String>()
    println(testClass.queueList)
    testClass.enqueue("Первый")
    println(testClass.queueList)
    testClass.enqueue("Второй")
    println(testClass.queueList)
    val first = testClass.dequeue()
    println(testClass.queueList)
    println(first)
    val second = testClass.dequeue()
    println(testClass.queueList)
    println(second)

    val a = listOf(1,2,2.2,3.0,4.0)
    val b = myFilter(a)
    println(b)

    var c1 : Result<Number,String> = Success(0)
    var c2 : Result<Number,String> = MyError("0")
    var d1 : Result<Any,String> = Success(0)
    var d2 : Result<Any,String> = MyError("0")

    var e1 : Result<Int, CharSequence> = Success(0)
    var e2 : Result<Int, CharSequence> = MyError("0")
    var f1 : Result<Int, Any> = Success(0)
    var f2 : Result<Int, Any> = MyError("0")

    c1 = returned()
    c2 = returned()
    d1 = returned()
    d2 = returned()

//    e1 = returned() //ОШИБКА
//    e2 = returned() //ОШИБКА
//    f1 = returned() //ОШИБКА
//    f2 = returned() //ОШИБКА
}