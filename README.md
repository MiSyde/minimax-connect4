# minimax-connect4🟡🔴
Project work for the "VIIIAB00" course at Budapest University of Technology and Economics. An implementation of the game "Connect 4" written in Java, with the enemy bot using minimax algorithm with alpha-beta pruning.🤖

## Gamemodes
- Local PVP
- Local PVE

## Saving 
If the player leaves the game before it ends, it seralizes the game's current state into a json file and upon selecting the mode the user left, it loads it back in.

## Graphical elements
The game's GUI is implemented using Swing.\
The board uses relative sizing, so it can be stretched in any way - the coins and board widen and tighten in a way that all rows and columns are equal height and width.\
Upon winning, the winning player's coins that they scored the Connect4 with, start blinking.
