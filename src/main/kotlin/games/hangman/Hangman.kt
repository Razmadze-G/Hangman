package games.hangman

import common.Game
import games.hangman.middlewares.GuessCharacterInputValidator

class Hangman(
    private var player: HangmanPlayer,
    private var scoreBoard: HangmanScoreBoard,
    private val word: String,
    private val guessCharacterInputValidator: GuessCharacterInputValidator
) : Game {
    private var alreadyUsedCharacters: HashSet<Char> = HashSet()

    override fun start() {
        println("Hello ${player.name}, Let's play Hangman!")
        println("Game is starting...")
        printRemainingLives()
        printCurrentWordState()
    }

    fun guessCharacter(c: String) {
        if (!guessCharacterInputValidator.validate(c)) {
            println("Please, enter a valid character!")
            return
        }

        val guess: Char = c[0].uppercaseChar()

        if (wasCharacterAlreadyUsed(guess)) {
            println("You already tried this character")
            return
        }

        alreadyUsedCharacters.add(guess)
        if (!word.contains(guess)) {
            println("There is no such character")
            player.lives -= 1
        } else {
            println("Yes, it is there!!!")
            printCurrentWordState()
        }

        if (isInEndingState()) {
            end()
        } else {
            printRemainingLives()
        }
    }

    fun isInEndingState(): Boolean {
        if (player.lives == 0)
            return true

        for (character: Char in word) {
            if (!alreadyUsedCharacters.contains(character))
                return false
        }

        return true
    }

    private fun end() {
        if (player.lives == 0)
            println("Sorry, you lost… The word was: $word")
        else {
            scoreBoard.addResult(player)
            println("Congratulations!")
        }

    }

    private fun printCurrentWordState() {
        var currentWord = ""

        for (character: Char in word) {
            if (!alreadyUsedCharacters.contains(character)) {
                currentWord += "_"
                continue
            }
            currentWord += character
        }
        println("Current Word is: $currentWord")
    }

    private fun printRemainingLives() {
        println("Lives remaining: ${player.lives}")
    }

    private fun wasCharacterAlreadyUsed(c: Char): Boolean {
        return alreadyUsedCharacters.contains(c)
    }
}
