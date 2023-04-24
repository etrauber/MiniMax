public class Tests{
    public static void main(String[] args){
        System.out.println("Testing my NimRunner class.");
        System.out.println(NimRunner.minimax(4, true) == 1);
        System.out.println(NimRunner.bestMove(4, true));
    }
}