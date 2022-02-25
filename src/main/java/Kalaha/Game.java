package Kalaha;

import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.List;

public class Game {
    private String player1;
    private String player2;
    private int player1Score = 0;
    private int player2Score = 0;
    private boolean playerPlaying;
    private Board board;

    //ToString metode
    @Override
    public String toString() {
        return "Spillet består av " + player1 + " og " + player2 + ", og " + player1 + " starter!";
    }


    //konstruktør av spillere me validering, uten æ,ø,å akkurat nå. evnt gyldig med tall.
    public Game(String player1, String player2, boolean startingPlayer,List<Integer> holes) {
        if (!Pattern.matches("[A-Za-z ]*", player1) && !Pattern.matches("[A-Za-z ]*", player1)) {
            throw new IllegalArgumentException("Navnet kan bare bestå av bokstaver og opprom!");
        }

        this.player1 = player1;
        this.player2 = player2;
        this.playerPlaying = startingPlayer;
        this.board = new Board(holes);
        
    }
//lag felt med board eller newBoard metode



    public void playRound(int index) {
        //a
        if (board.checkIfHome(board.playStones(index)) == false) {
            changePlayer();
            }
        }


        


    public void changePlayer() {
        // if (this.whoIsPlaying.equals(null)) {
        //     //random
        // }
        if (this.playerPlaying == true) {
            this.playerPlaying = false;
        }
        else {
            this.playerPlaying = true;
        }
    }
    









    //getters
    public String getPlayer1() {
        return player1;
    }


    public String getPlayer2() {
        return player2;
    }



    public static void main(String[] args) {

        Board board1 = new Board(Arrays.asList(6,6,6,6,6,6, 0 , 6,6,6,6,6,6, 0 ));
        System.out.println(board1);
        board1.playStones(0);
        System.out.println(board1);
    }



}
