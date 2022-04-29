package Kalaha;

import java.util.regex.Pattern;

public class Game {
    private boolean gameOver;
    private String player1;
    private String player2;
    private char versusAI;
    private int player1Score = 0;
    private int player2Score = 0;
    private Board board;

        // ------------------- TOSTRING FOR TESTING ---------------

    @Override
    public String toString() {
        return "Game [board=" + board + ", player1=" + player1 + ", player1Score=" + player1Score + ", player2="
                + player2 + ", player2Score=" + player2Score + ", gameover=" + gameOver + "]";
    }


    // ------------------- CONSTRUCTOR WITH VALIDATION OF NAMES ---------------
    //the number of player-decided input variables are restricted by ComboBoxes/Checkboxes in the controller/GUI, therefore validation is kept to player textinput fields (playernames and filename)
    public Game(String player1, String player2, boolean startingPlayer, int holes, String versusAI) {
        validateName(player1);
        validateName(player2);

        this.gameOver = false;
        this.player1 = player1;

        //takes input from controller and converts it into char for playRound logic.
        //Also sets name to robot for easier scoreboard translation
        if (versusAI.equals("Easy")) {
            this.versusAI = 'E';
            this.player2 = "EasyRobot";
        } else if (versusAI.equals("Medium")) {
            this.versusAI = 'M';
            this.player2 = "MediumRobot";
        } else {
            this.versusAI = 'H';
            this.player2 = player2;
        }

        this.board = new Board(holes, startingPlayer);
        
    }


    // ---------------- VALIDATION OF PLAYERNAMES ---------------------
    private void validateName(String player) {
        if (!Pattern.matches("[A-ZÆØÅa-zæøå ]*", player)) {
            throw new IllegalArgumentException("Name can only consist of letters and spaces!");
        }
    }

    // ------------------ MAIN METHOD FOR RUNNING A ROUND -------------------------
    public void playRound(int index) {

        isValidHole(index);
        board.placeStones(index);

        updateScore();

        changePlayer();

        //checks if the player is playing versus AI, if so the AI can play a round
        //AI objects are created here, in case the players suddenly wants to switch who to play against
        if (getVersusAI() == 'E' && board.getPlayerPlaying() == false) {
            EasyAI robot = new EasyAI();
            playRound(robot.decideBestPlay(this));
        }

        if (getVersusAI() == 'M' && board.getPlayerPlaying() == false) {
            MediumAI robot = new MediumAI();
            playRound(robot.decideBestPlay(this));
        }

        checkIfGameOver();
    }

    //------------------ SUPPORTING METHODS -----------------
    //can be private, but because of use during testing this method is set to public 
    public void changePlayer() {
        // if (this.whoIsPlaying.equals(null)) {
        //     //random
        // }
        
        if (board.getAnotherRound() == true) {
            //If anotherRound is set to true, do not change player
            return;
        }
        if (board.getPlayerPlaying() == true) {
            board.setPlayerPlaying(false);
            
        }
        else {
            board.setPlayerPlaying(true);
        }
    }

    private void isValidHole(int index) {
        //double validating for checking if the player does a valid move. The double check is necessary for implementing the empty hole rule
        //you also can not pick an empty hole
        if (board.getPlayerPlaying() == true) {
            if (index != 0 && index != 1 && index != 2 && index != 3 && index != 4 && index != 5) {
                throw new IllegalArgumentException("You can't choose enemy holes or homes.");
            } else if (board.getStones(index) == 0) {
                throw new IllegalArgumentException("You can't choose an empty hole!");
            }
        }else if (board.getPlayerPlaying() == false) {
            if (index != 7 && index != 8 && index != 9 && index != 10 && index != 11 && index != 12) {
                throw new IllegalArgumentException("You can't choose enemy holes or homes.");
            } else if (board.getStones(index) == 0) {
                throw new IllegalArgumentException("You can't choose an empty hole!");
            }
        }
    }

    private void checkIfGameOver() {
        //if one side of the board is empty, start end game process
        //when one player has no stones on their side, the enemy picks up all the remaining stones on their side and puts them in their home
        if (board.getStones(0) == 0 && board.getStones(1) == 0 && board.getStones(2) == 0 && board.getStones(3) == 0 && board.getStones(4) == 0 && board.getStones(5) == 0) {
            //find all enemy stones
            int enemyStones = board.getStones(7)+board.getStones(8)+board.getStones(9)+board.getStones(10)+board.getStones(11)+board.getStones(12);
            //remove all enemy stones and add them to your home
            board.setStones(7, 0); board.setStones(8, 0); board.setStones(9, 0); board.setStones(10, 0); board.setStones(11, 0); board.setStones(12, 0);
            int enemyhome = board.getStones(6);
            board.setStones(6, enemyhome+enemyStones);

            setGameOver(true);
            updateScore();
            System.out.println("Game is over.");

        } else if (board.getStones(7) == 0 && board.getStones(8) == 0 && board.getStones(9) == 0 && board.getStones(10) == 0 && board.getStones(11) == 0 && board.getStones(12) == 0) {
            //find all enemy stones
            int enemyStones = board.getStones(0)+board.getStones(1)+board.getStones(2)+board.getStones(3)+board.getStones(4)+board.getStones(5);
            //remove all enemy stones and add them to your home
            board.setStones(0, 0); board.setStones(1, 0); board.setStones(2, 0); board.setStones(3, 0); board.setStones(4, 0); board.setStones(5, 0);
            int enemyhome = board.getStones(13);
            board.setStones(13, enemyhome+enemyStones);

            setGameOver(true);
            updateScore();
            System.out.println("Game is over.");

        }


    }



    // ---------------- GETTERS AND SETTERS ----------------------
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

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
    
    public void setPlayer1(String name) {
        validateName(name);
        this.player1 = name;
    }

    public void setPlayer2(String name) {
        validateName(name);
        this.player2 = name;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public char getVersusAI() {
        return versusAI;
    }

    public void setVersusAI(char AI) {
        this.versusAI = AI;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean bool) {
        this.gameOver = bool;
    }

}
