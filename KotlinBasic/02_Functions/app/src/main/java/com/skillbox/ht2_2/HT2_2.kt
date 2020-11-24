package com.skillbox.ht2_2

import kotlin.math.sqrt

fun main(){
    val solutionSum = solveEquation(a = 4, b = 5, c = -6)
    println(solutionSum)
}

fun solveEquation(a: Int, b: Int, c: Int): Any{
    val d = b*b - 4*a*c // Вычисление дискриминанта
    return if(d >= 0){
        val x1 = (-b + sqrt(d.toDouble()))/(2*a) // Вычисление первого корня
        val x2 = (-b - sqrt(d.toDouble()))/(2*a) // Вычисление второго корня
        println(x1)
        println(x2)
        x1 + x2
    }else{
        "Дискриминант меньше 0 - нет корней"
    }
}