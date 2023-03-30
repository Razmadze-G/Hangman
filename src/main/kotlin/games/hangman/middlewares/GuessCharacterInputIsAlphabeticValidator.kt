package games.hangman.middlewares

class GuessCharacterInputIsAlphabeticValidator : GuessCharacterInputValidator {
    private var nextValidator: GuessCharacterInputValidator? = null

    override fun setNextValidator(nextValidator: GuessCharacterInputValidator) {
        this.nextValidator = nextValidator
    }

    override fun validate(input: String): Boolean {
        if (!input.all { it.isLetter() })
            return false

        return nextValidator?.validate(input) ?: true
    }
}