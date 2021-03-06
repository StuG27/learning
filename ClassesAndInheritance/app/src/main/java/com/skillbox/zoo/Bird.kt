package com.skillbox.zoo

import kotlin.random.Random

class Bird(energy: Int=0, weight: Int, age: Int = 0, name: String, override val maxAge: Int) :
        Animal(energy, weight, age, name), Soundable{

    override fun move() {
        if (isTooOld || energy < 5 || weight < 1) return
        super.move()
        println("летит")
    }
    override fun makeChild(): Bird {
        val childEnergy = Random.nextInt(10)+1
        val childWeight = Random.nextInt(5)+1
        println("Рождена новая птица. " +
                "Имя = ${name}, " +
                "максимальный возраст = ${maxAge}, " +
                "энергия = $childEnergy, " +
                "вес = $childWeight")
        return Bird(
                energy = childEnergy,
                weight = childWeight,
                maxAge = maxAge,
                name = name
        )
    }

    override fun makeSound() {
        println("Чик-Чирик")
    }
}