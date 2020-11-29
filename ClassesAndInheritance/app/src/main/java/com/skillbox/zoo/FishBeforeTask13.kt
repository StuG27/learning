package com.skillbox.zoo

import kotlin.random.Random

class FishBeforeTask13(energy: Int=0, weight: Int, age: Int = 0, maxAge: Int, name: String) :
        AnimalBeforeTask13(energy, weight, age, maxAge, name) {

    override fun move() {
        super.move()
        println("плывёт")
    }

    override fun makeChild(): FishBeforeTask13 {
        val childEnergy = Random.nextInt(10)+1
        val childWeight = Random.nextInt(5)+1
        println("Рождена новая рыба. " +
                "Имя = ${name}, " +
                "максимальный возраст = ${maxAge}, " +
                "энергия = $childEnergy, " +
                "вес = $childWeight")
        return FishBeforeTask13(
                energy = childEnergy,
                weight = childWeight,
                maxAge = maxAge,
                name = name
        )
    }
}