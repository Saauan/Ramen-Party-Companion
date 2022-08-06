package saauan.ramenpartycompanion

data class Player(var score: Int) {
    fun incrementScore() {
        score++
    }

    fun reset() {
        score = 0
    }
}
