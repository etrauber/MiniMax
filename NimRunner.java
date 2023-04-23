import java.util.*;
public class NimRunner{
    boolean player;
    int numPieces;

    public static boolean runGame(){
        return true;
    }
    
    public static int bestMove(int numPieces, boolean turn){
        int move = 1;
        for(int pieces=1; pieces<=3; pieces++){
            if(numPieces < pieces){
                int  score = minimax(numPieces-pieces,!turn);
                if(score > 0){
                    move = pieces;
                    break;
                }
            }
        }
        return move;
    }

    public static int minimax(int state, boolean myTurn){
        if(state == 0){
            if (myTurn == false){
                return -1;
            } else {
                return 1;
            }
        } else {
            ArrayList<Integer> scores = new ArrayList<>();
            for(int pieces=1; pieces<=3; pieces++){
                if(pieces<=state){
                    int score = minimax(state-pieces, !myTurn);
                    scores.add(score);
                } 
            }
            System.out.println(scores);
            if(myTurn){
                return Collections.max(scores);
            } else {
                return Collections.min(scores);
            }
        }
    }

    // public static int getXMove(int state){
    //     //determine how many pieces on the board
    //     //pass through this state and who's turn through minimax and determine if playerx or playery
    //     //determine how many pieces you are taking
    // }

    // public static int getYMove(state){

    // }
}