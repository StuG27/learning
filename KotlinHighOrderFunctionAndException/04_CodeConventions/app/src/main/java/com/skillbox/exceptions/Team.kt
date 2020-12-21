package com.skillbox.exceptions

import com.skillbox.exceptions.warriors.*

class Team(private val numberOfWarriors: Int) {
    var membersList: MutableList<AbstractWarrior> = mutableListOf()

    init {
        var i = 0
        var probability = 30
        while (i < numberOfWarriors) {
            when {
                probability.isChanceRealized() -> membersList.add(Sniper())
                probability.isChanceRealized() -> membersList.add(MachineGunner())
                (probability + 20).isChanceRealized() -> membersList.add(SubmachineGunner())
                else -> membersList.add(Rifleman())
            }
            probability = 30
            i++
        }
    }
//    fun sumHP() : Int{
//        var i =0
//        var sumHP = 0
//        while (i < numberOfWarriors){
//            if (membersList[i].currentHP > 0){
//                sumHP += membersList[i].currentHP
//            }
//            i++
//        }
//        return sumHP
//    }
}
