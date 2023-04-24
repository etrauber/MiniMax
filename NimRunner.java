import java.util.*;
public class NimRunner{
    //delcaring instance variables of player(true if player = x, false if not) and number of pieces on the pile (int)
    boolean player;
    public static boolean runGame(){
        int numPieces = 5;
        while(numPieces > 0){
            int xMove = getXMove(numPieces);
            numPieces = numPieces - xMove;
            if(numPieces == 0){
                return false;
            } else {
              int yMove = getYMove(numPieces);
                numPieces = numPieces - yMove;
              if(numPieces == 0){
                return true;
              }  
            }
        }
        //default return value
        return true;
    }
    
    //function to determine what move would be best given who's turn it is - return num pieces to be taken off pile by said player
    public static int bestMove(int numPieces, boolean turn){
        //declare and initalize move with default value
        int move = 1;
        //for loop to run 3 times, by checking what happens if 1, 2, or 3 pieces are taken off 
        for(int pieces=1; pieces<=3; pieces++){
            //the number of pieces is greater than pieces (possibly pieces being taken)
            if(numPieces >= pieces){
                //declare and initalize score - which will call minimax method and return either 1 or -1 (depending on who's turn it is) 
                int  score = minimax(numPieces-pieces,!turn);
                //if the score = 1 (therefore > 0) - then this is the best move 
                    //if so, break the loop and return move = num of pieces that this iteration of the loop indicates
                if(score > 0 && turn == true){
                    move = pieces;
                    break;
                } else if (score < 0 && turn == false){
                    move = pieces;
                    break;
                }
            }
        }
        return move;
    }

    //method to determine which move will result in the best outcome 
    public static int minimax(int numPieces, boolean myTurn){
        //base case - if numPieces = 0
        if(numPieces == 0){
            //if no pieces left and it is not playerX turn, then playerX lost (means playerX took the last piece) - return -1
            if (myTurn == false){
                return -1;
            //if playerX turn, then playerX won bc other player took the last piece off the pile
            } else {
                return 1;
            }
        } else {
            //create list of scores - list will have 3 nums (either 1 or -1) as player can take 1, 2, or 3 pieces
            ArrayList<Integer> scores = new ArrayList<>();
            //loop to run 3 times, each time determining score for taking 1, 2, or 3 pieces off the pile 
            for(int pieces=1; pieces<=3; pieces++){
                //as long as pieces is less than or equal to number of pieces on pile - can't take more pieces than pile has
                if(pieces<=numPieces){
                    //score = recalls minimax - in order to check if one move is good and find a score, need to determine if the sucessive moves after it will work
                        //recursive step to continue until hits base case
                    int score = minimax(numPieces-pieces, !myTurn);
                    //add score to list of scores - should end with 3 different scores
                    scores.add(score);
                } 
            }
            if(myTurn){
                //if myTurn - return max of the scores to determine which move is best
                return Collections.max(scores);
            } else {
                //if it's not myTurn - return min (then don't want best move)
                return Collections.min(scores);
            }
        }
    }

    public static int getXMove(int numPieces){
        //return best move x could have - true because playerX turn 
            //best move calls minimax
        return 1;
        //bestMove(numPieces, true);
    }

    public static int getYMove(int numPieces){
        //return bestMOve for player y - would this be return false to best move?
        return bestMove(numPieces, false);

    }
}