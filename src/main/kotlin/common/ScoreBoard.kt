package common

interface ScoreBoard<T> {
    fun display()
    fun displayTopKLeaders(k: Int)
    fun addResult(result: T)
    fun clear()
}
