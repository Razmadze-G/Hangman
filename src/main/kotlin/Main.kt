import common.GameFacade
import java.lang.Exception

fun main(args: Array<String>) {
    println("-Games-")
    println("1. Hangman")
    println("2. TicTacToe")
    print("Choose game to play : ")
    val gameId: String? = readlnOrNull()
    if (gameId.isNullOrEmpty() || gameId.length > 1 || !arrayListOf('1', '2').contains(gameId[0])) {
        println("Incorrect input!")
        main(args)
    } else {
        try {
            val gameFacade: GameFacade = GameFacadeFactory.getGameFacade(gameId[0])
            gameFacade.play()
        } catch (_: NotImplementedError){
            println("game not implemented!")
            main(args)
        } catch (_: Exception){
            println("unexpected error")
        }
    }
}
