package com.skillbox.exceptions.warriors

import com.skillbox.exceptions.weapons.AbstractWeapon
import com.skillbox.exceptions.weapons.Weapons

class Rifleman(
    maxHP: Int = 100,
    dodgeChance: Int = 50,
    accuracy: Int = 50,
    weapon: AbstractWeapon = Weapons.createRifle()
) : AbstractWarrior(
    maxHP,
    dodgeChance,
    accuracy,
    weapon
)
