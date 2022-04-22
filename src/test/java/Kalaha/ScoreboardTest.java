package Kalaha;

import org.junit.jupiter.api.BeforeEach;

public class ScoreboardTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        System.out.println("Initialiserer...");
        game = new Game("Alice", "Bob", true, 6, "Human");
    }
    
}
