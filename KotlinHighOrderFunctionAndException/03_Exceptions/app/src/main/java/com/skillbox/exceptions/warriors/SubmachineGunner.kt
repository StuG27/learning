package com.skillbox.exceptions.warriors

import com.skillbox.exceptions.weapons.AbstractWeapon
import com.skillbox.exceptions.weapons.Weapons

class SubmachineGunner(
        maxHP: Int = 100,
        dodgeChance: Int = 50,
        accuracy: Int = 30,
        weapon: AbstractWeapon = Weapons.createSubmachineGun()
) : AbstractWarrior(
        maxHP,
        dodgeChance,
        accuracy,
        weapon)
