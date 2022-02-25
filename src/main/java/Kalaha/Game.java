package Kalaha;

import java.util.regex.Pattern;

public class Game {
    private String player1;
    private String player2;
    private int player1Score = 0;
    private int player2Score = 0;
    private boolean whoIsPlaying = true;

    //ToString metode
    @Override
    public String toString() {
        return "Spillet består av " + player1 + " og " + player2 + ", og " + player1 + " starter!";
    }


    //konstruktør av spillere me validering, uten æ,ø,å akkurat nå. evnt gyldig med tall.
    public Game(String player1, String player2, boolean startingPlayer, Board board) {
        if (!Pattern.matches("[A-Za-z ]*", player1) && !Pattern.matches("[A-Za-z ]*", player1)) {
            throw new IllegalArgumentException("Navnet kan bare bestå av bokstaver og opprom!");
        }

        this.player1 = player1;
        this.player2 = player2;
        this.whoIsPlaying = startingPlayer;
    }
//lag felt med board eller newBoard metode



    public void playRound(Board board, int index) {

        board.checkIfHome(board.playStones(index));


        }


        
    }


    public void playerPlaying() {
        // if (this.whoIsPlaying.equals(null)) {
        //     //random
        // }
        if (whoIsPlaying() == true) {
            return false;
        }
        return true;
    }
    









    //getters
    public String getPlayer1() {
        return player1;
    }


    public String getPlayer2() {
        return player2;
    }



    public static void main(String[] args) {
        Game game1 = new Game("Jon Pape", "Jarl", true);
        System.out.println(game1);
    }



}
