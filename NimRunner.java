import java.util.*;
public class NimRunner{
    //delcaring instance variables of player(true if player = x, false if not) and number of pieces on the pile (int)
    boolean player;
    public static boolean runGame(){
        ArrayList<Integer> numPieces = new ArrayList<>();
        numPieces.add(1);
        numPieces.add(3);
        numPieces.add(5);
        while(numPieces.size() > 0){
            int xMove = getXMove(numPieces);
            numPieces = numPieces - xMove;
            System.out.println("Player x move: " + xMove);
            if(numPieces == 0){
                return false;
            } else {
              int yMove = getYMove(numPieces);
                numPieces = numPieces - yMove;
                System.out.println("Player y move: " + yMove);
              if(numPieces == 0){
                return true;
              }  
            }
        }
        //default return value
        return true;
    }
    
    //function to determine what move would be best given who's turn it is - return num pieces to be taken off pile by said player
    public static ArrayList<Integer> bestMove(ArrayList<Integer> numPieces, boolean turn){
        ArrayList<Integer> move = new ArrayList<>();
        //declare and initalize move with default value
        ArrayList<ArrayList<Integer> possibleMoves = getPossibleMoves(numPieces);
        //for loop to run 3 times, by checking what happens if 1, 2, or 3 pieces are taken off 
        for(int pieces=1; pieces<= possibleMoves.size(); pieces++){
       //the number of pieces is greater than pieces (possibly pieces being taken)
        //declare and initalize score - which will call minimax method and return either 1 or -1 (depending on who's turn it is) 
            int  score = minimax(numPieces, possibleMoves.get(pieces),!turn);
            //if the score = 1 (therefore > 0) - then this is the best move 
                //if so, break the loop and return move = num of pieces that this iteration of the loop indicates
            if(score > 0 && turn == true){
                move = possibleMoves.get(pieces);
                break;
            } else if (score < 0 && turn == false){
                move = possibleMoves.get(pieces);
                break;
            }       
        }
        return move;
    }

    //method to determine which move will result in the best outcome 
    public static int minimax(ArrayList<Integer> piles, ArrayList<Integer> state, boolean myTurn){
        //base case - if each pile = 0
        for(int pile = 0; i<piles.size(); i++){
            if(state.get(pile) == 0){
                if(myTurn){
                    return 1;
                } else {
                    return -1;
                }
            }
        }
            //create list of scores - list will have (either 1 or -1)
            ArrayList<Integer> scores = new ArrayList<>();
            //loop to amount of piles, each time determining score for taking 1, 2, or 3 pieces off the pile 
            for(int pieces=1; pieces<=state.size(); pieces++){
                for(int j=0; j<piles.get(pieces); j++){
                    if(state.get(pieces) > 0){
                        piles.set(pieces, piles.get(pieces)-state.get(pieces));
                        if (piles.get(pieces) == 0){
                            piles.remove(pieces);
                        }
                    }
                    ArrayList<ArrayList<Integer>> possibleMoves = getPossibleMoves(piles);
                    for(int i=0; i<possibleMoves.size(); i++){
                        int score = minimax(piles, possibleMoves.get(i), !myTurn)
                    }
                }
                //as long as pieces is less than or equal to number of pieces on pile - can't take more pieces than pile has
                //no longer need to check if valid if change to general possibleMoves
                    //score = recalls minimax - in order to check if one move is good and find a score, need to determine if the sucessive moves after it will work
                        //recursive step to continue until hits base case
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

    public static int getXMove(ArrayList<Integer> piles){
        //return best move x could have - true because playerX turn 
            //best move calls minimax
        //always return a valid move
        return bestMove(piles, true);
    }

    public static int getYMove(ArrayList<Integer> piles){
        //return bestMOve for player y - would this be return false to best move?
        return bestMove(piles, false);

    }

    public static ArrayList<Integer> getUserMove(ArrayList<Integer> numPieces){
        Scanner sc = new Scanner(System.in);
        System.out.println("There are " + numPieces.size() + " piles left. The pile have the following amount of pieces each: ");
        System.out.println(numPieces);
        System.out.println("How many pieces would you like to take and from which pile (please specify the index - first pile index is 0: ");
        int move = sc.nextInt();
        int index = sc.nextInt();
        ArrayList<Integer> movePlace = new ArrayList<>();
        movePlace.add(move);
        movePlace.add(index);
        for(int i=0; i<numPieces.size(); i++){
            if(move <= numPieces.get(i)){
                return movePlace;
            } else {
                System.out.println("Not a valid move! You lose!");
                ArrayList<Integer> empty = new ArrayList<>();
                return empty;
        }
        }
    }

    public static ArrayList<ArrayList<Integer>> getPossibleMoves(ArrayList<Integer> piles){
        ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<piles.size(); i++){
            for(int piece = 0; piece <piles.get(i); piece++){
                ArrayList<Integer> oneMove = new ArrayList<>();
                for(int j = 0; j<4; j++){
                    oneMove.add(0);
                }
                oneMove.set(i, piece);
                moves.add(oneMove); 
            }
        }
    }
}