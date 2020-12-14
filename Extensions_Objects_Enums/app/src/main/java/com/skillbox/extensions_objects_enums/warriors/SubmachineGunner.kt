package com.skillbox.extensions_objects_enums.warriors

import com.skillbox.extensions_objects_enums.weapons.AbstractWeapon
import com.skillbox.extensions_objects_enums.weapons.Weapons

class SubmachineGunner(
        maxHP: Int = 100,
        dodgeChance: Int = 50,
        accuracy: Int = 30,
        weapon: AbstractWeapon = Weapons.createSubmachineGun()
) : AbstractWarrior(
        maxHP,
        dodgeChance,
        accuracy,
        weapon){
    override var isKilled: Boolean = false
        get() = currentHP <= 0
}
