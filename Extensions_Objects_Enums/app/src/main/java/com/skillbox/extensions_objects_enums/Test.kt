package com.skillbox.extensions_objects_enums




fun main(){
    println("Добро пожаловать на ВОЙНУ")
    println("Введите число бойцов в команде")
    val numberOfWarriors = readLine()?.toInt() ?: 10
    println("Битва Начинается")
    val battle = Battle(Team(numberOfWarriors), Team(numberOfWarriors))
    var state = battle.getBattleState()
    println("Сумма HP красных ${state.sumHPRed}, сумма HP синих ${state.sumHPBlue}")
    while (!battle.isOver){
        battle.step()
        state = battle.getBattleState()
        if (!battle.isOver){
            println("Сумма HP красных ${state.sumHPRed}, сумма HP синих ${state.sumHPBlue}")
        }
    }
}
