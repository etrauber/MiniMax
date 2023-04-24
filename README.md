# Minimax project
## Background and Motivation
Nim is a two-player mathematical game, in which players alternate taking a certain amount of pieces off of a pile. For each turn, a player is able to take one, two, or three pieces off the pile, assuming there are three or more pieces on the pile. The goal of the game is to make the pile so that there is only one piece left, and subsequently the other player will have to take the last piece. The player who takes the last piece in the pile loses. 
Within our NimRunner code, we implemented a very important function, and evidently algorithm, called minimax. Minimax is essentially an algorithm that rates each move a player could possibly take as either 1 or -1, in order to determine if they player should make that given move. In order to implement the minimax function, we used an algorithm that would take in a move and the person's turn it is, and return if that move would be beneficial. In order to do this, we first created a base case that would use an if statement to determine if there are zero pieces left. If this is the case, the function would return 1 if it is playerX's turn and -1 if it is not, highlighting that if playerX took the last piece, represented by a -1, they lost but if it returns 1, playerX has won. However, recursion plays a large role in the rest of this algorithm. This is because in order to determine if a move is a good choice, we need to look through each of the possible successive moves, therefore requiring minimax to call itself. In the original function, a loop is needed to be called 3 times (so long as total pieces in greater than 3), in order to test the score of taking 3, 2, and 1 pieces. Then, within this loop, the function is recalled, while changing the number of pieces in the parameter to the total original pieces - number of pieces taken (indicated by the for loop), as well as changing the player. The function will eventually create a list of the possible scores assosiatced with the pieces taken, and depending on who's turn it is, it will return the minimum or maximum of these scores, indicating whether or not the player should make this move. 
//minimax recusivley determining how good a move is for a particular player - based on that can use to determine best move
characterstics minimax would work well for
- two player (one to maximize one to minimize)
- zero-sum game (meaning it has be a game where one loses and one wins)
- alternating turns (no two turns in a row)
Nim is a good test case of the algorithm of minimax because it represents a simplified two player game that requires a thoughtful process to determine how many pieces off the pile should be taken. This algorithm is able to spit out a 1 or -1, indicating if this move is smart, and evidently if the player should make it. As a result, it allows playerX to determine the best possible moves and beat their opponent almost every time. Subsequently, Nim is a great test case for minimax as it is also a game that allows us to manually determine if a move is smart or not, easily checking the credibility of our minimax function (thought in a slower manner than the method itself). 

## Usage
In order to run this program, two .java files are needed. The first file needed is a NimRunner file. This should hold the variables and methods needed to implement the Nim game, including minimax, bestMove, getXMove, and getYMove...ADD HOW TO RUN FULL NIM GAME
- run NimRunner class w/ no parameters - add public static void main to run game
    - run game by running class --> if i want to add user input for how many pieces on the board

## Future Contributions
In order to improve my code,...ADD IN LATER
- make more generic for other games - in addrtion to having nim runner, i could now have another game runner to use bestGame and minimax - what do u need to do that and what would it require - what game
- efficiency of algorithm - would need to be more generic making it less efficient for speicific games - if branching factor is bigger exponentially bigger
    - chess or checkers

# lines needed to be adapted for complex Nim
- for loops that only cycle throguh 3 times - as we said in simple nim can only take 3 pieces at most at a time
    - need to now generate an arrayList of an arrayList
- psuedocode for generating an arraylist of arraylist of moves
    // outside for loop run the amount of piles
        //inner for loop should run the amount of pieces in each pile
            //create moves up to the amount of pieces = 0 for that said pile - save that move to arraylist of arraylist
Puesdocode for function getPossibleMoves(state (which is piles))
1) set up an empty arraylist of arraylist of ints, called moves
2) for loop to iterate through the indexes through state - iterate through amount of piles (state = num of piles and amount in each pile in arraylist)
    3) for loop through # of possible pieces u can take from the speicifc pile (indicated by index of outer for loop) - can take up to numPieces
    - could also be a while loop that runs numPieces
        4) make a new arraylist of integers - one move - same amount of indeces as state
        - if arraylist need a loop to add 4 spots w 0's or can make array with 4 slots 
        5)at the index you are at in state (in outer for loop - pile index): add the # of pieces wanting to take for that move
        6) add oneMove arraylist to moves arraylist of arraylists
- in getBestMove - loop through possible moves instead of 3 times
- in minimax in loop - change loop from going through 3 moves
    - also a place where checking if it's a base case - need to change to check if each pile is zero
    - iterate through moves with a for each loop
        - for each move - need to figure out next state --> do that by subtracting from given pile
        - then call minimax again recursivley - instead of int thats the state - call arraylist


- change number of pieces that is hard coded 
    - change from int to an array list with the num of pieces in each pile - # index = number of piles 
    - num pieces should now = state as an arraylist 
        - arraylist over array to easily delete piles once they are empty
- move would now need to be another arraylist - first num = index of pile in first arraylist, second num = num of pieces wanted to take away
    - or have a parallel array and indicate how many pieces and put in same index as first array
    -ex/ if piles = [1,3,5,7]
            move = [0,0,3,0] - taking 3 pieces from pile with 5 
            piles = [1,3,2,7]
