package games.hangman.middlewares

interface GuessCharacterInputValidator {
    fun setNextValidator(nextValidator: GuessCharacterInputValidator)
    fun validate(input: String): Boolean
}