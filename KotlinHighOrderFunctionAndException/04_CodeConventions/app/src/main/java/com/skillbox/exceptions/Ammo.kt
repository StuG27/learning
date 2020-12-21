package com.skillbox.exceptions

enum class Ammo(
    private val damage: Int,
    private val criticalChance: Int,
    private val criticalDamageCoefficient: Int
) {
    REGULAR(10, 20, 2),
    INCENDIARY(20, 50, 2),
    EXPLOSIVE(50, 30, 2);

//  Из задания №1
//    fun calculateDamage(): Int {
//        val randomChance = Random.nextInt(100)
//        return if (criticalChance>randomChance) damage * criticalDamageCoefficient
//        else damage
//    }

    fun calculateDamage(): Int {
        return if (criticalChance.isChanceRealized()) damage * criticalDamageCoefficient
        else damage
    }
}
