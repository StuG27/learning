package com.skillbox.extensions_objects_enums

sealed class BattleState() {
    abstract val sumHPRed: Int
    abstract val sumHPBlue: Int
}

data class Progress(val redTeam: Team,  val blueTeam: Team) : BattleState(){
    override var sumHPBlue = 0
    get() = blueTeam.sumHP()
    override var sumHPRed = 0
    get() = redTeam.sumHP()
}
object TeamRedWin : BattleState(){
    override var sumHPRed = 0
    override var sumHPBlue = 0
}
object TeamBlueWin : BattleState(){
    override var sumHPRed = 0
    override var sumHPBlue = 0
}
object Draw : BattleState(){
    override var sumHPBlue = 0
    override var sumHPRed = 0
}