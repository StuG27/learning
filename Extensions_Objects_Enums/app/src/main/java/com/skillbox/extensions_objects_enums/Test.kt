package com.skillbox.extensions_objects_enums

import com.skillbox.extensions_objects_enums.Weapons.createSniperRifle


fun main(){
    val gun = createSniperRifle()
    println(gun.ammoList)
    gun.reloading()
    println(gun.ammoList)
    println(gun.areThereAmmo)
    gun.getAmmoForShooting()
    println(gun.ammoListForShooting)
}
