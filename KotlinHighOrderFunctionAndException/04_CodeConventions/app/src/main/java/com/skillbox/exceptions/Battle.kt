package com.skillbox.exceptions

class Battle(private val redTeam: Team, private val blueTeam: Team) {
    var isOver: Boolean = false
    fun getBattleState(): BattleState {
        var isRedAlive = isAlive(redTeam)
        var isBlueAlive = isAlive(blueTeam)
        return if (isRedAlive && isBlueAlive) {
//            println("Все живы")
            Progress(redTeam, blueTeam)
        }
        else if (isRedAlive) {
            isOver = true
//            println("Победа Красных")
            TeamRedWin
        }
        else if (isBlueAlive) {
            isOver = true
//            println("Победа Синих")
            TeamBlueWin
        }
        else {
            isOver = true
//           println("Ничья")
            Draw
        }
    }
    fun step(){
        redTeam.membersList.shuffle()
        blueTeam.membersList.shuffle()
        var i = 0
        while (i < redTeam.membersList.size){
            if (i % 2 == 0){
                if (!blueTeam.membersList[i].isKilled){
                    blueTeam.membersList[i].toAttack(redTeam.membersList[i])
                }
                if (!redTeam.membersList[i].isKilled){
                    redTeam.membersList[i].toAttack(blueTeam.membersList[i])
                }
            }
            else{
                if (!redTeam.membersList[i].isKilled){
                    redTeam.membersList[i].toAttack(blueTeam.membersList[i])
                }
                if (!blueTeam.membersList[i].isKilled){
                    blueTeam.membersList[i].toAttack(redTeam.membersList[i])
                }
            }
            i++
        }
    }
    private fun isAlive(team: Team): Boolean{
        var i = 0
        while (i < team.membersList.size){
            if (!team.membersList[i].isKilled){
                return true
            }
            i++
        }
        return false
    }
}