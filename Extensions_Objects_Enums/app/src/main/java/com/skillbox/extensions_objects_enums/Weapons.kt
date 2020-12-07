package com.skillbox.extensions_objects_enums

object Weapons {
    fun createRifle(): AbstractWeapon {
        return object : AbstractWeapon(10, Singles){
            override fun createAmmo(): Ammo {
                return Ammo.REGULAR
            }
        }
    }
    fun createSniperRifle(): AbstractWeapon {
        return object : AbstractWeapon(20, Singles){
            override fun createAmmo(): Ammo {
                return Ammo.EXPLOSIVE
            }
        }
    }
    fun createSubmachineGun(): AbstractWeapon {
        return object : AbstractWeapon(60, Burst(30)){
            override fun createAmmo(): Ammo {
                return Ammo.INCENDIARY
            }
        }
    }
    fun createMachineGun(): AbstractWeapon {
        return object : AbstractWeapon(300, Burst(60)){
            override fun createAmmo(): Ammo {
                return Ammo.REGULAR
            }
        }
    }
}