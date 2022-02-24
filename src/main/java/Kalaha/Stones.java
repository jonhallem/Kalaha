package Kalaha;

public class Stones {
    
    


    public void playStones(int index) {
        board.getStones(7);
    }






    public static void main(String[] args) {
        Board board = new Board();
        System.out.println(board.getStones(5));
    }
}
