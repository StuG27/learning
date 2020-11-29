package com.skillbox.zoo

import kotlin.random.Random


fun main() {
    //lifeOfOneAnimal() // Задание 9
    //lifeOfOneAnimalTest() // Тест после задания 16
    val zoo = Zoo()
    println("Введите число итераций")
    val n = readLine()?.toInt() ?: return
    var i = 0
    while (i < n){
        var zooListToRemove = mutableListOf<Animal>()
        for(j in 0 until zoo.zooList.size){
            var countOfActions = if(zoo.zooList[j] is Soundable) { 5 } else { 4 }
            when(Random.nextInt(countOfActions)+1){
                1 -> zoo.zooList[j].move()
                2 -> zoo.zooList[j].eat()
                3 -> zoo.zooList.add(zoo.zooList[j].makeChild())
                4 -> zoo.zooList[j].sleep()
                5 -> if (zoo.zooList[j] is Soundable) (zoo.zooList[j] as Soundable).makeSound()
            }
            if (zoo.zooList[j].isTooOld) {
                zooListToRemove.add(zoo.zooList[j])
            }
        }
        zoo.zooList = zoo.zooList.filterNotIn(zooListToRemove) as MutableList<Animal>
        i++
        if (zoo.zooList.size == 0) {
            println("В зоопарке все умерли")
            break
        }
    }
    println("В зоопарке находится ${zoo.zooList.size} животных")
}

fun <T>Collection<T>.filterNotIn(collection: Collection<T>): Collection<T> {
    val set = collection.toSet()
    return filterNot { set.contains(it) }
}

fun lifeOfOneAnimal(){
    var pet = AnimalBeforeTask13(10,10,0,10,"Пёс")
    while (true){
        pet.eat()
        pet.move()
        pet.sleep()
        if (pet.isTooOld) {
            pet = pet.makeChild()
        }
    }
}
fun lifeOfOneAnimalTest(){
    var pet = Bird(10,10,0,"Воробушек",10)
    while (true){
        pet.eat()
        pet.move()
        pet.sleep()
        if (pet.isTooOld) {
            pet = pet.makeChild()
        }
    }
}