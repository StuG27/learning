package com.skillbox.zoo

import kotlin.random.Random

class BirdBeforeTask13(energy: Int=0, weight: Int, age: Int = 0, maxAge: Int, name: String) :
        AnimalBeforeTask13(energy, weight, age, maxAge, name) {

    override fun move() {
        super.move()
        println("летит")
    }

    override fun makeChild(): BirdBeforeTask13 {
        val childEnergy = Random.nextInt(10)+1
        val childWeight = Random.nextInt(5)+1
        println("Рождена новая птица. " +
                "Имя = ${name}, " +
                "максимальный возраст = ${maxAge}, " +
                "энергия = $childEnergy, " +
                "вес = $childWeight")
        return BirdBeforeTask13(
                energy = childEnergy,
                weight = childWeight,
                maxAge = maxAge,
                name = name
        )
    }
}