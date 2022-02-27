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



    // //ToString metode
    // @Override
    // public String toString() {
    //     return "Spillet består av " + player1 + " og " + player2 + ", og " + player1 + " starter!";
    // }


    @Override
    public String toString() {
        return "Game [board=" + board + ", player1=" + player1 + ", player1Score=" + player1Score + ", player2="
                + player2 + ", player2Score=" + player2Score + ", playerPlaying=" + playerPlaying + "]";
    }



    //konstruktør av spillere me validering, uten æ,ø,å akkurat nå. evnt gyldig med tall.
    public Game(String player1, String player2, boolean startingPlayer, int holes) {
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
        
        isValidHole(index);
        board.placeStones(index);



        }


        
    private void name() {
        
    }

    

    public boolean getPlayerPlaying() {
        return playerPlaying;
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
    
    public void isValidHole(int index) {
        if (playerPlaying == true) {
            if (index != 0 && index != 1 && index != 2 && index != 3 && index != 4 && index != 5) {
                throw new IllegalArgumentException("You can't choose enemy holes or homes.");
            } 
        }else if (playerPlaying == false) {
            if (index != 7 && index != 8 && index != 9 && index != 10 && index != 11 && index != 12) {
                throw new IllegalArgumentException("You can't choose enemy holes or homes.");
            } 
        }
    }


    public void updateScore() {
        this.player1Score = board.getStones(6);
        this.player2Score = board.getStones(14);
    }

    //getters
    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }



    public static void main(String[] args) {
        Game game = new Game("Jon", "Jarl", true, 6);
        // Board board1 = new Board(6);
        // System.out.println(board1);
        // board1.playStones(0);
        // System.out.println(board1);
        System.out.println(game);
        game.playRound(5);
        System.out.println(game);
    }



}
