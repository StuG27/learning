package com.skillbox.extensions_objects_enums.warriors

import com.skillbox.extensions_objects_enums.weapons.AbstractWeapon
import com.skillbox.extensions_objects_enums.weapons.Weapons

class MachineGunner(
        maxHP: Int = 120,
        dodgeChance: Int = 50,
        accuracy: Int = 20,
        weapon: AbstractWeapon = Weapons.createMachineGun()
) : AbstractWarrior(
        maxHP,
        dodgeChance,
        accuracy,
        weapon)
