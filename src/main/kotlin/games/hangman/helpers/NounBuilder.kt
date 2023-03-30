package games.hangman.helpers

interface NounBuilder {
    fun setMinSize(x: Int): NounBuilder
    fun setMaxSize(x: Int): NounBuilder
    fun toUpperCase(): NounBuilder
    fun build(): String
}
