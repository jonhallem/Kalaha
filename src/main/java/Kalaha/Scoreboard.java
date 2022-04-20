package Kalaha;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Scoreboard {

    //kan fjerne?
    List<List<String>> scoreBoardList;

    //method for saving completed games
    //the scoreboard file is stored in the default project folder, for easier access by the examinator
    public void scoreBoardSave(Game game) throws FileNotFoundException {

        //!!!!!!!!IS this a good method?!!!!!!
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("scoreboard.txt", true))) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date date = new Date();
            
            bw.write(game.getPlayer1() +";"+ game.getPlayer1Score() +";"+ game.getPlayer2() +";"+ game.getPlayer2Score() +";"+dateFormat.format(date) + "\n");
            
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void scoreBoardLoad() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File("scoreboard.txt"))) {

            List<List<String>> scoreList = new ArrayList<>();
            
            while (scanner.hasNextLine()) {
                String[] savedata = scanner.nextLine().split(";");
                scoreList.add(Arrays.asList(savedata));


                // for (int i = 0; i < 5; i++) {
                //     savedata[i];
                // }
                System.out.println(scoreList);
            }
        
        this.scoreBoardList = scoreList;


        } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Feil i scanner" + e);
                this.scoreBoardList = null;
        }
    }

    //getter for scoreBoardList
    public List<String> getScoreBoardListString() {

        //gets the scoreboard and converts it into a string for the listview in the controller
        List<String> scoreBoardString = new ArrayList<String>();

        for (List<String> list : scoreBoardList) {
            scoreBoardString.add(list.toString());
        }

        return scoreBoardString;
    }



    //Source: https://stackoverflow.com/questions/35761864/java-sort-list-of-lists
    public void sortScoreBoardByPlayer1() {
        Collections.sort(scoreBoardList, new SortScoreBoardByName<>());
    }

    public void sortScoreBoardByTime() {
        Collections.sort(scoreBoardList, new SortScoreBoardByDate<>());
    }



}