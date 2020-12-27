package com.skillbox.extensions_objects_enums.weapons

import com.skillbox.extensions_objects_enums.Ammo
import com.skillbox.extensions_objects_enums.FireType

abstract class AbstractWeapon(private val maxOfAmmo: Int, private val fireType: FireType) {
    private var ammoList: List<Ammo> = listOf()
    var ammoListForShooting: MutableList<Ammo> = mutableListOf()
    var areThereAmmo = false
        get() = ammoList.size > fireType.burstSize


    abstract fun createAmmo(): Ammo

    fun reloading(){
        val ammoListNew = MutableList(maxOfAmmo) {createAmmo()}
//        val ammoListNew: MutableList<Ammo> = mutableListOf()
//        var i = 0
//        var ammo: Ammo
//        while (i < maxOfAmmo) {
//            ammo = createAmmo()
//            ammoListNew.add(ammo)
//            i++
//        }
        ammoList = ammoListNew
    }
    fun getAmmoForShooting(): MutableList<Ammo>{
        var i = ammoList.lastIndex
        while (i > ammoList.lastIndex-fireType.burstSize){
            ammoListForShooting.add(ammoList[i])
            i--
        }
        ammoList = ammoList.dropLast(fireType.burstSize)
        return ammoListForShooting
    }
}