package com.skillbox.zoo

import kotlin.random.Random

abstract class Animal (_energy: Int=0,
                   _weight: Int,
                   _age: Int = 0,
                   _name: String
):AgedAnimal(){
    var energy = _energy
        get() {
            return field
        }
        private set
    var weight = _weight
        private set
    var age = _age
        private set
    val name = _name
    var isTooOld:Boolean = false
    get() = age >= maxAge
    fun sleep(){
        if (isTooOld) return
        energy +=5
        age +=1
        println("$name спит")
    }
    fun eat(){
        if (isTooOld) return
        energy +=3
        weight +=1
        incrementAgeSometimes()
        println("$name ест")
    }
    open fun move(){
        if (isTooOld || energy < 5 || weight < 1) return
        energy -=5
        weight -=1
        incrementAgeSometimes()
        println("$name двигается")
    }
    open fun makeChild(): Animal{
        val childEnergy = Random.nextInt(10)+1
        val childWeight = Random.nextInt(5)+1
        println("Рождено новое животное. " +
                "Имя = ${name}, " +
                "максимальный возраст = ${maxAge}, " +
                "энергия = $childEnergy, " +
                "вес = $childWeight")
        return object : Animal(childEnergy, childWeight, age, name){
            override val maxAge = this@Animal.maxAge
        }
    }
    private fun incrementAgeSometimes(){
        if (Random.nextBoolean()){
            age +=1
        }
    }
}