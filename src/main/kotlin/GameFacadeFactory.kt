import common.GameFacade
import games.hangman.HangmanCliFacade
import games.titactoe.TicTacToeCliFacade

class GameFacadeFactory {

    companion object {
        // This function is kinda cringe
        fun getGameFacade(gameId: Char): GameFacade {
            if (gameId == '1') {
                return HangmanCliFacade()
            }
            return TicTacToeCliFacade()
        }
    }
}