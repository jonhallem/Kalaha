package Kalaha;

import java.util.Random;

public class EasyAI {

    private int bestplay;

    // ------------------- MAIN METHOD ----------------------

    public int decideBestPlay(Game game) {

        System.out.println("Robot is calculating move!");
        
        //calculates a random move
        calculateRandomPlay(game);

        //then checks for a better move; where a stone lands in home
        calculateHomePlay(game);

        //returns a valid play
        return getBestPlay();
    }

    // ----------------- SUPPORTING METHODS --------------------

    protected void calculateRandomPlay(Game game) {
        Random random = new Random();
        int index = random.nextInt(6)+7;
        while (game.getBoard().getStones(index) == 0) {
            index = random.nextInt(6)+7;
        }
        System.out.println("robot is playing random " + index);
        setBestPlay(index);
    }


    protected void calculateHomePlay(Game game) {
    
        for (int j = 7; j < 13; j++) {
            if (game.getBoard().getStones(j)+j == (13)) {
                System.out.println("robot is playing smart " + bestplay);
                setBestPlay(j);;
            }
        } 
        
    }

    // --------------- GETTERS AND SETTERS - ALSO USED FOR TESTING ---------------------

    protected int getBestPlay() {
        return bestplay;
    }

    protected void setBestPlay(int bestPlay) {
        this.bestplay = bestPlay;
    }
    
    
    
}
