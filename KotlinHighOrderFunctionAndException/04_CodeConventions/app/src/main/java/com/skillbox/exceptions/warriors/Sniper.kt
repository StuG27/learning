package com.skillbox.exceptions.warriors

import com.skillbox.exceptions.weapons.AbstractWeapon
import com.skillbox.exceptions.weapons.Weapons

class Sniper(
        maxHP: Int = 80,
        dodgeChance: Int = 90,
        accuracy: Int = 90,
        weapon: AbstractWeapon = Weapons.createSniperRifle()
) : AbstractWarrior(
        maxHP,
        dodgeChance,
        accuracy,
        weapon)

