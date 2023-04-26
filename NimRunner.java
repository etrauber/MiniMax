import java.util.*;
public class NimRunner{
    public static void main(String[] args){
        ArrayList<Integer> piles = new ArrayList<>();
        piles.add(1);
        piles.add(3);
        piles.add(5);
        if(runGame(piles)){
            System.out.println("PlayerX wins");
        } else {
            System.out.println("PlayerY wins");
        }
    }
    
    //function to run overall Nim game
    public static boolean runGame(ArrayList<Integer> numPieces){
        //while there are still pieces left in at least one pile
        while(!(isAllZeros(numPieces))){
            //empty arraylist to store xMove
            ArrayList<Integer> xMove = getXMove(numPieces);
            System.out.println("Player X's move is: " + xMove);
            //initalizing default index to 0
            int index = 0;
            //for loop and if statement to determine at what index the x move is at 
                //will be in form up [0,1,0] --> index will = 1
            for(int i=0; i<xMove.size(); i++){
                if(xMove.get(i)>0){
                    index = i;
                }
            }
            //altering numPieces to subtract xMove from given pile (xMove index are parallel to numPieces index)
            numPieces.set(index, numPieces.get(index) - xMove.get(index));
            System.out.println("The new piles are: " + numPieces);
            //if no pile has any pieces left after x turn - x loses --> return false
            if(isAllZeros(numPieces)){
                return false;
            //if still pieces left after x turn - move onto y turn
            } else {
                //initalize empty arraylist for y move
                ArrayList<Integer> yMove = getUserMove(numPieces);
                System.out.println("Player Y's move is: " + yMove);
                //for loop to loop through yMove and determine at what index the pieces are being subtracted
                for(int y=0; y<yMove.size(); y++){
                if(yMove.get(y) > 0){
                    //using same index counter as above - reassigning it
                    index = y;
                }
            }
            //altering numPieces to subtract y move from give pile (numPieces will continue to be altered til no pieces left by x and y moves)
            numPieces.set(index, numPieces.get(index) - yMove.get(index));
            System.out.println("The new piles are: " + numPieces);
            //checking to see is all the piles are empty - if so y loses --> return true
            if(isAllZeros(numPieces)){
                return true;
              }  
            }
        }
        //default return value
        return true;
    }
    
    //function to determine what move would be best given who's turn it is - return num pieces to be taken off specific pile by said player
    public static ArrayList<Integer> bestMove(ArrayList<Integer> numPieces, boolean turn){
        //intialize empty arraylist filled with zeros to hold the give move
        ArrayList<Integer> move = new ArrayList<Integer>();
        for(int i=0; i<numPieces.size(); i++){
            move.add(0);
        }
        //for loop to loop through each individual pile
        for(int piece = 0; piece<numPieces.size(); piece++){
            //making sure there are more than zero pieces in a pile - if not, cannot remove from it so moves to next pile
            if(numPieces.get(piece) > 0){
                //loop to loop through amount of pieces in each pile to determine the ideal amount to remove
                for(int remove = 1; remove <= numPieces.get(piece); remove++){
                    //for loop to reset move to hold only zeros (initalized outside so still has the same amount of indeces as numPieces)
                    for(int i=0; i<numPieces.size(); i++){
                        move.set(i,0);
                    }
                    //counter arraylist to hold altered piles
                    ArrayList<Integer> newPiles = new ArrayList<>();
                    //loop to add elements of numPieces to newPiles - can't set = because reference varaibles
                    for(int i=0; i<numPieces.size(); i++){
                        newPiles.add(numPieces.get(i));
                    }
                    //change newPiles to be equal to original piles, while subtracting certain amount from certain index 
                        //looking at possible moves
                    newPiles.set(piece, newPiles.get(piece)-remove);
                    //call minimax to determine score of possible move (either 1 or -1)
                    int score = minimax(newPiles, !turn);
                    //if the score = 1 and playerX turn - should make the move - set the move = pieces removed at certain index
                        //return new move
                    if(score > 0 && turn == true){
                        move.set(piece, remove);
                        return move;
                    //if the score = -1 and playerY turn - should make the move - set the move and return
                    } else if (score < 0 && turn == false){
                        move.set(piece, remove);
                        return move;
                    }
                }
            }
        }
        //default move - making sure the default value will not alter a pile that has 0 pieces on it
        for(int i=0; i<numPieces.size(); i++){
            if(numPieces.get(i) != 0){
                move.set(i,1);
                break;
            }
        }
        return move;
    }

