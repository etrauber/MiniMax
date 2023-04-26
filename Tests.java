import java.util.*;
public class Tests{
    public static void main(String[] args){
        System.out.println("Testing my NimRunner class.");
        //System.out.println(NimRunner.minimax(4, true) == 1);
        // System.out.println(NimRunner.bestMove(4, false));
        //System.out.println(NimRunner.bestMove(5, true));

        //create new arrayList with 1 and 3 pieces added in piles
        ArrayList<Integer> pieces = new ArrayList<Integer>();
        pieces.add(1);
        pieces.add(3);
        //System.out.println(NimRunner.getXMove(pieces));
        //adding in piled to have [1,3,5,7]
        pieces.add(5);
        pieces.add(7);
        //should return -1
        System.out.println(NimRunner.minimax(pieces, true) == -1);
        System.out.println(NimRunner.bestMove(pieces,true));
        //pieces.add(3);
        //pieces.add(5);
        System.out.println(NimRunner.runGame(pieces) == true);
    }
}