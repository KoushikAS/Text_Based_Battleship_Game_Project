# Battleship Game Project (Text-Based Java Implementation)

## Overview

This project is a Java-based implementation of the classic game "Battleship", completed as part of an academic assignment. The game is played in a text interface, where two players engage in naval warfare by positioning ships and attempting to locate and destroy the opponent's fleet. This implementation adds unique twists to the traditional Battleship gameplay, including various ship types and special actions. If you are not familiar with the game, you can read about it here:

 https://en.wikipedia.org/wiki/Battleship_(game)

## Features

### Core Game Mechanics
- **Grid-Based Gameplay:** A 20x10 grid (A0 to T9) for ship placement and tracking.
- **Ship Variety:** Includes Submarines, Destroyers, Battleships, and Carriers, each with unique sizes.
- **Two-Player Mode:** Support for two players in a turn-based format.

### Enhanced Gameplay (Version 2)
- **Advanced Ship Types:** Battleships and Carriers have unique shapes with four orientation options (Up, Right, Down, Left).
- **Special Actions:**
  - **Move Ship:** Allows repositioning of ships a limited number of times.
  - **Sonar Scan:** Provides hints about the opponent's ship locations within a certain radius.

## Installation and Running the Game

### Prerequisites
- Java JDK 8 or higher.

### Installation

1. Clone the repository to your local machine:

```sh
git clone https://github.com/KoushikAS/pomelo-test.git
cd pomelo-test
```

### Running the Game

```sh
./gradlew installDist
app/build/install/app/bin/app
```

## How to Play

1. **Start the Game:** Choose between two-player or player vs. computer mode.
2. **Place Your Ships:** Enter coordinates and orientations to place your ships on the grid.
3. **Take Turns:** Each player takes turns guessing the location of the opponent's ships.
4. **Use Special Actions:** Utilize move and sonar scan actions strategically.
5. **Winning the Game:** The first player to sink all opponent's ships wins.

## Contributions

This project was completed as part of an academic assignment with requirments provided [https://gitlab.oit.duke.edu/adh39/ece651-spr23/-/tree/master/hwk1-battleship?ref_type=heads](here). Contributions were made solely by Koushik Annareddy Sreenath, adhering to the project guidelines and requirements set by the course ECE-651 Software Engineering. 

## License

This project is licensed under the [MIT License](LICENSE).

## Acknowledgments

- Thanks to Andrew Hilton and the course staff for providing guidance and support throughout the project.
- Inspired by the classic Battleship game.

