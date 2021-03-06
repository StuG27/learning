package com.skillbox.zoo

import kotlin.random.Random

class Dog(energy: Int=0, weight: Int, age: Int = 0, name: String, override val maxAge: Int) :
        Animal(energy, weight, age, name), Soundable {

    override fun move() {
        if (isTooOld || energy < 5 || weight < 1) return
        super.move()
        println("бежит")
    }

    override fun makeChild(): Dog {
        val childEnergy = Random.nextInt(10) + 1
        val childWeight = Random.nextInt(5) + 1
        println("Рождена новая собака. " +
                "Имя = ${name}, " +
                "максимальный возраст = ${maxAge}, " +
                "энергия = $childEnergy, " +
                "вес = $childWeight")
        return Dog(
                energy = childEnergy,
                weight = childWeight,
                maxAge = maxAge,
                name = name
        )
    }

    override fun makeSound() {
        println("Гав-Гав")
    }
}
