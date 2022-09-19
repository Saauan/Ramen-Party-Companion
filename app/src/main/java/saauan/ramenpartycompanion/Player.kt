package saauan.ramenpartycompanion

data class Player(val name: String) {
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
