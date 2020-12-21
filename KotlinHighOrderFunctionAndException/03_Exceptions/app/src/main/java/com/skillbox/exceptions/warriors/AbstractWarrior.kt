package com.skillbox.exceptions.warriors

import com.skillbox.exceptions.NoAmmoException
import com.skillbox.exceptions.weapons.AbstractWeapon
import com.skillbox.exceptions.isChanceRealized

abstract class AbstractWarrior(
        private val maxHP: Int,
        override val dodgeChance: Int,
        private val accuracy: Int,
        private val weapon: AbstractWeapon,
        var currentHP: Int = maxHP
) : Warrior {
    override var isKilled: Boolean = false
        get() = currentHP <= 0
    override fun toAttack(enemy: Warrior) {
        var damage = 0
//        if (!weapon.areThereAmmo){
//            weapon.reloading()
//        }
//        else {
        try {
            weapon.getAmmoForShooting()
        }
        catch (t: NoAmmoException) {
            println("Поймал исключение")
            weapon.reloading()
        }
            for (ammo in weapon.ammoListForShooting){
                if (accuracy.isChanceRealized() && !enemy.dodgeChance.isChanceRealized()){
                    damage += ammo.calculateDamage()
                }
            }
            enemy.getDamage(damage)
//        }
    }

    override fun getDamage(damage: Int) {
        currentHP -= damage
        if (currentHP<0) currentHP = 0
    }
}