package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaveHandlerTest {
    
    private SaveHandler savehandler;
    private Game game;


    private static final String testSaveFileContent = """
        TestOne;TestTwo;true;false;false;H
        [6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]
        """.replaceAll("\\R", System.getProperty("line.separator"));

    private static final String testInvalidSaveFileContent = """
        TestInvalid;TestInvalidTwo;t;fle;false;H;;
        [6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]""".replaceAll("\\R", System.getProperty("line.separator"));


    @BeforeAll
    public void setUp() throws IOException {
        System.out.println("Initialiserer...");
        savehandler = new SaveHandler();
        game = new Game("TestOne", "TestTwo", true, 6, "Human");

        Files.write(savehandler.getFile("testSave").toPath(), testSaveFileContent.getBytes());
        Files.write(savehandler.getFile("testInvalidSave").toPath(), testInvalidSaveFileContent.getBytes());

    }


    @Test
    public void testLoadNonExistingSave() {
        assertThrows(
            IOException.class,
            () -> savehandler.readSave("nonExistingSave", game),
            "IOException should be thrown if file does not exist!");
    }

    @DisplayName("Testing validateSaveFile method for corrupted files/input")
    @Test
    public void testReadInvalidReceipt(){
        assertThrows(
                IllegalArgumentException.class,
                () -> savehandler.readSave("testInvalidSave", game),
                "Cannot load corrupted file");
    }

    @Test
    public void testWriteSave() throws IOException {

        savehandler.writeSave("newSave", game);
        Path expectedFile = savehandler.getFile("newSave").toPath();
        Path actualFile = savehandler.getFile("testSave").toPath();
        assertEquals(Files.mismatch(expectedFile, actualFile), -1,
                "Contents of files are not the same");
    }

    @Test
    public void testSaveFileNameValidation() {
        assertThrows(IllegalArgumentException.class,
        () -> savehandler.writeSave("Åge.txt", game));

        assertThrows(IllegalArgumentException.class,
        () -> savehandler.writeSave("Åge32#", game));
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
        System.out.println("Deleting testfiles...");
        savehandler.getFile("newSave").delete();
        savehandler.getFile("testSave").delete();
        savehandler.getFile("testInvalidSave").delete();
    }

}
