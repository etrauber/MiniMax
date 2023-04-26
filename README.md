# Minimax project
## Background and Motivation
Nim is a mathematical game, in which two players alternate taking a certain amount of pieces off of piles. For each turn, a player is able to take any number of pieces off a pile, however, are not allowed to take pieces off of multiple piles at a time. The goal of the game is to make the last pile have only piece left, which will force the other player to take the last piece, which is how they lose.  
Within our NimRunner code, we implemented a very important function, and evidently an algorithm, called minimax. Minimax is an algorithm that uses recursion to determine how good a move is for a particular player. In order for minimax to work, it must be used to determine the rating of moves for a two player game. As well, for this algorithm to work, it needs to be implemeted for a zero-sum game, meaning one player must win and one player must lose. Finally, the last requierment for this algorithm is that the two players must take alternating turns. In order to implement the minimax function, we used an algorithm that would take in a move and whose turn it is, and return a number indicating if that move would be beneficial. In order to do this, we first created a base case that would use an if statement to determine if there are zero pieces left. If this is the case, the function would return 1 if it is playerX's turn and -1 if it is not, highlighting that if playerX took the last piece, represented by a -1, they lost but if it returns 1, playerX has won. However, recursion plays a large role in the rest of this algorithm. This is because in order to determine if a move is a good choice, we need to look through each of the possible successive moves, therefore requiring minimax to call itself. The function will eventually create a list of the possible scores assosiatced with the possible pieces that could subsequently be take after a move, and depending on whose turn it is, it will return the minimum or maximum of these scores, indicating whether or not the player should make this move. 
Nim is a good test case of the algorithm of minimax because it represents a simplified two player game that requires a thoughtful process to determine how many pieces off the pile should be taken. Minimax is able to spit out a 1 or -1, indicating if this move is smart for the given player. As a result, it allows playerX to determine the best possible move and therefore have the best chance to beat their opponent.
 
## Usage
In order to run this program, two .java files are needed. The first file needed is a NimRunner file. This file holds the methods needed to implement the Nim game, including minimax, bestMove, getXMove, getYMove, and runGame. In order to run this code, a user could either run the Tests.java file which calls runGame in NimRunner, or they can directly call the NimRunner class, which will call upon the runGame method in the public static void main. The user can either type javac Tests.java, followed by java Tests to the command line, or they can type javac NimRunner.java, followed by java NimRunner (both will work to produce a game). As well, if they want to alter the amount of pieces in each pile, they will have to either alter it in Tests.java or in the static void main method of NimRunner.java.  

## Future Contributions
In order to improve my code, it would be helpful to make it more generic to other games. runGame currently will run a game a of Nim; however, to add to my code it could include another runGame function that would be specific for a different game that could use the minimax and bestMove functions that are already coded. Another game that could be include would be the game of checkers. However, this may be a bit difficult because of the branching factor. In order to implement a game like this, someone would need to improve the efficiency of the algorithm. By making it less specific, it would be less efficient for certain games, including checkers and chess, as the branching facotr is bigger, which therefore exponentially increases the amount of pieces needed to check. 

