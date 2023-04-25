import java.util.*;
public class NimRunner{
    //delcaring instance variables of player(true if player = x, false if not) and number of pieces on the pile (int)
    boolean player;
    public static boolean runGame(){
        ArrayList<Integer> numPieces = new ArrayList<>();
        numPieces.add(1);
        while(numPieces.size() > 0){
            if(numPieces.size() == 1 && numPieces.get(0) == 0){
                break;
            }
            ArrayList<Integer> xMove = getXMove(numPieces);
            int index = 0;
            for(int i=0; i<xMove.size(); i++){
                if(xMove.get(i)>0){
                    index = xMove.get(i);
                }
            }
            numPieces.set(index, numPieces.get(index) - xMove.get(index));
            if(numPieces.get(index) == 0){
                numPieces.remove(index);
            }
            System.out.println("Player x move: " + xMove);
            if(numPieces.size() == 0){
                return false;
            } else {
              ArrayList<Integer> yMove = getYMove(numPieces);
              for(int y=0; y<yMove.size(); y++){
                if(yMove.get(y) > 0){
                    index = y;
                }
            }
            numPieces.set(index, numPieces.get(index) - yMove.get(index));
            if(numPieces.get(index) == 0){
                numPieces.remove(index);
            }
            System.out.println("Player y move: " + yMove);
            if(numPieces.size() == 0){
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
        for(int i=0; i<numPieces.size(); i++){
            move.add(0);
        }
        System.out.println("after first loop in best move"); 
        if(numPieces.get(0) > 1){
            move.set(0,1);
        }
        //declare and initalize move with default value
        ArrayList<ArrayList<Integer>> possibleMoves = getPossibleMoves(numPieces);
        //for loop to run 3 times, by checking what happens if 1, 2, or 3 pieces are taken off 
        for(int pieces=0; pieces< possibleMoves.size(); pieces++){
       //the number of pieces is greater than pieces (possibly pieces being taken)
        //declare and initalize score - which will call minimax method and return either 1 or -1 (depending on who's turn it is)
        System.out.println("before calling minimax"); 
            int  score = minimax(numPieces, possibleMoves.get(pieces),turn);
            //if the score = 1 (therefore > 0) - then this is the best move 
                //if so, break the loop and return move = num of pieces that this iteration of the loop indicates
        System.out.println("after calling minimax");
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
        System.out.println("minimax");
        //base case - if each pile = 0
        if(piles.get(0) == 0 && piles.size() == 1){
            if(myTurn){
                return 1;
            } else {
                return -1;
            }
        } else {
            System.out.println("after first if statement in minimax");
            //create list of scores - list will have (either 1 or -1)
            ArrayList<Integer> scores = new ArrayList<>();
            //loop to amount of piles, each time determining score for taking 1, 2, or 3 pieces off the pile 
            for(int pieces=0; pieces<state.size(); pieces++){
                System.out.println("inside first for loop in minimax");
                for(int j=0; j<piles.get(pieces); j++){
                    System.out.println("second for loop in minimax");
                    if(state.get(pieces) > 0){
                        piles.set(pieces, piles.get(pieces)-state.get(pieces));
                    }
                    ArrayList<ArrayList<Integer>> possibleMoves = getPossibleMoves(piles);
                    for(int i=0; i<possibleMoves.size(); i++){
                        int score = minimax(piles, possibleMoves.get(i), !myTurn);
                        scores.add(score);
                    }
                    if (piles.get(pieces) == 0){
                        piles.remove(pieces);
                    }
                }
                //as long as pieces is less than or equal to number of pieces on pile - can't take more pieces than pile has
                //no longer need to check if valid if change to general possibleMoves
                    //score = recalls minimax - in order to check if one move is good and find a score, need to determine if the sucessive moves after it will work
                        //recursive step to continue until hits base case
                    //add score to list of scores - should end with 3 different scores
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

    public static ArrayList<Integer> getXMove(ArrayList<Integer> piles){
        //return best move x could have - true because playerX turn 
            //best move calls minimax
        //always return a valid move
        System.out.println("before bestMove");
        return bestMove(piles, true);
    }

    public static ArrayList<Integer> getYMove(ArrayList<Integer> piles){
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
        if(move <= numPieces.get(index)){
            movePlace.add(move);
            movePlace.add(index);
            return movePlace;
        } else {
            System.out.println("Not a valid move! You lose!");
            return movePlace;
        }
    }

    public static ArrayList<ArrayList<Integer>> getPossibleMoves(ArrayList<Integer> piles){
        ArrayList<ArrayList<Integer>> moves = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<piles.size(); i++){
            for(int piece = 0; piece<piles.get(i); piece++){
                ArrayList<Integer> oneMove = new ArrayList<>();
                for(int j = 0; j<piles.size(); j++){
                    oneMove.add(0);
                }
                oneMove.set(i, piece);
                moves.add(oneMove); 
            }
        }
        return moves;
    }
}