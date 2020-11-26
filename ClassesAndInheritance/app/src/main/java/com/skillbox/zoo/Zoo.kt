package com.skillbox.zoo

fun main() {
    lifeOfOneAnimal() // Задание 9
}
fun lifeOfOneAnimal(){
    var pet = Animal(10,5,0,10,"Вася")
    while (true){
        pet.eat()
        pet.move()
        pet.sleep()
        if (pet.isTooOld) {
            pet = pet.makeChild(pet)
        }
    }
}