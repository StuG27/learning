package com.skillbox.extensions_objects_enums.warriors

import com.skillbox.extensions_objects_enums.Ammo
import com.skillbox.extensions_objects_enums.weapons.AbstractWeapon
import com.skillbox.extensions_objects_enums.isChanceRealized

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
        if (!weapon.areThereAmmo){
            weapon.reloading()
        }
        else {
            ammoListForShooting = weapon.getAmmoForShooting()
            for (ammo in ammoListForShooting){
                if (accuracy.isChanceRealized() && !enemy.dodgeChance.isChanceRealized()){
                    damage += ammo.calculateDamage()
                }
            }
            enemy.getDamage(damage)
        }
    }

    override fun getDamage(damage: Int) {
        currentHP -= damage
        if (currentHP<0) currentHP = 0
    }
}