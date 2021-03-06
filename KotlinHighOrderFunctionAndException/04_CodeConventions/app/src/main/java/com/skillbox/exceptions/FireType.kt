package com.skillbox.exceptions

sealed class FireType(val burstSize: Int)
object Singles : FireType(1)
data class Burst(val _burstSize: Int) : FireType(burstSize = _burstSize)
