package com.skillbox.ht3

import kotlin.math.absoluteValue

fun main(){
    println("Введите количество чисел")
    val n = readLine()?.toIntOrNull() ?: return
    var number: Int?
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
    println("Введите число для вычисления НОД")
    val numberForGCD = readLine()?.toIntOrNull() ?: return
    val gCD = calculateGCD(numberForGCD, sum)
    println("Наибольший общий делитель равен $gCD")
}

tailrec fun calculateGCD(a: Int, b: Int): Int {
    if (b == 0)
        return a.absoluteValue
    return calculateGCD(b, a % b)
}