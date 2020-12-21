package com.skillbox.exceptions.warriors

interface Warrior {
    var isKilled: Boolean
    val dodgeChance: Int

    fun toAttack(enemy: Warrior)
    fun getDamage(damage: Int)
}
