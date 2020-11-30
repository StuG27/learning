package com.skillbox.zoo

class Zoo {
    var zooList = mutableListOf<Animal>()
    init {
        var i = 0
        val numberOfBird = 5
        var bird = Bird(0,0,0,"Птица",2)
        while (i < numberOfBird){
            bird = bird.makeChild()
            zooList.add(bird)
            i++
        }
        val numberOfFish = 3
        var fish = Fish(0,0,0,"Рыба",4)
        while (i < numberOfBird+numberOfFish){
            fish = fish.makeChild()
            zooList.add(fish)
            i++
        }
        val numberOfDog = 2
        var dog = Dog(0,0,0,"Собака",1)
        while (i < numberOfBird+numberOfFish+numberOfDog){
            dog = dog.makeChild()
            zooList.add(dog)
            i++
        }
        val numberOfAnimal = 4
        val animal = object : Animal(0,0,0,"Простое Животное"){
            override val maxAge = 3
        }
        while (i < numberOfBird+numberOfFish+numberOfDog+numberOfAnimal){
            val animal = animal.makeChild()
            zooList.add(animal)
            i++
        }
        println("Зоопарк сформирован")
    }
//    fun printZoo(){
//        println(zooList)
//    }
}
