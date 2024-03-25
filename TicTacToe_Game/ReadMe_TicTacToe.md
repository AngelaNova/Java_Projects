# Tic Tac Toe Game

This Java program implements a simple Tic Tac Toe game where a player competes against an AI opponent. The game allows users to specify the board dimension and takes turns for each player to make a move.

## File
- **TicTacToe.java**: Contains the main class `TicTacToe` which implements the game logic. It includes methods to create the game board, display the board, allow users to make moves, let the AI make moves, check for winners, and manage the game flow.

## How to Use
1. Compile the `TicTacToe.java` file.
2. Run the `TicTacToe` class.

```bash
java TicTacToe
```

- Follow the prompts to enter your name and choose the board dimension.
- Input coordinates to make your move when prompted.

## Rules of the Game
- The game is played on a square grid of specified dimension (e.g., 3x3, 4x4, etc.).
- Players take turns placing their symbol ('x' for the user, 'o' for the AI) on an empty cell of the grid.
- The first player to form a horizontal, vertical, or diagonal line of their symbols wins the game.
- If all cells are filled and no player has won, the game is declared a tie.

## Features
- **Dynamic Board Size**: Players can choose the dimension of the board they want to play on.
- **Randomized First Move**: The game randomly decides which player (user or AI) goes first.
- **AI Opponent**: The AI makes moves based on simple strategies to either win the game or block the user from winning.
- **User Input Validation**: The program validates user input to ensure it is within the bounds of the board and that the chosen cell is empty.

**Note:** Ensure you have Java installed on your system to compile and run the program.
