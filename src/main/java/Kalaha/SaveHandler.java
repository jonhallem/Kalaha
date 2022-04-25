package Kalaha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SaveHandler implements ISaveHandler {

    @Override
    public void readSave(String filename, Game game) throws FileNotFoundException {
        
        try(Scanner scanner = new Scanner(getFile(filename))) {

            String[] saveData = scanner.nextLine().split(";");
            String[] saveDataHoles = scanner.nextLine().replace("[", "").replace("]", "").split(",");
            


            game.setPlayer1(saveData[0]);
            game.setPlayer2(saveData[1]);
            
            validateSavefile(saveData[2], saveData[3], saveData[4], saveData[5], saveDataHoles);
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
            scanner.close();

            System.out.println("Fullført loading");
            System.out.println(game);

        }
    }

    private void validateSavefile(String playerPlaying, String anotherRound, String gameOver, String versusAI, String[] holes) {

        if (!(playerPlaying.equals("true") || playerPlaying.equals("false"))) {
            throw new IllegalArgumentException("Cannot load corrupted file");
        }
        if (!(anotherRound.equals("true") || anotherRound.equals("false"))) {
            throw new IllegalArgumentException("Cannot load corrupted file");
        }
        if (!(gameOver.equals("true") || gameOver.equals("false"))) {
            throw new IllegalArgumentException("Cannot load corrupted file");
        }
        if (!(versusAI.charAt(0) == 'H' || versusAI.charAt(0) == 'E'  || versusAI.charAt(0) == 'M')) {
            throw new IllegalArgumentException("Cannot load corrupted file");
        }

        // //check if holes are more than the max number of stones with 6 in each (standard rule) or less than 0
        // int sum = 0;
        // for (Integer stones : holes) {
        //     sum += stones;
        // }

        // for (int i = 0; i < holes.length;i++) {
        //     sum += Integer.parseInt(holes[i]);
        //     i++;
        // if (sum > 72 || sum < 0) {
        //     throw new IllegalArgumentException("Cannot load corrupted file");
        // }
    }


    //Saves are stored in the resources project folder, in the subfolder "Saves/"
    @Override
    public void writeSave(String filename, Game game) throws FileNotFoundException {
        
        validateSaveName(filename);

        try(PrintWriter writer = new PrintWriter(getFile(filename))) {

            writer.println(game.getPlayer1() +";"+ game.getPlayer2() +";"+game.getBoard().getPlayerPlaying() +";"+ Boolean.toString(game.getBoard().getAnotherRound()) +";"+ Boolean.toString(game.getGameOver()) + ";" + game.getVersusAI());
            writer.println(game.getBoard().toString());

            writer.flush();
            writer.close();
        }
        
    }

    private void validateSaveName(String name) {
            if (!Pattern.matches("[A-ZÆØÅa-zæøå0-9]*", name)) {
                throw new IllegalArgumentException("Filename can only consist of letters and numbers!");
            }
    }

    private static File getFile(String filename) {
        return new File("src/main/resources/Kalaha/Saves/" + filename + ".txt");
    }


    //method for finding correct path during testing
    public Path getSavePath(String filename) {
        return Path.of("src/main/resources/Kalaha/Saves/" + filename + ".txt");
    }


}
