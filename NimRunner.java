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

    public static int getXMove(int numPieces){
        //return best move x could have - true because playerX turn 
            //best move calls minimax
        return bestMove(numPieces, true);
    }

    public static int getYMove(int numPieces){
        //return bestMOve for player y - would this be return false to best move?
        return bestMove(numPieces, false);

    }
}