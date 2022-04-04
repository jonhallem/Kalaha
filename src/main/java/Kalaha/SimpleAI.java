package Kalaha;

import java.util.Random;

public class SimpleAI {
    private int bestplay;

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

        //if not; calculates a random move
        this.bestplay = calculateBestPlay();
        while (game.getBoard().getStones(this.bestplay) == 0) {
            this.bestplay = calculateBestPlay();
        }
        System.out.println("robot is playing random " + bestplay);
        return bestplay;
    }

    private int calculateBestPlay() {
        Random random = new Random();
        int index = random.nextInt(6)+7;
        return index;
    }


}
