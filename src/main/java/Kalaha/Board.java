package Kalaha;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Board {
    private List<Integer> holes;
    private boolean anotherRound;


    //ToString
    @Override
    public String toString() {
        return "[" + holes + anotherRound+ "]";
    }
    
    //konstruktør av et brett med 6-4 steiner i hvert hull
    public Board(int stones) {
        if (stones == 6) {
            this.holes = Arrays.asList(6,6,6,6,6,6, 0 , 6,6,6,6,6,6, 0 );
        } else if (stones == 5) {
            this.holes = Arrays.asList(5,5,5,5,5,5, 0 , 5,5,5,5,5,5, 0 );
        } else if (stones == 4) {
            this.holes = Arrays.asList(4,4,4,4,4,4, 0 , 4,4,4,4,4,4, 0 );
        } else {
            throw new IllegalArgumentException("There can only be between 4-6 stones in each hole");
        }
        this.anotherRound = false;
    }

    // public void lastInHome(int index) {
    //     if (index == 6 || index == 13) {
    //         // if last stone placed in home, player gets another round
    //         playStones(index);
    //         int i = index+1;
    //     }
    // }     


                    // //check if player
                    // if (i == 6 || i == 13) {
                    //     playStones(i);


    //settere og gettere for steiner i hull
    public void setStones(int hole, int value) {
        this.holes.set(hole, value);
    }

    public int getStones(int index) {
        return this.holes.get(index);
    }

    // public void playStones(int index) {
    //     int i = index;
    //     int hole = index+i;
    //     while (i < (getStones(index))) {
    //         i++;
    //         System.out.println("dette er hull " + i);

    //         //for each iteration update hole with 1 more stone
    //         setStones(i+index, getStones(i)+1);
    //         System.out.println(this.holes);
            
    //     } 
    //     System.out.println(i);
          
    // }

    public void placeStones(int index) {
        int endIndex = (getStones(index)+index);
        setStones(index, 0);
        for (int i = (index); i < endIndex;) {
            i++;
            if (i == 14) { endIndex = endIndex-i; i = 0; }
            System.out.println("dette er hull " + i);

            //for each iteration update hole with 1 more stone
            setStones(i, getStones(i)+1);
            System.out.println(this.holes);

            if (i == endIndex) {
                checkIfEmpty(i);
                checkIfHome(i);
            }


        }
    }

    public void checkIfHome(int index) {
            if (index == 6 || index == 13) {
                // if last stone placed in home, player gets another round
                this.setAnotherRound(true);
            } 
    }
    

    public void checkIfEmpty(int index) {
        if (getStones(index) == 0) {
            //steal stones from enemy hole across the board
            int stolen = getStones((12-index));
            setStones((12-index), 0);
            if (index == 0 && index == 1 && index == 2 && index == 3 && index == 4 && index == 5) {
                setStones(6, getStones(6)+stolen);
            } else {
                setStones(13, getStones(13)+stolen);
            }

        }
    }

    public boolean isAnotherRound() {
        return anotherRound;
    }

    public void setAnotherRound(boolean anotherRound) {
        this.anotherRound = anotherRound;
    }

    public static void main(String[] args) {
        Board board1 = new Board(6);
        System.out.println(board1);
        board1.placeStones(9);
        System.out.println(board1);
    }
}
