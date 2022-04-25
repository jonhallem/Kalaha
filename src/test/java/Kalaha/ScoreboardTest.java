package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScoreboardTest {

    private Scoreboard scoreboard;
    private Game game;

    private String createScoreBoardContent() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        String time = dateFormat.format(date);

        String text = """
            TestOne;0;TestTwo;0;""";

        System.out.println(text + time);
        
        return text + time + "\n";
    }

    private String createScoreBoardContentWithoutNewLine() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        String time = dateFormat.format(date);

        String text = """
            TestOne;0;TestTwo;0;""";

        System.out.println(text + time);
        
        return text + time;
    }

    @BeforeAll
    public void setUp() throws IOException {
        System.out.println("Initialiserer...");
        scoreboard = new Scoreboard();
        game = new Game("TestOne", "TestTwo", true, 0, "Human");

        Files.write(scoreboard.getScoreBoardPath("testScoreBoard"), createScoreBoardContent().getBytes());
    }

    @Test
    public void testLoadNonExistingScoreBoard() {
        assertThrows(
            IOException.class,
            () -> scoreboard.scoreBoardLoad("NonExistingScoreBoard"),
            "IOException should be thrown if file does not exist!");
    }

    @DisplayName("Tests if the scoreboard is created correctly. This one might be false if the date timer switches minute while testing; if so, try again")
    @Test
    public void testWriteScoreBoard() throws IOException {

        scoreboard.scoreBoardSave("newScoreBoard", game);
        Path expectedFile = scoreboard.getScoreBoardPath("newScoreBoard");
        Path actualFile = scoreboard.getScoreBoardPath("testScoreBoard");
        assertEquals(Files.mismatch(expectedFile, actualFile), -1,
                "Contents of files are not the same");
    }

    @Test
    public void testReadSave() throws IOException {
        try {
            scoreboard.scoreBoardLoad("testScoreBoard");
        } catch (Exception e) {
            fail("The save does not exist");
        }
        assertEquals(Arrays.asList(Arrays.asList(createScoreBoardContentWithoutNewLine().split(";"))).toString(), scoreboard.getScoreBoardListString().toString(), "The contents are not the same, double check if the file have multiple lines and delete the file if necessary to restart the test");
    }




    //For each completed game the scoreboard appends a game with a new line to the existing scoreboard. Deletion after testing is therefore necessary. 
    @AfterAll
    public void cleanUp() {
        scoreboard.getScoreBoardPath("testScoreBoard").toFile().delete();
        scoreboard.getScoreBoardPath("newScoreBoard").toFile().delete();
    }
    
}
