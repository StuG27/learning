package com.skillbox.extensions_objects_enums

import kotlin.random.Random


fun main(){
//    println("Добро пожаловать на ВОЙНУ")
//    println("Введите число бойцов в команде")
//    val numberOfWarriors = readLine()?.toInt() ?: 10
//    println("Битва Начинается")
//    val battle = Battle(Team(numberOfWarriors), Team(numberOfWarriors))
//    var state = battle.getBattleState()
//    println("Сумма HP красных ${state.sumHPRed}, сумма HP синих ${state.sumHPBlue}")
//    while (!battle.isOver){
//        battle.step()
//        state = battle.getBattleState()
//        if (!battle.isOver){
//            println("Сумма HP красных ${state.sumHPRed}, сумма HP синих ${state.sumHPBlue}")
//        }
//    }
    var numberOfWarriors: Int
    var drawCount = 0
    var teamBlueWinCount = 0
    var teamRedWinCount = 0
    for (i in 0..9){
        numberOfWarriors = Random.nextInt(20) + 10
        val battle = Battle(Team(numberOfWarriors), Team(numberOfWarriors))
        var state = battle.getBattleState()
        if (state is Progress){
            println("Количество бойцов в команде $numberOfWarriors Сумма HP красных ${state.sumHPRed}, сумма HP синих ${state.sumHPBlue}")
        }
        while (state is Progress){
            battle.step()
            state = battle.getBattleState()
            when (state) {
                is Progress -> println("Сумма HP красных ${state.sumHPRed}, сумма HP синих ${state.sumHPBlue}")
                is TeamBlueWin -> {
                    println("Синие победили")
                    teamBlueWinCount++
                }
                is TeamRedWin -> {
                    println("Красные победили")
                    teamRedWinCount++
                }
                is Draw -> {
                    println("Ничья")
                    drawCount++
                }
            }
        }
    }
    println("Побед синих $teamBlueWinCount, побед красных $teamRedWinCount, ничьих $drawCount")
}
