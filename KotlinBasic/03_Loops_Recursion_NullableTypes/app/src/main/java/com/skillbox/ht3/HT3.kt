package com.skillbox.ht3

import kotlin.math.absoluteValue

fun main(){
    println("Введите количество чисел")
    val n = readLine()?.toIntOrNull() ?: return
    var number: Int?
    var gCD: Int
    var numberForGCD: Int?
    var sum = 0
    var positiveNumbers = 0
    var i = 0
    while (i < n) {
        println("Введите число")
        number = readLine()?.toIntOrNull() ?:  continue
        i++
        sum += number
        if (number > 0) positiveNumbers++
    }
    println("Вы ввели $positiveNumbers положительных чисел")
    for (i in 0 until n) {
        println("Введите число для вычисления НОД")
        numberForGCD = readLine()?.toIntOrNull() ?:  continue
        gCD = calculateGCD(numberForGCD, sum)
        println("Наибольший общий делитель $numberForGCD и $sum равен $gCD")
    }
}

tailrec fun calculateGCD(a: Int, b: Int): Int {
    if (b == 0)
        return a.absoluteValue
    return calculateGCD(b, a % b)
}