package com.skillbox.extensions_objects_enums.warriors

import com.skillbox.extensions_objects_enums.weapons.AbstractWeapon
import com.skillbox.extensions_objects_enums.weapons.Weapons

class Sniper(
        maxHP: Int = 80,
        dodgeChance: Int = 90,
        accuracy: Int = 90,
        weapon: AbstractWeapon = Weapons.createSniperRifle()
) : AbstractWarrior(
        maxHP,
        dodgeChance,
        accuracy,
        weapon){
    override var isKilled: Boolean = false
        get() = currentHP <= 0
}

