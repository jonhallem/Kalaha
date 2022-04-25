package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("Tests main method of boardclass, which also tests the supportingmethod checkIfHome")
    @Test
    public void testPlaceStones() {
        //testing initial placement of stone with last stone in home
        assertFalse(board.getAnotherRound());
        board.placeStones(0);
        assertEquals(Arrays.asList(0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0), board.getHoles());
        //when placing stones and the last stone lands in home, player should get another round
        assertTrue(board.getAnotherRound());
        board.placeStones(1);
        //then it should reset, and the player should no longer get another round
        //testing enemy hole placement with last in home
        assertFalse(board.getAnotherRound());
        board.setPlayerPlaying(false);
        board.placeStones(7);
        assertEquals(Arrays.asList(1, 0, 8, 8, 8, 8, 2, 0, 8, 7, 7, 7, 7, 1), board.getHoles());
        //now remains to test if the placestone mechanic loops properly
        board.placeStones(12);
        assertEquals(Arrays.asList(2, 1, 9, 9, 9, 9, 2, 0, 8, 7, 7, 7, 0, 2), board.getHoles());

        //this tests some of the calculations for placement and home logic, the remaining logic will be tested in the gameclass test 
    }

    @DisplayName("When a stone is placed in an empty hole, steal the other players stones")
    @Test
    public void testStoneInEmptyHole() {
        Integer[] testHoles =  {2, 1, 0, 2, 0, 2, 0, 3, 1, 1, 2, 2, 0, 0};
        board.setHoles(testHoles);
        //remove stones and steal the enemy stones (and place in home)
        board.placeStones(0);
        assertEquals(Arrays.asList(0, 2, 0, 2, 0, 2, 3, 3, 1, 1, 0, 2, 0, 0), board.getHoles());
        //test for no changes when not empty hole
        board.placeStones(1);
        assertEquals(board.getHoles(), Arrays.asList(0, 0, 1, 3, 0, 2, 3, 3, 1, 1, 0, 2, 0, 0));
        //test for no changes when wrong player
        board.placeStones(9);
        assertEquals(board.getHoles(), Arrays.asList(0, 0, 1, 3, 0, 2, 3, 3, 1, 0, 1, 2, 0, 0));

        //check for player 2
        board.setPlayerPlaying(false);
        board.placeStones(8);
        assertEquals(board.getHoles(), Arrays.asList(0, 0, 1, 0, 0, 2, 3, 3, 0, 0, 1, 2, 0, 4));
        //test for no changes when not empty hole and wrong player
        board.placeStones(9);
        assertEquals(board.getHoles(), Arrays.asList(0, 0, 1, 0, 0, 2, 3, 3, 0, 0, 1, 2, 0, 4));
        //test for no changes when wrong player
        board.placeStones(3);
        assertEquals(board.getHoles(), Arrays.asList(0, 0, 1, 0, 0, 2, 3, 3, 0, 0, 1, 2, 0, 4));
    }






}
