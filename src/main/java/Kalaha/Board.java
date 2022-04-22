package Kalaha;

import java.util.Arrays;
import java.util.List;

public class Board {
    private List<Integer> holes;
    private boolean anotherRound;
    private boolean playerPlaying;


    //ToString
    @Override
    public String toString() {
        return ""+ holes;
    }
    
    // Constructor of a board with 4-6 stones in each hole
    public Board(int s, boolean startingPlayer) {
        //The most normal starting game options 4, 5 and 6 are restricted by the controller.
        //But in theory any number of stones can be used

        this.holes = Arrays.asList(s,s,s,s,s,s, 0 , s,s,s,s,s,s, 0 );

        this.anotherRound = false;

        //Option to change who is the starting player
        this.playerPlaying = startingPlayer;
    }

    public void placeStones(int index) {
        int endIndex = (getStones(index)+index);
        setStones(index, 0);
        for (int i = (index); i < endIndex;) {
            i++;
            //restart index when iterating past index
            if (i >= 14) { endIndex = endIndex-i; i = 0; }


            //check if last stone is placed in empty or home
            if (i == endIndex) {
                checkIfEmpty(i);
                checkIfHome(i);
            }

            System.out.println("dette er index " + i + " av " + endIndex);
            System.out.println(this.holes);

            //for each iteration update hole with 1 more stone
            //but only if it's not the enemy hole
            if (i == 6 && getPlayerPlaying() == false) {
                endIndex++;
                }
            else if (i == 13 && getPlayerPlaying() == true) {
                endIndex++;
                }
            else {
                setStones(i, getStones(i)+1);
            }
            System.out.println(this.holes);
        }
    }


    //!!!!kan være privat, men må være public for at testen skal fungere???
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
    

    //private
    public void checkIfEmpty(int index) {
        //the following if-check sees if the hole is empty (the latter check is only for testing purposes)
        if (getStones(index) == 0 && index != 6 && index != 13) {

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

    public void setAnotherRound(boolean anotherRound) {
        this.anotherRound = anotherRound;
    }

   public boolean getAnotherRound() {
        return anotherRound;
    }

    public boolean getPlayerPlaying() {
        return playerPlaying;
    }

    public void setPlayerPlaying(boolean playerPlaying) {
        this.playerPlaying = playerPlaying;
    }

    //methods for loading

    //are these in use?

    public void setHoles(Integer[] holes) {
        this.holes = Arrays.asList(holes);
    }

    //temp for scoreboardloading

    public List<Integer> getHoles() {
        return this.holes;
    }



    public static void main(String[] args) {
        Board board = new Board(6, true);
        Integer[] testHoles =  {1, 0, 4, 3, 1, 7, 7, 0, 7, 2, 18, 1, 9, 7};
        board.setHoles(testHoles);
        board.placeStones(10);
    }
}
