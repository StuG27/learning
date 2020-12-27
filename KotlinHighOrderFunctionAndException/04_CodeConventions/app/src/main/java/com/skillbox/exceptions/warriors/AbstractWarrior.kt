package com.skillbox.exceptions.warriors

import com.skillbox.exceptions.Ammo
import com.skillbox.exceptions.NoAmmoException
import com.skillbox.exceptions.isChanceRealized
import com.skillbox.exceptions.weapons.AbstractWeapon

abstract class AbstractWarrior(
    private val maxHP: Int,
    override val dodgeChance: Int,
    private val accuracy: Int,
    private val weapon: AbstractWeapon,
    var currentHP: Int = maxHP
) : Warrior {
    override var isKilled: Boolean = false
        get() = currentHP <= 0

    private var ammoListForShooting : MutableList<Ammo> = mutableListOf()
    override fun toAttack(enemy: Warrior) {
        var damage = 0
//        if (!weapon.areThereAmmo){
//            weapon.reloading()
//        }
//        else {
        try {
            ammoListForShooting = weapon.getAmmoForShooting()
        } catch (t: NoAmmoException) {
            println("Поймал исключение")
            weapon.reloading()
        }
        for (ammo in ammoListForShooting) {
            if (accuracy.isChanceRealized() && !enemy.dodgeChance.isChanceRealized()) {
                damage += ammo.calculateDamage()
            }
        }
        enemy.getDamage(damage)
//        }
    }

    override fun getDamage(damage: Int) {
        currentHP -= damage
        if (currentHP < 0) currentHP = 0
    }
}
