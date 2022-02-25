package Kalaha;

import java.util.Arrays;
import java.util.List;

public class Board {
    private List<Integer> holes;


    //ToString
    @Override
    public String toString() {
        return "Board holes=" + holes;
    }
    
    //konstruktør av tomt brett
    public Board(List<Integer> holes) {
        this.holes = holes;
    }

    public void lastInHome(int index) {
        if (index == 6 || index == 13) {
            // if last stone placed in home, player gets another round
            playStones(index);
            int i = index+1;
        }
    }     


                    // //check if player
                    // if (i == 6 || i == 13) {
                    //     playStones(i);

    public void setStones(int hole, int value) {
        this.holes.set(hole, value);
    }

    public int getStones(int index) {
        return this.holes.get(index);
    }

    // public void playStones(int index) {
    //     int i = index+1;
    //     while (i <= getStones(index)) {
    //         System.out.println("dette er hull " + i);

    //         //for each iteration update hole with 1 more stone
    //         setStones(i, getStones(i)+1);
    //         System.out.println(this.holes);
    //         i++;
    //     } 
    //     System.out.println(i);
          
    // }

    public int playStones(int index) {
        int initialIndex = getStones(index);
        for (int i = (index+1); i <= initialIndex; i++) {
            System.out.println("dette er hull " + i);

            //for each iteration update hole with 1 more stone
            setStones(i, getStones(i)+1);
            System.out.println(this.holes);
            
            
            if (i == initialIndex) {
                return i;  
            }
             
            
            }
            return 0;
        }
   

    public boolean checkIfHome(int i) {
            if (i == 6 || i == 13) {
                // if last stone placed in home, player gets another round
                return true;
            }
            return false;
    }

    public void checkIfEmpty() {
        
    }


    public static void main(String[] args) {
        Board board1 = new Board(Arrays.asList(6,6,6,6,6,6, 0 , 6,6,6,6,6,6, 0 ));
        System.out.println(board1);
        board1.playStones(0);
        System.out.println(board1);
    }
}
