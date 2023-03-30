package games.hangman.helpers

class InMemoryNounBuilder : NounBuilder {
    private var shouldBeInUpperCase: Boolean = false
    private var nouns: Array<String> = arrayOf(
        "Able", "Bite", "Chat", // 4-letter words
        "Amber", "Bread", "Chant", // 5-letter words
        "Assert", "Bubble", "Canyon", // 6-letter words
        "Almanac", "Blizzard", "Captain", // 7-letter words
        "Ambition", "Building", "Campaign", // 8-letter words
        "Adjective", "Blueprint", "Character" // 9-letter words
    )

    override fun setMinSize(x: Int): InMemoryNounBuilder {
        nouns = nouns.filter { it.length >= x }.toTypedArray()
        return this
    }

    override fun setMaxSize(x: Int): InMemoryNounBuilder {
        nouns = nouns.filter { it.length <= x }.toTypedArray()
        return this
    }

    override fun toUpperCase(): InMemoryNounBuilder {
        shouldBeInUpperCase = true
        return this
    }

    override fun build(): String {
        val randomIndex = nouns.indices.random()
        return if (shouldBeInUpperCase) nouns[randomIndex].uppercase() else nouns[randomIndex]
    }
}
