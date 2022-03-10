package Kalaha;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Board {
    private List<Integer> holes;
    private boolean anotherRound;
    private boolean playerPlaying;


    //ToString
    @Override
    public String toString() {
        return "[" + holes + anotherRound+ "]";
    }
    
    // Constructor of a board with 4-6 stones in each hole
    public Board(int stones, boolean startingPlayer) {
        //The most normal starting game options. 
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

        //Option to change who is the starting player
        this.playerPlaying = startingPlayer;
    }

    public void placeStones(int index) {
        int endIndex = (getStones(index)+index);
        setStones(index, 0);
        for (int i = (index); i < endIndex;) {
            i++;
            if (i == 14) { endIndex = endIndex-i; i = 0; }
            if (i == endIndex) {
                checkIfEmpty(i);
                checkIfHome(i);
            }

            System.out.println("dette er hull " + i);

            //for each iteration update hole with 1 more stone
            setStones(i, getStones(i)+1);
            System.out.println(this.holes);




        }
    }

    public void checkIfHome(int index) {
            if (index == 6 && getPlayerPlaying() == true) {
                // if last stone placed in player 1 home, player 1 gets another round
                this.setAnotherRound(true);
            } else if (index == 13 && getPlayerPlaying() == false) {
                // if last stone placed in player 2 home, player 2 gets another round
                this.setAnotherRound(true);
            }
            else {
                this.setAnotherRound(false);
            }
    }
    

    public void checkIfEmpty(int index) {
        if (getStones(index) == 0) {

            if (index == 0 || index == 1 || index == 2 || index == 3 || index == 4 || index == 5) {
                if (getPlayerPlaying() != true) {
                    return;      
                }
            } else {
                if (getPlayerPlaying() == !false) {
                    return;
                }
            }

            //steal stones from enemy hole across the board
            int stolen = getStones((12-index))+1;
            setStones((12-index), 0);
            setStones(index, -1);
            //check if valid hole to run stealmechanic AND if it is a valid player
            if (index == 0 || index == 1 || index == 2 || index == 3 || index == 4 || index == 5) {
                setStones(6, getStones(6)+stolen);        

            } else {
                setStones(13, getStones(13)+stolen);
            }

        }
    }


    //settere og gettere for steiner i hull
    public void setStones(int hole, int value) {
        this.holes.set(hole, value);
    }

    public int getStones(int index) {
        return this.holes.get(index);
    }

    public boolean isAnotherRound() {
        return anotherRound;
    }

    public void setAnotherRound(boolean anotherRound) {
        this.anotherRound = anotherRound;
    }


    public boolean getPlayerPlaying() {
        return playerPlaying;
    }

    public void setPlayerPlaying(boolean playerPlaying) {
        this.playerPlaying = playerPlaying;
    }

    public static void main(String[] args) {
        Board board1 = new Board(6);
        System.out.println(board1);
        board1.placeStones(9);
        System.out.println(board1);
    }
}
