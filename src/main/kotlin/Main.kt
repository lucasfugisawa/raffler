package com.fugisawa

import java.security.SecureRandom

/**
 * Generates a random integer within the specified range [start, end] (inclusive).
 * Uses SecureRandom for cryptographically strong random number generation.
 *
 * @param start The lower bound of the range (inclusive)
 * @param end The upper bound of the range (inclusive)
 * @return A random integer within the specified range
 * @throws IllegalArgumentException if start is greater than end
 */
fun randomInt(start: Int, end: Int): Int {
    require(start <= end) { "Start value must be less than or equal to end value" }

    if (start == end) return start

    val random = SecureRandom()
    return random.nextInt(end - start + 1) + start
}

fun main() {
    // Example usage of the randomizer
    val min = 1
    val max = 100

    println("Generating a random number between $min and $max:")
    val randomNumber = randomInt(min, max)
    println("Random number: $randomNumber")
}
