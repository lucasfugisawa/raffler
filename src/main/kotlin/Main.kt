package com.fugisawa

import java.security.SecureRandom

// Create a single SecureRandom instance to be reused across all calls
private val random = SecureRandom()

/**
 * Generates random integers within the specified range [start, end] (inclusive).
 * Uses SecureRandom for cryptographically strong random number generation.
 * Ensures all generated numbers are unique (no duplicates).
 *
 * @param start The lower bound of the range (inclusive)
 * @param end The upper bound of the range (inclusive)
 * @param numberOfRandoms The number of random integers to generate (default is 1)
 * @return An array of random integers within the specified range
 * @throws IllegalArgumentException if start is greater than end
 * @throws IllegalArgumentException if numberOfRandoms is greater than the possible range of values
 */
fun randomInt(start: Int, end: Int, numberOfRandoms: Int = 1): IntArray {
    require(start <= end) { "Start value must be less than or equal to end value" }
    require(numberOfRandoms > 0) { "Number of randoms must be greater than zero" }

    if (start == end) {
        return IntArray(numberOfRandoms) { start }
    }

    val rangeSize = end - start + 1
    require(numberOfRandoms <= rangeSize) { "Cannot generate $numberOfRandoms unique numbers in range $start..$end (range size: $rangeSize)" }

    if (numberOfRandoms == 1) {
        return intArrayOf(random.nextInt(rangeSize) + start)
    }

    val result = mutableSetOf<Int>()

    while (result.size < numberOfRandoms) {
        result.add(random.nextInt(rangeSize) + start)
    }

    return result.toIntArray()
}

/**
 * Displays a progress bar in the console to simulate calculation.
 *
 * @param totalSteps The total number of steps in the progress bar
 * @param delayMs The delay between steps in milliseconds
 */
fun showFakeButCoolProgressBar(totalSteps: Int = 30, delayMs: Int = 80) {
    println("And the reward goes to...")
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
    val numberOfWinners = 1

    println("Raffle range: [$min..$max]")
    println("Number of winners: $numberOfWinners")

    showFakeButCoolProgressBar()

    val randomNumbers = randomInt(min, max, numberOfWinners)
    println("... the lucky people in rows: ${randomNumbers.joinToString(", ")} 🎉🎉🎉")
}
