package Kalaha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

// Be sure to finish a game first for the scoreboard class to create the correct directory in user.home folder
// If the test fails at first, be sure to run it a few times to delete excess files.

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScoreboardTest {

    private Scoreboard scoreboard;
    private Game game;

    // this project is made on MAC, the \\R replacement is an attempt to make it work on on Windows
    private String createScoreBoardContent() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        String time = dateFormat.format(date);

        String text = """
            TestOne;0;TestTwo;0;""".replaceAll("\\R", System.getProperty("line.separator"));

        System.out.println(text + time);
        
        return text + time + "\n";
    }

    private String createScoreBoardContentWithoutNewLine() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        String time = dateFormat.format(date);

        String text = """
            TestOne;0;TestTwo;0;""".replaceAll("\\R", System.getProperty("line.separator"));

        System.out.println(text + time);
        
        return text + time;
    }

    @BeforeAll
    public void setUp() throws IOException {
        System.out.println("Initialiserer...");
        scoreboard = new Scoreboard();
        game = new Game("TestOne", "TestTwo", true, 0, "Human");

        // In case the examinator does not run the app first, this code makes sure the correct directory is made in the user.home folder
        Files.createDirectories(Path.of(System.getProperty("user.home"), "tdt4100Kalaha", "scoreboard"));


        Files.write(scoreboard.getFile("testScoreBoard").toPath(), createScoreBoardContent().getBytes());
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
        Path expectedFile = scoreboard.getFile("newScoreBoard").toPath();
        Path actualFile = scoreboard.getFile("testScoreBoard").toPath();
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

    @DisplayName("Method to make sure sorting works alphabetically")
    @Test
    public void testSortingByAlphabet() {
        List<String> b = Arrays.asList("Bbb");
        List<String> a =Arrays.asList("Aaa");
        List<String> c =Arrays.asList("Ccc");
        List<List<String>> list = Arrays.asList(c, b, a);
        assertEquals("[[Ccc], [Bbb], [Aaa]]", list.toString());
        Collections.sort(list, new ScoreBoardByNameComparator<>());
        assertEquals("[[Aaa], [Bbb], [Ccc]]", list.toString());
    }

    @DisplayName("Method to make sure sorting works by date")
    @Test
    public void testSortingByDate() {
        List<String> a = Arrays.asList("A", "0", "B" , "0", "29/04/2022 18:09");
        List<String> b =Arrays.asList("A", "0", "B" , "0", "29/04/1994 15:09");
        List<String> c =Arrays.asList("A", "0", "B" , "0", "29/04/2021 00:00");
        List<List<String>> list = Arrays.asList(a, b, c);
        assertEquals("[[A, 0, B, 0, 29/04/2022 18:09], [A, 0, B, 0, 29/04/1994 15:09], [A, 0, B, 0, 29/04/2021 00:00]]", list.toString());
        Collections.sort(list, new ScoreBoardByDateComparator<>());
        assertEquals("[[A, 0, B, 0, 29/04/2022 18:09], [A, 0, B, 0, 29/04/2021 00:00], [A, 0, B, 0, 29/04/1994 15:09]]", list.toString());
    }


    // For each completed game the scoreboard appends a game with a new line to the existing scoreboard. Deletion after testing is therefore necessary. 
    @AfterAll
    public void cleanUp() {
        System.out.println("Deleting testfiles...");
        scoreboard.getFile("testScoreBoard").delete();
        scoreboard.getFile("newScoreBoard").delete();
    }
    
}
