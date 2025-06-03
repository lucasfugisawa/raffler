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

/**
 * Displays a progress bar in the console to simulate calculation.
 *
 * @param totalSteps The total number of steps in the progress bar
 * @param delayMs The delay between steps in milliseconds
 */
fun showProgressBar(totalSteps: Int = 30, delayMs: Int = 80) {
    println("And the JetBrains IDE license goes to...")
    print("Generating: [")
    for (i in 1..totalSteps) {
        print("=")
        System.out.flush()
        Thread.sleep(delayMs.toLong())
    }
    println("] Done!")
}

fun main() {
    val min = 1
    val max = 100

    println("Raffle range: [$min..$max]")

    showProgressBar()

    val randomNumber = randomInt(min, max)
    println("... the lucky person in row: $randomNumber 🎉🎉🎉")
}
