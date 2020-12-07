package com.skillbox.extensions_objects_enums

enum class Ammo(
        private val damage: Int,
        private val criticalChance: Int,
        private val criticalDamageCoefficient: Int
) {
    REGULAR(1,20,2),
    INCENDIARY(2,50, 2),
    EXPLOSIVE(5,30,2);

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
