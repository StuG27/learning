package com.skillbox.extensions_objects_enums

abstract class AbstractWeapon(private val maxOfAmmo: Int, private val fireType: FireType) {
    private var ammoList: List<Ammo> = listOf()
    var areThereAmmo = ammoList.size >= fireType.burstSize

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
    }
    fun getAmmoForShooting(): List<Ammo>{
        var i = ammoList.lastIndex
        var ammoListForShooting: MutableList<Ammo> = mutableListOf()
        while (i > ammoList.lastIndex-fireType.burstSize){
            ammoListForShooting.add(ammoList[i])
            i--
        }
        ammoList = ammoList.dropLast(fireType.burstSize)
        return ammoListForShooting
    }
}