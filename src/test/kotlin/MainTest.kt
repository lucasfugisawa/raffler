package com.fugisawa

import kotlin.test.*

class MainTest {

    @Test
    fun `randomInt returns value within specified range`() {
        val start = 1
        val end = 100

        // Test multiple times to ensure randomness works correctly
        repeat(100) {
            val result = randomInt(start, end)[0]
            assertTrue(result in start..end, "Random value $result should be between $start and $end")
        }
    }

    @Test
    fun `randomInt throws exception when start is greater than end`() {
        val start = 100
        val end = 1

        val exception = assertFailsWith<IllegalArgumentException> {
            randomInt(start, end)
        }

        assertEquals("Start value must be less than or equal to end value", exception.message)
    }

    @Test
    fun `randomInt returns start value when start equals end`() {
        val start = 42
        val end = 42

        val result = randomInt(start, end)[0]
        assertEquals(start, result, "When start equals end, the function should return that value")
    }

    @Test
    fun `randomInt handles negative numbers correctly`() {
        val start = -100
        val end = -1

        repeat(50) {
            val result = randomInt(start, end)[0]
            assertTrue(result in start..end, "Random value $result should be between $start and $end")
        }
    }

    @Test
    fun `randomInt handles zero in range correctly`() {
        val start = -50
        val end = 50

        // Test multiple times to increase chance of getting zero
        var foundZero = false
        repeat(100) {
            val result = randomInt(start, end)[0]
            assertTrue(result in start..end, "Random value $result should be between $start and $end")
            if (result == 0) foundZero = true
        }

        // Note: This is a probabilistic test. It might fail occasionally,
        // but with 100 attempts in range -50 to 50, it's very likely to find 0 at least once.
        assertTrue(foundZero, "Zero should be possible in the range $start to $end")
    }

    @Test
    fun `randomInt distribution is reasonably uniform`() {
        val start = 1
        val end = 10
        val iterations = 10000
        val expectedCount = iterations / (end - start + 1)
        val tolerance = expectedCount * 0.2 // Allow 20% deviation

        val counts = IntArray(end - start + 1)

        repeat(iterations) {
            val result = randomInt(start, end)[0]
            counts[result - start]++
        }

        // Check that each number appears approximately the expected number of times
        for (i in start..end) {
            val count = counts[i - start]
            assertTrue(
                count > expectedCount - tolerance && count < expectedCount + tolerance,
                "Count for $i was $count, expected around $expectedCount ± $tolerance"
            )
        }
    }

    @Test
    fun `randomInt returns array with specified number of randoms`() {
        val start = 1
        val end = 100
        val numberOfRandoms = 10

        val result = randomInt(start, end, numberOfRandoms)

        assertEquals(numberOfRandoms, result.size, "Should return array with $numberOfRandoms elements")

        // Check all values are within range
        for (value in result) {
            assertTrue(value in start..end, "Random value $value should be between $start and $end")
        }
    }

    @Test
    fun `randomInt returns array with unique values`() {
        val start = 1
        val end = 100
        val numberOfRandoms = 50

        val result = randomInt(start, end, numberOfRandoms)

        // Check for uniqueness
        val uniqueValues = result.toSet()
        assertEquals(numberOfRandoms, uniqueValues.size, "All values in the array should be unique")
    }

    @Test
    fun `randomInt throws exception when numberOfRandoms is greater than range size`() {
        val start = 1
        val end = 10
        val numberOfRandoms = 20 // More than the range size (10)

        val exception = assertFailsWith<IllegalArgumentException> {
            randomInt(start, end, numberOfRandoms)
        }

        assertTrue(exception.message!!.contains("Cannot generate $numberOfRandoms unique numbers"), 
            "Exception message should indicate that the number of randoms exceeds the range")
    }

    @Test
    fun `randomInt throws exception when numberOfRandoms is zero or negative`() {
        val start = 1
        val end = 100

        val exception = assertFailsWith<IllegalArgumentException> {
            randomInt(start, end, 0)
        }

        assertEquals("Number of randoms must be greater than zero", exception.message)

        val exceptionNegative = assertFailsWith<IllegalArgumentException> {
            randomInt(start, end, -5)
        }

        assertEquals("Number of randoms must be greater than zero", exceptionNegative.message)
    }

    @Test
    fun `randomInt returns array with all values equal to start when start equals end`() {
        val start = 42
        val end = 42
        val numberOfRandoms = 5

        val result = randomInt(start, end, numberOfRandoms)

        assertEquals(numberOfRandoms, result.size, "Should return array with $numberOfRandoms elements")

        // All values should be equal to start/end
        for (value in result) {
            assertEquals(start, value, "When start equals end, all values should be equal to that value")
        }
    }
}
