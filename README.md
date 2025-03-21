# Riddle Game

A simple Java console-based riddle game that presents random riddles to the player, evaluates answers, provides hints, and tracks the player's score.

## Features

- Displays random riddles from a predefined collection
- Evaluates user answers (case-insensitive)
- Provides hints for incorrect responses
- Gives a second chance to answer after a hint
- Tracks and displays the user's score (1 point for correct first try, 0.5 points for correct after hint)
- Prevents riddles from repeating in a single game session
- Allows users to continue playing or exit the game

## How to Run

1. Make sure you have Java installed on your system
2. Compile the Java file:
   ```
   javac RiddleGame.java
   ```
3. Run the compiled program:
   ```
   java RiddleGame
   ```

## How to Play

1. The game will present you with a riddle
2. Type your answer and press Enter
3. If correct, you'll earn 1 point
4. If incorrect, you'll receive a hint and a second chance to answer
5. If correct on the second try, you'll earn 0.5 points
6. After each riddle, you can choose to continue or exit the game
7. Your final score will be displayed when you exit

## Extending the Game

To add more riddles, modify the `initializeRiddles()` method in the `RiddleGame.java` file by adding new Riddle objects with questions, answers, and hints.
