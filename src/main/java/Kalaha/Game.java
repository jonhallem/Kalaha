package Kalaha;

import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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

        updateScore();
        checkIfGameOver();
        changePlayer();

        }


    public void changePlayer() {
        // if (this.whoIsPlaying.equals(null)) {
        //     //random
        // }
        if (board.isAnotherRound() == true) {
            //If anotherRound is set to true, do not change player
            return;
        }
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



    public void checkIfGameOver() {
        //if one side of the board is empty, start end game process
        if (board.getStones(0) == 0 && board.getStones(1) == 0 && board.getStones(2) == 0 && board.getStones(3) == 0 && board.getStones(4) == 0 && board.getStones(6) == 0) {
            //find all enemy stones
            int enemyStones = board.getStones(7)+board.getStones(8)+board.getStones(9)+board.getStones(10)+board.getStones(11)+board.getStones(12);
            //remove all enemy stones and add them to the home
            board.setStones(7, 0); board.setStones(8, 0); board.setStones(9, 0); board.setStones(10, 0); board.setStones(11, 0); board.setStones(12, 0);
            int home = board.getStones(6);
            board.setStones(6, home+enemyStones);

            updateScore();
            System.out.println("Game is over.");
        } else if (board.getStones(7) == 0 && board.getStones(8) == 0 && board.getStones(9) == 0 && board.getStones(10) == 0 && board.getStones(11) == 0 && board.getStones(12) == 0) {
            //find all enemy stones
            int enemyStones = board.getStones(0)+board.getStones(1)+board.getStones(2)+board.getStones(3)+board.getStones(4)+board.getStones(5);
            //remove all enemy stones and add them to the home
            board.setStones(0, 0); board.setStones(1, 0); board.setStones(2, 0); board.setStones(3, 0); board.setStones(4, 0); board.setStones(5, 0);
            int home = board.getStones(13);
            board.setStones(13, home+enemyStones);

            updateScore();
            System.out.println("Game is over.");
        }
    }

    public void updateScore() {
        this.player1Score = board.getStones(6);
        this.player2Score = board.getStones(13);
    }

    

    public String getPlayer1Score() {
        return String.valueOf(player1Score);
    }



    public String getPlayer2Score() {
        return String.valueOf(player2Score);
    }



    //getters
    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public boolean getPlayerPlaying() {
        return playerPlaying;
    }

    public Board getBoard() {
        return board;
    }



    public static void main(String[] args) {
        Game game = new Game("Jon", "Jarl", true, 6);
        System.out.println(game);
        game.playRound(0);
        game.playRound(1);
        game.playRound(7);
        game.playRound(0);
        System.out.println(game);
    }

}
