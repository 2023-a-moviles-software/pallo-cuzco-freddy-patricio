package com.freddywin.artistmusic

class RandomUnrepeated(from: Int, to: Int) {
    private val numbers = (from..to).toMutableList()
    fun nextInt(): Int {
        val index = kotlin.random.Random.nextInt(numbers.size)
        val number = numbers[index]
        numbers.removeAt(index)
        return number
    }
}