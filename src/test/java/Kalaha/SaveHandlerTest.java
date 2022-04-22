package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaveHandlerTest {
    
    private SaveHandler savehandler;
    private Game game;


    private static final String testSaveFileContent = """
        TestOne;TestTwo;true;false;false;H
        [6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]
        """;;

    private static final String testInvalidSaveFileContent = """
        TestInvalid123;TestInvalidTwo;t;fle;false;H;;
        [6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]""";;


    @BeforeAll
    public void setUp() throws IOException {
        System.out.println("Initialiserer...");
        savehandler = new SaveHandler();
        game = new Game("TestOne", "TestTwo", true, 6, "Human");

        Files.write(savehandler.getSavePath("testSave"), testSaveFileContent.getBytes());
        Files.write(savehandler.getSavePath("testInvalidSave"), testInvalidSaveFileContent.getBytes());

    }


    @Test
    public void testLoadNonExistingSave() {
        assertThrows(
            IOException.class,
            () -> savehandler.readSave("nonExistingSave", game),
            "IOException should be thrown if file does not exist!");
    }

    @Test
    public void testReadInvalidReceipt() {
        assertThrows(
                Exception.class,
                () -> savehandler.readSave("testInvalidSave", game),
                "If file is invalid, exception should be thrown");
    }

    @Test
    public void testWriteSave() throws IOException {

        // try {
		// 	savehandler.writeSave("testSave1", game);
		// } catch (FileNotFoundException e) {
		// 	fail("Could not load saved file");
		// 	return;
		// }

        // byte[] testFile = null, newFile = null;

		// try {
		// 	testFile = Files.readAllBytes(savehandler.getSavePath("testSave"));
		// } catch (IOException e) {
		// 	fail("Could not load test file");
		// }

        savehandler.writeSave("newSave", game);
        Path expectedFile = savehandler.getSavePath("newSave");
        Path actualFile = savehandler.getSavePath("testSave");
        assertEquals(Files.mismatch(expectedFile, actualFile), -1,
                "Contents of files are not the same");





    }



    @Test
    public void testReadSave() throws IOException {
        //changing all values for game
        game.playRound(1);
        game.setPlayer1("loadTest");
        game.setPlayer2("loadTestTwo");
        game.setGameOver(true);
        game.getBoard().setAnotherRound(true);
        game.setVersusAI('M');

        //confirm changes to game
        assertNotEquals("TestOne", game.getPlayer1());
        assertNotEquals("TestTwo", game.getPlayer2());
        assertFalse(game.getBoard().getPlayerPlaying());
        assertTrue(game.getBoard().getAnotherRound());
        assertTrue(game.getGameOver());
        assertNotEquals('H', game.getVersusAI());
        assertNotEquals(Arrays.asList(6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0), game.getBoard().getHoles());

        try {
            savehandler.readSave("testSave", game);
        } catch (Exception e) {
            fail("The save does not exist");
        }

        //check if loading correctly loads all values
        assertEquals("TestOne", game.getPlayer1());
        assertEquals("TestTwo", game.getPlayer2());
        assertTrue(game.getBoard().getPlayerPlaying());
        assertFalse(game.getBoard().getAnotherRound());
        assertFalse(game.getGameOver());
        assertEquals('H', game.getVersusAI());
        assertEquals(Arrays.asList(6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0), game.getBoard().getHoles());

    }


    @AfterAll
    public void cleanUp() {
        savehandler.getSavePath("newSave").toFile().delete();
        savehandler.getSavePath("testSave").toFile().delete();
        savehandler.getSavePath("testInvalidSave").toFile().delete();
    }

}
