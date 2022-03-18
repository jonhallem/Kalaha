package Kalaha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveHandler implements ISaveHandler {

    @Override
    public Game readSave(String filename, Game game) throws FileNotFoundException {
        
        
        try(Scanner scanner = new Scanner(getFile(filename))) {
        
            String[] saveData = scanner.nextLine().split(";");
        
            new Game(saveData[0], saveData[1], Boolean.parseBoolean(saveData[2]), 0);

        } catch (Exception e) {
                //TODO: handle exception
        }

        return null;

    }

    @Override
    public void writeSave(String filename, Game game) throws FileNotFoundException {
        
        try(PrintWriter writer = new PrintWriter(getFile(filename))) {

            writer.println(game.getPlayer1() +";"+ game.getPlayer2() +";"+game.getBoard().getPlayerPlaying() +";"+ Boolean.toString(game.getBoard().getAnotherRound()) +";"+ game.getBoard().toString());
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }


    private static File getFile(String filename) {
        return new File(filename + ".txt");
    }
    
    

}
