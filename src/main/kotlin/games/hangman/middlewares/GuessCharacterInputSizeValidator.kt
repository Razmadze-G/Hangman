package games.hangman.middlewares

class GuessCharacterInputSizeValidator : GuessCharacterInputValidator {
    private var nextValidator: GuessCharacterInputValidator? = null

    override fun setNextValidator(nextValidator: GuessCharacterInputValidator) {
        this.nextValidator = nextValidator
    }

    override fun validate(input: String): Boolean {
        if (input.length != 1)
            return false

        return nextValidator?.validate(input) ?: true
    }
}