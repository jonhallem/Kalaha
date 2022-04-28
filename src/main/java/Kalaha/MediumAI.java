package Kalaha;

public class MediumAI extends EasyAI {

    // ------------------- MAIN METHOD ----------------------
    //Almost the same method as the superclass, but with added logic for emptyPlay
    @Override
    public int decideBestPlay(Game game) {

        System.out.println("Robot is calculating move!");
        
        //calculates a random move
        calculateRandomPlay(game);

        //check if one hole is zero, if it is, check if there are any holes that can land in it
        calculateEmptyPlay(game);

        //then checks for a better move; where a stone lands in home
        calculateHomePlay(game);

        return getBestPlay();
    }

    // ----------------- SUPPORTING METHODS --------------------

    //could be private but for testing set to public
    public void calculateEmptyPlay(Game game) {
        //checks if a hole is empty
        for (int j = 8; j < 13; j++) {
            if (game.getBoard().getStones(j) == 0) {
                System.out.println("Robot found " + j + " to be empty");
                //and if a hole is empty, if there is a valid play that lands in it
                for (int i = 1; i < 7; i++) {
                    if ((game.getBoard().getStones(j-i) == i) && (j-i > 6)) { 
                        System.out.println("and robot found " + i +  " steps before, and therefore plays " + (j-i));
                        setBestPlay(j-i);
                    }
                }
            }
        }

        
    }

    
}
