package games.hangman

import common.Player

data class HangmanPlayer(
    override val name: String,
    var lives: Int = 8,
) : Player
