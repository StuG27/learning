package com.skillbox.exceptions.warriors

import com.skillbox.exceptions.weapons.AbstractWeapon
import com.skillbox.exceptions.weapons.Weapons

class MachineGunner(
    maxHP: Int = 120,
    dodgeChance: Int = 50,
    accuracy: Int = 20,
    weapon: AbstractWeapon = Weapons.createMachineGun()
) : AbstractWarrior(
    maxHP,
    dodgeChance,
    accuracy,
    weapon
)
