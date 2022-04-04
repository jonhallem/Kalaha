package Kalaha;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Scoreboard {

    List<String> scoreBoardList;

    //method for saving completed games
    public void scoreBoardSave(Game game) throws FileNotFoundException {

        //!!!!!!!!IS this a good method?!!!!!!
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("scoreboard.txt", true))) {

            Calendar date = Calendar.getInstance();
            
            bw.write( game.getPlayer1() +": "+ game.getPlayer1Score() +" vs. "+ game.getPlayer2() +": "+ game.getPlayer2Score() +" - "+date.getTime() + "\n");
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public List<String> scoreBoardLoad() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File("scoreboard.txt"))) {

            List<String> scoreList = new ArrayList<>();
            
            while (scanner.hasNextLine()) {
                String savedata = scanner.nextLine();
                System.out.println(savedata);
                scoreList.add(savedata);
            }

        //sort by newest entry first
        Collections.reverse(scoreList);
        
        return scoreList;


        } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Feil i scanner" + e);
                return null;
        }
    }

    //getter for scoreBoardList
    public List<String> getScoreBoardList() {
        return scoreBoardList;
    }
    


}
