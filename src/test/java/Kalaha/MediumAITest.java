package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MediumAITest {

    private MediumAI mediumAI;
    private Game game;

    @BeforeEach
    public void setUp() {
        System.out.println("Initialiserer...");
        mediumAI = new MediumAI();
        game = new Game("Alice", "Bob", true, 6, "Human");
        mediumAI.setBestPlay(99);
    }

    @DisplayName("Main method for finding a valid best play. The decidePlay method calls various supportmethods which will be tested on their own as well")
    @Test
    public void testDecidePlay() {
        //the best play will land the last stone in the home
        Integer[] testHoles =  {1, 1, 1, 1, 1, 1, 0, 6, 6, 6, 6, 6, 6, 0};
        game.getBoard().setHoles(testHoles);
        mediumAI.decideBestPlay(game);
        assertEquals(7, mediumAI.getBestPlay());

        //tests with a valid play that will land in an empty hole
        Integer[] testHoles2 =  {1, 1, 1, 1, 1, 1, 0, 0, 5, 2, 6, 0, 6, 0};
        game.getBoard().setHoles(testHoles2);
        mediumAI.decideBestPlay(game);
        assertEquals(8, mediumAI.getBestPlay());
    }

    @Test
    public void testEmptyPlay() {
        Integer[] testHoles =  {6, 10, 6, 6, 6, 6, 0, 1, 0, 6, 6, 6, 6, 0};
        game.getBoard().setHoles(testHoles);
        mediumAI.calculateEmptyPlay(game);
        assertEquals(7, mediumAI.getBestPlay());
    }


    
}
