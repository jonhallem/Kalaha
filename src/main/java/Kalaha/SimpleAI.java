package Kalaha;

import java.util.Random;

public class SimpleAI {
    protected int bestplay;

    public int decidePlay(Game game) {

        System.out.println("Robot is calculating move!");

        //checks if a move lands in home
        for (int j = 7; j < 13; j++) {
            if (game.getBoard().getStones(j)+j == (13)) {
                int bestplay = j;
                System.out.println("robot is playing smart " + bestplay);
                return bestplay;
            }
        } 


        // check if one hole is zero, if it is, check if there are any holes that can land in it

        for (int j = 9; j < 13; j++) { //j er 12
            if (game.getBoard().getStones(j) == 0) {
                System.out.println("Robot found " + j + " to be zero");
                //ut i fra j sjekk om hull før er j+1
                for (int i = 1; i < 7; i++) { //i er 1
                    if ((game.getBoard().getStones(j-i) == i) && (j-i > 6)) { //j-i er da 11
                        System.out.println("and robot found " + i +  ", therefore plays " + (j-i));
                        int bestplay = j-i;
                        return bestplay;
                    }
                }
            }
        }

        //if not; calculates a random move
        this.bestplay = calculateBestPlay(game);
        System.out.println("robot is playing random " + bestplay);
        return bestplay;
    }

    private int calculateBestPlay(Game game) {
        Random random = new Random();
        int index = random.nextInt(6)+7;
        while (game.getBoard().getStones(index) == 0) {
            index = random.nextInt(6)+7;
        }
        return index;
    }


}
