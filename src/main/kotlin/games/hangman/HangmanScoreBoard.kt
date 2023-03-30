package games.hangman

import common.ScoreBoard

class HangmanScoreBoard : ScoreBoard<HangmanPlayer> {
    private val hangmanPlayerComparator: Comparator<HangmanPlayer> = Comparator<HangmanPlayer> { p1, p2 ->
        p2.lives.compareTo(p1.lives)
    }

    private var scoreBoard: MutableList<HangmanPlayer> = mutableListOf()

    override fun display() {
        scoreBoard.sortedWith(hangmanPlayerComparator).forEachIndexed { index, hangmanPlayer ->
            println("${index+1}) ${hangmanPlayer.name} - ${hangmanPlayer.lives}")
        }
    }

    override fun displayTopKLeaders(k: Int) {
        if(scoreBoard.isEmpty()) {
            println("Scoreboard empty!")
            return
        }
        scoreBoard.sortedWith(hangmanPlayerComparator).forEachIndexed { index, hangmanPlayer ->
            if(index >= k)
                return
            println("${index+1}) ${hangmanPlayer.name} - ${hangmanPlayer.lives}")
        }
    }

    override fun addResult(result: HangmanPlayer) {
        scoreBoard.add(result)
    }

    override fun clear() {
        scoreBoard = mutableListOf()
    }

}
