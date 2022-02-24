package Kalaha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private List<Integer> holes = new ArrayList<Integer>(Arrays.asList(6,6,6,6,6,6, 0 , 6,6,6,6,6,6, 0 ));


    //ToString
    @Override
    public String toString() {
        return "Board holes=" + holes;
    }
    //konstruktør av tomt brett
    public Board() {
    }

    
    //(6,6,6,6,6,6, 0 , 6,6,6,6,6,6, 0);


    public static void main(String[] args) {
        Board board1 = new Board();
        System.out.println(board1);
    }
}
