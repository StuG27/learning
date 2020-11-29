package com.skillbox.zoo

import kotlin.random.Random

class Fish(energy: Int=0, weight: Int, age: Int = 0, name: String, override val maxAge: Int) :
        Animal(energy, weight, age, name) {

    override fun move() {
        if (isTooOld || energy < 5 || weight < 1) return
        super.move()
        println("плывёт")
    }

    override fun makeChild(): Fish {
        val childEnergy = Random.nextInt(10)+1
        val childWeight = Random.nextInt(5)+1
        println("Рождена новая рыба. " +
                "Имя = ${name}, " +
                "максимальный возраст = ${maxAge}, " +
                "энергия = $childEnergy, " +
                "вес = $childWeight")
        return Fish(
                energy = childEnergy,
                weight = childWeight,
                maxAge = maxAge,
                name = name
        )
    }
}