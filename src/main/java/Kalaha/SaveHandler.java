package Kalaha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class SaveHandler implements ISaveHandler {

    @Override
    public void readSave(String filename, Game game) throws FileNotFoundException {
        
        try(Scanner scanner = new Scanner(getFile(filename))) {

            String[] saveData = scanner.nextLine().split(";");
            String[] saveDataHoles = scanner.nextLine().replace("[", "").replace("]", "").split(",");
            
            game.setPlayer1(saveData[0]);
            game.setPlayer2(saveData[1]);
            game.getBoard().setPlayerPlaying(Boolean.parseBoolean(saveData[2]));
            game.getBoard().setAnotherRound(Boolean.parseBoolean(saveData[3]));
            game.setVersusAI(saveData[5].charAt(0));

            if (saveData[4].equals("true")) {
                game.setGameOver(true);
            } else {
                game.setGameOver(false);
            }

            for (int i = 0; i < saveDataHoles.length;) {
                String temp = saveDataHoles[i].strip();
                int number = Integer.parseInt(temp);
                game.getBoard().setStones(i, Integer.valueOf(number));
                i++;
            }

            game.updateScore();

            System.out.println("Fullført loading");
            System.out.println(game);

        }
    }

    //!!!!!!are they
    //Saves are stored in the default project folder, for easier access by the examinator
    @Override
    public void writeSave(String filename, Game game) throws FileNotFoundException {
        
        try(PrintWriter writer = new PrintWriter(getFile(filename))) {

            writer.println(game.getPlayer1() +";"+ game.getPlayer2() +";"+game.getBoard().getPlayerPlaying() +";"+ Boolean.toString(game.getBoard().getAnotherRound()) +";"+ Boolean.toString(game.getGameOver()) + ";" + game.getVersusAI());
            writer.println(game.getBoard().toString());
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }


    public static File getFile(String filename) {
    return new File(SaveHandler.class.getResource("Saves/").getFile() + filename + ".txt");
    }


    //method for finding correct path during testing
    public Path getSavePath(String filename) {
        return Path.of(SaveHandler.class.getResource("Saves/").getFile() + filename + ".txt");
    }

    
    
    

}
