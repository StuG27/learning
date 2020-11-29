package com.skillbox.zoo

import kotlin.random.Random

open class AnimalBeforeTask13 (_energy: Int=0,
                               _weight: Int,
                               _age: Int = 0,
                               _maxAge: Int,
                               _name: String
){
    var energy = _energy
        get() {
            return field
        }
        private set
    var weight = _weight
        private set
    var age = _age
        private set
    /*private - до 12 задания*/protected val maxAge = _maxAge
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
    open fun makeChild(): AnimalBeforeTask13{
        val childEnergy = Random.nextInt(10)+1
        val childWeight = Random.nextInt(5)+1
        println("Рождено новое животное. " +
                "Имя = ${name}, " +
                "максимальный возраст = ${maxAge}, " +
                "энергия = $childEnergy, " +
                "вес = $childWeight")
        return AnimalBeforeTask13(
            _energy = childEnergy,
            _weight = childWeight,
            _maxAge = maxAge,
            _name = name
        )
    }

    private fun incrementAgeSometimes(){
        if (Random.nextBoolean()){
            age +=1
        }
    }
}