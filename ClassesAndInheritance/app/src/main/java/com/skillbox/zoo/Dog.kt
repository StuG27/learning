package com.skillbox.zoo

class Dog: Animal(0,0,0,0,"") {
    override fun move(){
        super.move()
        println("$name бежит")
    }
}