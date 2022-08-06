package saauan.ramenpartycompanion

data class Player(val name: String) {
    var score = 0
    fun incrementScore() {
        score++
    }

    fun reset() {
        score = 0
    }
}
