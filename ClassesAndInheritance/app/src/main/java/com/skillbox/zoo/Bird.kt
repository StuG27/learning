package com.skillbox.zoo

import kotlin.random.Random

class Bird: Animal(0,0,0,0,"") {

    override fun move(){
        super.move()
        println("$name летит")
    }

    override fun makeChild(parent:Bird): Bird {
        return super.makeChild(parent) as Bird
    }
}