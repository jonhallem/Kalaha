package Kalaha;

public class MediumAI extends EasyAI {

    @Override
    public int decideBestPlay(Game game) {

        System.out.println("Robot is calculating move!");
        
        //calculates a random move
        super.calculateRandomPlay(game);

        //check if one hole is zero, if it is, check if there are any holes that can land in it
        calculateEmptyPlay(game);

        //then checks for a better move; where a stone lands in home
        super.calculateHomePlay(game);

        return bestplay;
    }



    private void calculateEmptyPlay(Game game) {
        for (int j = 9; j < 13; j++) { //j er 12
            if (game.getBoard().getStones(j) == 0) {
                System.out.println("Robot found " + j + " to be empty");
                //ut i fra j sjekk om hull før er j+1
                for (int i = 1; i < 7; i++) { //i er 1
                    if ((game.getBoard().getStones(j-i) == i) && (j-i > 6)) { //j-i er da 11
                        System.out.println("and robot found " + i +  " steps before, and therefore plays " + (j-i));
                        this.bestplay = j-i;
                    }
                }
            }
        }

        
    }
    
}
