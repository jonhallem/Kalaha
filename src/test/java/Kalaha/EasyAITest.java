package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EasyAITest {

    private EasyAI easyAI;
    private Game game;

    @BeforeEach
    public void setUp() {
        System.out.println("Initialiserer...");
        easyAI = new EasyAI();
        game = new Game("Alice", "Bob", true, 6, "Human");
        easyAI.setBestPlay(99);
    }


    @DisplayName ("Main method for finding a valid best play. The decidePlay method calls various supportmethods which will be tested on their own as well")
    @Test
    public void testDecidePlay() {

        //the best play will land the last stone in the home
        Integer[] testHoles =  {1, 1, 1, 1, 1, 1, 0, 6, 6, 6, 6, 6, 6, 0};
        game.getBoard().setHoles(testHoles);
        easyAI.decideBestPlay(game);
        assertEquals(7, easyAI.getBestPlay());

        //tests with a valid play that will land in an empty hole
        Integer[] testHoles2 =  {1, 1, 1, 1, 1, 1, 0, 0, 5, 2, 6, 0, 6, 0};
        game.getBoard().setHoles(testHoles2);
        easyAI.decideBestPlay(game);
        assertEquals(8, easyAI.getBestPlay());

    }

    //!!!!!!hvordan teste tilfeldige tall? !!!!!!
    @Test
    public void testRandomPlay() {
        easyAI.calculateRandomPlay(game);
    }


    @Test
    public void testHomePlayer1() {
        Integer[] testHoles =  {0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0};
        game.getBoard().setHoles(testHoles);
        easyAI.calculateHomePlay(game);
        assertEquals(7, easyAI.getBestPlay());
        Integer[] testHoles2 =  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0};
        game.getBoard().setHoles(testHoles2);
        easyAI.calculateHomePlay(game);
        assertEquals(11, easyAI.getBestPlay());
    }


}
