package com.skillbox.exceptions

sealed class BattleState

data class Progress(val redTeam: Team, val blueTeam: Team) : BattleState(){
    var sumHPBlue = 0
        get() = blueTeam.membersList.sumBy { it.currentHP }
//        get() = blueTeam.sumHP()

    var sumHPRed = 0
        get() = redTeam.membersList.sumBy { it.currentHP }
//        get() = redTeam.sumHP()
}
object TeamRedWin : BattleState()
object TeamBlueWin : BattleState()
object Draw : BattleState()
