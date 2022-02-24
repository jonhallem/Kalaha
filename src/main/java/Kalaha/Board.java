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

    public void setStones(int hole, int value) {
        this.holes.set(hole, value);
    }

    public int getStones(int index) {
        return this.holes.get(index);
    }

    // public void playStones(int index) {
    //     int i = getStones(index);
    //     while (i > index) {
    //         System.out.println("dette er hull " + i);

    //         //for each iteration update hole with 1 more stone
    //         setStones(index, getStones(index)+1);
    //         System.out.println(this.holes);
    //         i--;
    //     } 
          
    // }






    public void playStones(int index) {
        for (int i = index; i < this.holes.get(i); i++) {
            System.out.println("dette er hull " + i);

            //for each iteration update hole with 1 more stone
            setStones(i, getStones(i)+1);
            System.out.println(this.holes);
        }
            
    }



    public static void main(String[] args) {
        Board board1 = new Board();
        System.out.println(board1);
        board1.playStones(0);
        System.out.println(board1);
    }
}
