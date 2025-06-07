# Raffler

A simple raffler application used during [Kotlin Devs Brasil KUG](https://kotlinbr.dev/) online events.

## Description

This application generates random numbers within a specified range to select winners during online events. It uses SecureRandom for cryptographically strong random number generation and ensures all generated numbers are unique (no duplicates).

## Features

- Generate random integers within a specified range
- Ensure uniqueness of generated numbers
- Display a cool progress bar animation in the console
- Simple and easy to use

## How to Use

1. Clone the repository
2. Build the project using Gradle
3. Run the application

By default, the application will generate 1 random number between 1 and 100. You can modify these parameters in the `main` function in `Main.kt`.

```kotlin
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
```

## Requirements

- JDK 21 or higher
- Kotlin 2.1.20 or higher

## Installation

```bash
# Clone the repository
git clone https://github.com/yourusername/raffler.git

# Navigate to the project directory
cd raffler

# Build the project
./gradlew build

# Run the application
./gradlew run
```

## About Kotlin Devs Brasil KUG

[Kotlin Devs Brasil KUG](https://kotlinbr.dev/) is a Kotlin User Group in Brazil that organizes events, meetups, and discussions about Kotlin programming language.

## License

This project is open source and available under the [MIT License](LICENSE).