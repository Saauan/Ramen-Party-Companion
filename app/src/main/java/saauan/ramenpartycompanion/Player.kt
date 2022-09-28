package saauan.ramenpartycompanion

import java.io.Serializable

data class Player(val name: String) : Serializable {
    var score = 0
    fun incrementScore() {
        score++
    }

    fun decrementScore() {
        score--
    }

    fun resetScore() {
        score = 0
    }
}
