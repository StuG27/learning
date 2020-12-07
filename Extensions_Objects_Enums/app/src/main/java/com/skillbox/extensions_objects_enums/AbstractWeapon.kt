package com.skillbox.extensions_objects_enums

abstract class AbstractWeapon(private val maxOfAmmo: Int, private val fireType: FireType) {
    var ammoList: List<Ammo> = listOf()
    var ammoListForShooting: MutableList<Ammo> = mutableListOf()
    var areThereAmmo = false

    abstract fun createAmmo(): Ammo

    fun reloading(){
        val ammoListNew: MutableList<Ammo> = mutableListOf()
        var i = 0
        var ammo: Ammo
        while (i < maxOfAmmo) {
            ammo = createAmmo()
            ammoListNew.add(ammo)
            i++
        }
        ammoList = ammoListNew
        areThereAmmo = true
    }
    fun getAmmoForShooting(){
        var i = ammoList.lastIndex
        while (i > ammoList.lastIndex-fireType.burstSize){
            ammoListForShooting.add(ammoList[i])
            i--
        }
        ammoList = ammoList.dropLast(fireType.burstSize)
        areThereAmmo = ammoList.size >= fireType.burstSize
    }
}