import java.util.*;
public class NimRunner{
    public static void main(String[] args){
        ArrayList<Integer> piles = new ArrayList<>();
        piles.add(1);
        //piles.add(3);
        //piles.add(5);
        System.out.println(runGame(piles) == false);
    }
    
    //function to run overall Nim game
    public static boolean runGame(ArrayList<Integer> numPieces){
        //while there are still pieces left in at least one pile
        while(!(isAllZeros(numPieces))){
            //empty arraylist to store xMove
            ArrayList<Integer> xMove = getXMove(numPieces);
            //initalizing default index to 0
            int index = 0;
            //for loop and if statement to determine at what index the x move is at 
                //will be in form up [0,1,0] --> index will = 1
            for(int i=0; i<xMove.size(); i++){
                if(xMove.get(i)>0){
                    index = i;
                }
            }
            //
            numPieces.set(index, numPieces.get(index) - xMove.get(index));
            if(isAllZeros(numPieces)){
                return false;
            } else {
              ArrayList<Integer> yMove = getYMove(numPieces);
              for(int y=0; y<yMove.size(); y++){
                if(yMove.get(y) > 0){
                    index = y;
                }
            }
            numPieces.set(index, numPieces.get(index) - yMove.get(index));
            if(isAllZeros(numPieces)){
                return true;
              }  
            }
        }
        //default return value
        return true;
    }
    
    //function to determine what move would be best given who's turn it is - return num pieces to be taken off pile by said player
    public static ArrayList<Integer> bestMove(ArrayList<Integer> numPieces, boolean turn){
        ArrayList<Integer> move = new ArrayList<Integer>();
        for(int i=0; i<numPieces.size(); i++){
            move.add(0);
        }
        for(int piece = 0; piece<numPieces.size(); piece++){
            if(numPieces.get(piece) > 0){
                for(int remove = 1; remove <= numPieces.get(piece); remove++){
                    for(int i=0; i<numPieces.size(); i++){
                    move.set(i,0);
                    }
                    ArrayList<Integer> newPiles = new ArrayList<>();
                    for(int i=0; i<numPieces.size(); i++){
                    newPiles.add(numPieces.get(i));
                    }
                    newPiles.set(piece, newPiles.get(piece)-remove);
                    int score = minimax(newPiles, !turn);
                    if(score > 0 && turn == true){
                        move.set(piece, remove);
                        return move;
                    } else if (score < 0 && turn == false){
                        move.set(piece, remove);
                        return move;
                    }
                }
            }
        }
        //default move
        for(int i=0; i<numPieces.size(); i++){
            if(numPieces.get(i) != 0){
                move.set(i,1);
                break;
            }
        }
        return move;
    }

    //method to determine which move will result in the best outcome 
    public static int minimax(ArrayList<Integer> piles, boolean myTurn){
        //base case - if each pile = 0
        if(isAllZeros(piles)){
            if(myTurn){
                return 1;
            } else {
                return -1;
            }
        }
        else {
            //create list of scores - list will have (either 1 or -1)
            ArrayList<Integer> scores = new ArrayList<>();
            //loop to amount of piles, each time determining score for taking 1, 2, or 3 pieces off the pile 
            for(int pieces=0; pieces<piles.size(); pieces++){
                for(int amount=1; amount<=piles.get(pieces); amount++){
                    ArrayList<Integer> newPiles = new ArrayList<>();
                    for(int i=0; i<piles.size(); i++){
                        newPiles.add(piles.get(i));
                    }
                    newPiles.set(pieces, newPiles.get(pieces)-amount);
                    int score = minimax(newPiles, !myTurn);
                    scores.add(score);
                } 
            }
                //as long as pieces is less than or equal to number of pieces on pile - can't take more pieces than pile has
                //no longer need to check if valid if change to general possibleMoves
                    //score = recalls minimax - in order to check if one move is good and find a score, need to determine if the sucessive moves after it will work
                        //recursive step to continue until hits base case
                    //add score to list of scores - should end with 3 different scores 
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
        return bestMove(piles, true);
    }

    public static ArrayList<Integer> getYMove(ArrayList<Integer> piles){
        //return bestMOve for player y - would this be return false to best move?
        return bestMove(piles, false);

    }

    public static ArrayList<Integer> getUserMove(ArrayList<Integer> numPieces){
        Scanner sc = new Scanner(System.in);
        System.out.println("There are " + numPieces.size() + " piles left. The piles have the following amount of pieces each: ");
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

    public static boolean isAllZeros(ArrayList<Integer> piles){
        for(int i=0; i<piles.size(); i++){
            if(piles.get(i) != 0){
                return false;
            }
        }
        return true;
    }
}