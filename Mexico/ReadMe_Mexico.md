# Mexico Game

This Java program simulates the Mexico dice game. Players, represented as "Giulia" and "David", take turns rolling two dice and comparing scores to determine the winner. The game continues until one player runs out of funds.

## File
- **Mexico.java**: Contains the main class `Mexico` which implements the game logic. It includes methods to roll dice, calculate scores, play rounds, determine the winner, and manage the game flow.

## How to Use
1. Compile the `Mexico.java` file.
2. Run the `Mexico` class, providing two command-line arguments: the initial buy-in amount and the bet amount for each round.

```bash
java Mexico <buyIn> <bet>
```

- `<buyIn>`: Initial amount each player brings into the game.
- `<bet>`: Amount each player bets for each round.

## Rules of the Game
- Each player rolls two dice and calculates their score based on the numbers rolled.
- Scores are calculated according to the rules of the Mexico game:
  - If the two dice show different numbers, the score is the smaller number in the one's place, and the larger number in the ten's place. For example, if the dice show 3 and 5, the score is 53.
  - If the two dice show the same number, the score is a double. For example, if both dice show 4, the score is 44.
- The player with the higher score wins the round. If scores are tied, the round is a tie.
- The game continues until one player runs out of funds or is unable to bet.


**Note:** Ensure you have Java installed on your system to compile and run the program.
