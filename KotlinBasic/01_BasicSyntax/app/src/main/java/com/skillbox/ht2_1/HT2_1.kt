package com.skillbox.ht2_1

fun main(){
    val firstName = "Daniel"
    val lastName = "Volynkin"
    var height = 180
    val weight = 65f
    var isChild = height<150 || weight<40
    var info = "Меня зовут $firstName $lastName, мой рост равен $height см, а вес - $weight кг. Я ребенок? Ответ - $isChild"
    println(info)
    height = 149
    isChild = height<150 || weight<40
    info = "Меня зовут $firstName $lastName, мой рост равен $height см, а вес - $weight кг. Я ребенок? Ответ - $isChild"
    println(info)
}