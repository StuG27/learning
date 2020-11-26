package com.skillbox.ht4

fun main() {

    println("Введите число абонентов")
    val n = readLine()?.toInt() ?: return
    var numberList: MutableList<String>
    numberList = readNumber(n)
    println("Только номера на +7 ${numberList.filter { it.startsWith("+7") }}")
    var numberSet = numberList.toSet()
    println("Уникальных номеров ${numberSet.size}")
    println("Сумма длин всех номеров ${numberList.sumBy { it.length }}")
    val numberMap = mutableMapOf<String, String>()
    for (i in numberSet){
        println("Введите имя человека с номером телефона $i:")
        numberMap[i] = readLine().toString()
    }
    for ((k, v) in numberMap){
        println("Человек: $v. Номер телефона: $k")
    }
}

fun readNumber(n: Int): MutableList<String> {
    val numberList = mutableListOf<String>()
    var number: String
    for (i in 0 until n){
        println("Введите номер")
        number = readLine().toString()
        numberList.add(number)
    }
    return numberList
}