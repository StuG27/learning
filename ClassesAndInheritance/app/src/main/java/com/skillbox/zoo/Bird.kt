package com.skillbox.zoo

import kotlin.random.Random

class Bird: Animal(0,0,0,0,"") {


    override fun move(){
        super.move()
        println("$name летит")
    }
//
//    override fun makeChild(parent:Bird): Bird {
//        val childEnergy = Random.nextInt(10)+1
//        val childWeight = Random.nextInt(5)+1
//        println("Рождено новое животное. " +
//                "Имя = ${parent.name}, " +
//                "максимальный возраст = ${parent.maxAge}, " +
//                "энергия = $childEnergy, " +
//                "вес = $childWeight")
//        return Bird(
//            _energy = childEnergy,
//            _weight = childWeight,
//            _maxAge = parent.maxAge,
//            _name = parent.name
//        )
//
//    }
}