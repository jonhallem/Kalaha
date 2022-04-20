package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;
    

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Initialiserer...");
        board = new Board(6, true);
    }

    @Test
    public void testConstructorCorrectly() {
        assertEquals(Arrays.asList(6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0), board.getHoles());
        assertTrue(board.getPlayerPlaying());
        assertFalse(board.getAnotherRound());
     }


    // @BeforeEach
    // public void testConstructor() throws Exception {
    //     assertThrows(
    //         IllegalArgumentException.class,
    //         new Board(2, false));
    // }


    @Test
    public void testPlaceStones() {
        //testing initial placement of stone with last stone in home
        board.placeStones(0);
        assertEquals(board.getHoles(), Arrays.asList(0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0));
        //testing enemy hole placement with last in home
        board.setAnotherRound(false);
        board.setPlayerPlaying(false);
        board.placeStones(7);
        assertEquals(board.getHoles(), Arrays.asList(0, 7, 7, 7, 7, 7, 1, 0, 7, 7, 7, 7, 7, 1));
        //now remains to test if the placestone mechanic loops properly
        board.placeStones(12);
        assertEquals(board.getHoles(), Arrays.asList(1, 8, 8, 8, 8, 8, 1, 0, 7, 7, 7, 7, 0, 2));

        //this tests some of the mechanics for empty and home logic, but more testing is required
    }

    @Test
    public void testStoneInHome() {
        assertTrue(!board.getAnotherRound());
        //when placing stones and the last stone lands in home, player should get another round
        board.placeStones(0);
        assertTrue(board.getAnotherRound());
        // then it should reset, and the player should no longer get another round
        board.placeStones(1);
        assertTrue(!board.getAnotherRound());


        //testing the empty method
        board.setAnotherRound(false);
        board.checkIfHome(6);
        assertTrue(board.getAnotherRound());

        board = new Board(6, false);
        board.setAnotherRound(false);
        board.placeStones(7);
        assertTrue(board.getAnotherRound());

        board.setAnotherRound(false);
        board.setPlayerPlaying(false);
        board.checkIfHome(13);
        assertTrue(board.getAnotherRound());

    }

    @Test
    public void testStoneInEmptyHole() {
        Integer[] testHoles =  {2, 2, 0, 2, 0, 2, 0, 3, 1, 1, 2, 2, 0, 0};
        board.setHoles(testHoles);
        //remove stones and steal the enemy stones (and place in home). the empty hole is set to -1 to accomodate for the following final placeStones round
        board.checkIfEmpty(2);
        assertEquals(board.getHoles(), Arrays.asList(2, 2, -1, 2, 0, 2, 3, 3, 1, 1, 0, 2, 0, 0));
        //test for no changes when not empty hole and wrong player
        board.checkIfEmpty(1);
        assertEquals(board.getHoles(), Arrays.asList(2, 2, -1, 2, 0, 2, 3, 3, 1, 1, 0, 2, 0, 0));
        board.checkIfEmpty(9);
        assertEquals(board.getHoles(), Arrays.asList(2, 2, -1, 2, 0, 2, 3, 3, 1, 1, 0, 2, 0, 0));

        //check for player 2
        board.setPlayerPlaying(false);
        board.checkIfEmpty(12);
        assertEquals(board.getHoles(), Arrays.asList(0, 2, -1, 2, 0, 2, 3, 3, 1, 1, 0, 2, -1, 3));
        //test for no changes when not empty hole and wrong player
        board.checkIfEmpty(9);
        assertEquals(board.getHoles(), Arrays.asList(0, 2, -1, 2, 0, 2, 3, 3, 1, 1, 0, 2, -1, 3));
        board.checkIfEmpty(3);
        assertEquals(board.getHoles(), Arrays.asList(0, 2, -1, 2, 0, 2, 3, 3, 1, 1, 0, 2, -1, 3));
    }






}
