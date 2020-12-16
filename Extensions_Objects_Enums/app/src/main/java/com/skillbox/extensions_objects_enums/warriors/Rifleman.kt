package com.skillbox.extensions_objects_enums.warriors

import com.skillbox.extensions_objects_enums.weapons.AbstractWeapon
import com.skillbox.extensions_objects_enums.weapons.Weapons

class Rifleman(
        maxHP: Int = 100,
        dodgeChance: Int = 50,
        accuracy: Int = 50,
        weapon: AbstractWeapon = Weapons.createRifle()
) : AbstractWarrior(
        maxHP,
        dodgeChance,
        accuracy,
        weapon)
