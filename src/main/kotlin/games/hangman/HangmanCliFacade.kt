package games.hangman

import common.GameFacade
import games.hangman.helpers.InMemoryNounBuilder
import games.hangman.helpers.NounBuilder
import games.hangman.middlewares.GuessCharacterInputIsAlphabeticValidator
import games.hangman.middlewares.GuessCharacterInputSizeValidator
import games.hangman.middlewares.GuessCharacterInputValidator

class HangmanCliFacade : GameFacade {
    private var hangmanScoreBoard: HangmanScoreBoard = HangmanScoreBoard()

    override fun play() {
        val playerName: String = getPlayerName()
        val difficultyId: Char = getDifficultyId()
        val guessCharacterInputValidator: GuessCharacterInputValidator = GuessCharacterInputIsAlphabeticValidator()
        guessCharacterInputValidator.setNextValidator(GuessCharacterInputSizeValidator())
        startSession(
            Hangman(
                HangmanPlayer(playerName),
                hangmanScoreBoard,
                getWord(difficultyId),
                guessCharacterInputValidator
            )
        )
    }

    private fun getDifficultyId(): Char {
        println("-Difficulties-")
        println("1. Easy")
        println("2. Medium")
        println("3. Hard")
        print("Select difficulty : ")
        val difficultyId: String? = readlnOrNull()
        if (difficultyId.isNullOrEmpty() ||
            difficultyId.length > 1 ||
            !arrayListOf('1', '2', '3').contains(difficultyId[0])
        ) {
            println("Enter correct difficulty!")
            return getDifficultyId()
        }
        return difficultyId[0]
    }

    private fun getWord(difficultyId: Char): String {
        val nounBuilder: NounBuilder = InMemoryNounBuilder()
        when (difficultyId) {
            '1' -> nounBuilder.setMinSize(4).setMaxSize(5)
            '2' -> nounBuilder.setMinSize(6).setMaxSize(7)
            '3' -> nounBuilder.setMinSize(8).setMaxSize(9)
        }
        return nounBuilder.toUpperCase().build()
    }

    private fun getPlayerName(): String {
        print("Enter name : ")
        val playerName: String? = readlnOrNull()
        return if (playerName.isNullOrEmpty()) {
            println("Enter correct name!")
            getPlayerName()
        } else {
            playerName
        }
    }

    private fun startSession(hangman: Hangman) {
        hangman.start()
        while (!hangman.isInEndingState()) {
            print("Enter Character: ")
            val guess: String = readln()
            hangman.guessCharacter(guess)
        }
        startOver()
    }

    private fun startOver() {
        print("Want to play again? (Y/N/H):")
        val nextStep: String? = readlnOrNull()
        if (nextStep.isNullOrEmpty() || nextStep.length > 1 || !arrayListOf('Y', 'N', 'H', 'y', 'n', 'h').contains(
                nextStep[0]
            )
        ) {
            println("Incorrect input!")
            startOver()
        } else {
            when (nextStep[0]) {
                'H', 'h' -> {
                    hangmanScoreBoard.displayTopKLeaders(5)
                    startOver()
                }

                'Y', 'y' -> {
                    play()
                }

                'N', 'n' -> {
                    println("Thanks for playing!")
                }
            }
        }
    }
}
