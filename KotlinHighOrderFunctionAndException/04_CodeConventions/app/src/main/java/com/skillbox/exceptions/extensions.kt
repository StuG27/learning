package com.skillbox.exceptions

import kotlin.random.Random

fun Int.isChanceRealized(): Boolean {
    val randomChance = Random.nextInt(100)
    return this> randomChance // this - шанс в процентах
}
