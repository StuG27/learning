package com.skillbox.ht2_2

import kotlin.math.sqrt

fun main(){
    val solutionSum = solveEquation(a = 9, b = 6, c = 1)
    if(solutionSum.isNaN()) println("Дискриминант меньше нуля - нет корней")
    else println("Сумма корней равна $solutionSum")
}

fun solveEquation(a: Int, b: Int, c: Int): Double{
    val d = b*b - 4*a*c // Вычисление дискриминанта
    return when {
        d > 0 -> {
            val x1 = (-b + sqrt(d.toDouble())) / (2*a) // Вычисление первого корня
            val x2 = (-b - sqrt(d.toDouble())) / (2*a) // Вычисление второго корня
            println(x1)
            println(x2)
            x1 + x2
        }
        d == 0 -> -b / (2*a).toDouble() // Вычисление единственного корня
        else -> Double.NaN
    }
}