    //method to determine which move will result in the best outcome  - output = a score (either 1 or -1)
    public static int minimax(ArrayList<Integer> piles, boolean myTurn){
        //base case - if each pile has 0 elements 
            //call helper method to check
        if(isAllZeros(piles)){
            //if zero elements and playerX turn - return 1, bc then playerY took last piece
            if(myTurn){
                return 1;
            //if zero elements and playY turn - return -1, bc then playerX took last piece
            } else {
                return -1;
            }
        }
        else {
            //create list of scores - list will have (either 1 or -1)
            ArrayList<Integer> scores = new ArrayList<>();
            //loop through amount of piles 
            for(int pieces=0; pieces<piles.size(); pieces++){
                //nested loop to loop through individual pieces in each pile
                for(int amount=1; amount<=piles.get(pieces); amount++){
                    //create new empty arrayList to hold altered piles - not to change main piles until optimal move is determined
                    ArrayList<Integer> newPiles = new ArrayList<>();
                    //loop to set values of piles = value of newPiles 
                    for(int i=0; i<piles.size(); i++){
                        newPiles.add(piles.get(i));
                    }
                    //altering newPiles to = newPiles - amount wanted to remove (will always be valid bc of for loop) - at specifed index of outer loop
                    newPiles.set(pieces, newPiles.get(pieces)-amount);
                    //recursive step - calling minimax to determine the score - given new altered pile
                    int score = minimax(newPiles, !myTurn);
                    //adding score to scores arraylist to be cycled through later to determine optimal move
                    scores.add(score);
                } 
            }
            //playerX turn - return max of the scores
            if(myTurn){
                return Collections.max(scores);
            //if playerY turn - return min of the scores
            } else {
                return Collections.min(scores);
            }
        }
    }

    //function to get playerX move
    public static ArrayList<Integer> getXMove(ArrayList<Integer> piles){
        //return best move playerX could have 
            // true because playerX turn
        return bestMove(piles, true);
    }

    public static ArrayList<Integer> getYMove(ArrayList<Integer> piles){
        //return bestMOve for playerY 
            //input false bc playerY turn
        return bestMove(piles, false);

    }

    //function to allow a user to play against the computer
    public static ArrayList<Integer> getUserMove(ArrayList<Integer> numPieces){
        //scanner to be able to use user input
        Scanner sc = new Scanner(System.in);
        //telling user how many piles are left and how many pieces are in each pile
        System.out.println("There are " + numPieces.size() + " piles left. The piles have the following amount of pieces each: ");
        System.out.println(numPieces);
        //user input 2 numbers, seperated by a space (first move then index)
        System.out.println("How many pieces would you like to take and from which pile (please specify the index - first pile index is 0: ");
        //creating variable to hold amount to remove and index
        int amountToRemove = sc.nextInt();
        int index = sc.nextInt();
        //setting an empty arraylist = to amount of zeros as index of numPieces - correct format for runGame to look at 
        ArrayList<Integer> move = new ArrayList<>();
        for(int i=0; i<numPieces.size(); i++){
            move.add(0);
        }
        //making sure amount wanted to remove is less than amount in pile - if so, set move = amount to remove at that specific index
        if(amountToRemove <= numPieces.get(index)){
            move.set(index, amountToRemove);
        //if not - return all zeros- not a valid move
        } 
        else {
            System.out.println("Not a valid move! You lose!");
        }
        return move;
    }

    //function to check if all the piles are = zero
    public static boolean isAllZeros(ArrayList<Integer> piles){
        //if all of the piles are not equal to zero - break loop and return false
        for(int i=0; i<piles.size(); i++){
            if(piles.get(i) != 0){
                return false;
            }
        }
        //if all piles = 0, will return true
        return true;
    }
}