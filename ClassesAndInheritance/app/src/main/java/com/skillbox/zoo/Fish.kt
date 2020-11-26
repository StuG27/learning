package com.skillbox.zoo

class Fish: Animal(0,0,0,0,"") {
    override fun move(){
        super.move()
        println("$name плывёт")
    }
}