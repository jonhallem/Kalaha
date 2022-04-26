package Kalaha;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
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

    @Test
    public void testConstructorCorrectly() {
       assertEquals("Alice", game.getPlayer1());
       assertEquals("Bob", game.getPlayer2());
       assertTrue(game.getBoard().getPlayerPlaying());
       assertEquals(Arrays.asList(6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0), game.getBoard().getHoles());
       assertEquals('H', game.getVersusAI());
       assertEquals("0", game.getPlayer1Score());
       assertEquals("0", game.getPlayer2Score());
    }

    @Test
    public void testNameValidation() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, 
        () -> new Game("12345", "Bob", true, 6, "Human"));
        assertTrue(exception.getMessage().startsWith("Name can only consist"));

        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, 
        () -> new Game("Alice", "12345", true, 6, "Human"));
        assertTrue(exception2.getMessage().startsWith("Name can only consist"));

        Exception exception3 = Assertions.assertThrows(IllegalArgumentException.class, 
        () -> new Game("Øyvind#Nål", "Åge.Æleksander", true, 6, "Human"));
        assertTrue(exception3.getMessage().startsWith("Name can only consist"));

        assertThrows(IllegalArgumentException.class,
        () -> game.setPlayer1("1234."));

        assertThrows(IllegalArgumentException.class,
        () -> game.setPlayer2("1234#"));
    }

    @DisplayName ("Main method for playing a round in the game. The playRound method calls various supportmethods")
    @Test
    public void testPlayRoundHuman() {
        //play one round and check if all the values are set correctly
        game.playRound(5);
        assertEquals(Arrays.asList(6, 6, 6, 6, 6, 0, 1, 7, 7, 7, 7, 7, 6, 0), game.getBoard().getHoles());
        assertFalse(game.getBoard().getPlayerPlaying());
        assertEquals("1", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());
        assertFalse(game.getGameOver());

        //check if playRound does not allow wrong input values
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(6));
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(13));
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(1000));
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(-1000));

        //tests for wrong player inputs
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(1));
        game.changePlayer();
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(10));

        //tests if playRound correctly ends game
        Integer[] testHoles =  {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        game.getBoard().setHoles(testHoles);
        game.playRound(0);
        assertTrue(game.getGameOver());
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
        assertDoesNotThrow(() ->  game.playRound(0));
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(0));
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(7));

        //check player 2
        game.getBoard().setAnotherRound(false);
        game.changePlayer();
        assertDoesNotThrow(() -> game.playRound(7));
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(7));
        assertThrows(IllegalArgumentException.class,
        () -> game.playRound(0));
    }

    @Test
    public void testGameOverPlayer1() {
        Integer[] testHoles =  {5, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 10};
        game.getBoard().setHoles(testHoles);
        game.playRound(0);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 14), game.getBoard().getHoles());
        assertTrue(game.getGameOver());
    }

    @Test
    public void testGameOverPlayer2() {
        game.changePlayer();
        Integer[] testHoles =  {0, 0, 0, 0, 0, 0, 10, 5, 0, 0, 0, 0, 0, 10};
        game.getBoard().setHoles(testHoles);
        game.playRound(7);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 14, 0, 0, 0, 0, 0, 0, 11), game.getBoard().getHoles());
        assertTrue(game.getGameOver());
    }

    //testing one getter with more "advanced functionality"
    @Test
    public void testUpdateScore() {
        assertEquals("0", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());
        game.playRound(3);
        assertEquals("1", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());
        game.playRound(8);
        assertEquals("1", game.getPlayer1Score());   
    }


    //test AI
    @Test
    public void testAI() {
        //test if AI plays a round after the player
        game.setVersusAI('E');
        Integer[] testHoles =  {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0};
        game.getBoard().setHoles(testHoles);
        game.playRound(0);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4), game.getBoard().getHoles());


        game.setVersusAI('M');
        Integer[] testHoles2 =  {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0};
        game.getBoard().setHoles(testHoles2);
        game.playRound(0);
        assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4), game.getBoard().getHoles());
    }


    
}
