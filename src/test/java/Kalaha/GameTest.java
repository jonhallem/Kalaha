package Kalaha;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        System.out.println("Initialiserer...");
        game = new Game("Alice", "Bob", true, 6, "Human");
        
    }

    // public void testConstructor() {
    //     Game nameTestGame = new Game("321player", "Bob", true, 6, "Human");
    //     assertThrows(IllegalArgumentException.class, () -> nameTestGame);
    // }


    @DisplayName ("Main method for playing a round in the game. This method calls various supportmethods which will be tested on their own as well")
    @Test
    public void testPlayRoundHuman() {
        
    }


    @Test
    public void testChangePlayer() {
        //checks if the changeplayer method correctly changes player
        assertTrue(game.getBoard().getPlayerPlaying());
        assertFalse(game.getBoard().getAnotherRound());
        game.changePlayer();
        assertFalse(game.getBoard().getPlayerPlaying());


        //when set to another round, the changeplayer method should not change the player
        game.getBoard().setAnotherRound(true);
        game.changePlayer();
        assertFalse(game.getBoard().getPlayerPlaying());

        //then check the same for player 1
        game.getBoard().setAnotherRound(false);
        game.changePlayer();
        assertFalse(game.getBoard().getAnotherRound());
        assertTrue(game.getBoard().getPlayerPlaying());
        game.getBoard().setAnotherRound(true);
        game.changePlayer();
        assertTrue(game.getBoard().getPlayerPlaying());

    }


    @DisplayName ("Testing inputs for validhole. This method should stop the player from choosing a wrong hole during their turn")
    @Test
    public void testValidHole() {
        //check player1
        assertDoesNotThrow(() ->  game.isValidHole(0));
        assertThrows(IllegalArgumentException.class,
        () -> game.isValidHole(7));

        //switch to player 2
        game.changePlayer();
        assertDoesNotThrow(() -> game.isValidHole(7));
        assertThrows(IllegalArgumentException.class,
        () -> game.isValidHole(0));

    }

    @Test
    public void testGameOverPlayer1() {
        Integer[] testHoles =  {0, 0, 0, 0, 0, 0, 10, 0, 5, 0, 0, 0, 0, 10};
        game.getBoard().setHoles(testHoles);
        game.checkIfGameOver();
        assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 10), game.getBoard().getHoles());
        assertTrue(game.getGameOver());
    }

    @Test
    public void testGameOverPlayer2() {
        Integer[] testHoles =  {0, 0, 0, 5, 0, 0, 10, 0, 0, 0, 0, 0, 0, 10};
        game.getBoard().setHoles(testHoles);
        game.checkIfGameOver();
        assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 15), game.getBoard().getHoles());
        assertTrue(game.getGameOver());
    }



    
}
