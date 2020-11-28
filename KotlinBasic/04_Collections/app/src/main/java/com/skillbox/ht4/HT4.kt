package com.skillbox.ht4

fun main() {
    println("Введите число абонентов")
    val n = readLine()?.toInt() ?: return
    val numberList: List<String>
    numberList = readNumber(n)
    println("Только номера на +7 ${numberList.filter { it.startsWith("+7") }}")
    val numberSet = numberList.toSet()
    println("Уникальных номеров ${numberSet.size}")
    println("Сумма длин всех номеров ${numberList.sumBy { it.length }}")
    val numberMap = mutableMapOf<String, String>()
    for (i in numberSet){
        println("Введите имя человека с номером телефона $i:")
        numberMap[i] = readLine().toString()
    }
//    for ((k, v) in numberMap){
//        println("Человек: $v. Номер телефона: $k")
//    }
    val sortedMapByKey = numberMap.toSortedMap()
    println("Сортировка по телефону")
    for ((k, v) in sortedMapByKey){
        println("Человек: $v. Номер телефона: $k")
    }
    val sortedMapByValue = numberMap.toList()
            .sortedBy { (key, value) -> value }
            .toMap()
    println("Сортировка по имени")
    for ((k, v) in sortedMapByValue){
        println("Человек: $v. Номер телефона: $k")
    }
}

fun readNumber(n: Int): MutableList<String> {
    val numberList = mutableListOf<String>()
    var number: String
    var i = 1
    while (i < n+1){
        println("Введите $i номер мобильного телефона")
        number = readLine().toString()
        if (!phoneNumberValidate(number)) continue
        i++
        numberList.add(number)
    }
    return numberList
}
fun phoneNumberValidate(string: String): Boolean{
    return string.matches(Regex("""^(\+?7|8)(\d{10})"""))
    // Подходят +70123456789 и 80123456789
}