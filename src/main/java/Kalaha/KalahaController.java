package Kalaha;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class KalahaController {

    private Game game;

    @FXML
    private GridPane background;

    @FXML
    private Label info, stoneLabel;

    @FXML
    private TextField player1Name, player2Name;

    @FXML
    private CheckBox playerStarting;

    @FXML
    private TextField startingStones;

    @FXML
    private Button startGame;

    @FXML
    private Button home6, home13;

    @FXML
    private Button hole0, hole1, hole2, hole3, hole4, hole5, hole7, hole8, hole9, hole10, hole11, hole12;

    public void initialize() {
    }

    public void startGame() {
        game = new Game(player1Name.getText(), player2Name.getText(), playerStarting.isSelected(), Integer.parseInt(startingStones.getText()));
        hole0.setDisable(false); hole1.setDisable(false); hole2.setDisable(false); hole3.setDisable(false); hole4.setDisable(false); hole5.setDisable(false); 
        hole7.setDisable(false); hole8.setDisable(false); hole9.setDisable(false); hole10.setDisable(false); hole11.setDisable(false); hole12.setDisable(false); 
        
        showPlaying();
        updateHoles();
        player1Name.setVisible(false);
        player2Name.setVisible(false);
        playerStarting.setVisible(false);
        stoneLabel.setVisible(false);
        startingStones.setVisible(false);
        startGame.setVisible(false);
    } 
    public void hole0() {
        game.playRound(0);
        updateScore();
        updateHoles();
    }

    public void hole1() {
        game.playRound(1);
        updateScore();
        updateHoles();
    }

    public void hole2() {
        game.playRound(2);
        updateScore();
        updateHoles();
    }

    public void hole3() {
        game.playRound(3);
        updateScore();
        updateHoles();
    }

    public void hole4() {
        game.playRound(4);
        updateScore();
        updateHoles();
    }

    public void hole5() {
        game.playRound(5);
        updateScore();
        updateHoles();
    }


    public void hole7() {
        game.playRound(7);
        updateScore();
        updateHoles();
    }

    public void hole8() {
        game.playRound(8);
        updateScore();
        updateHoles();
    }

    public void hole9() {
        game.playRound(9);
        updateScore();
        updateHoles();
    }

    public void hole10() {
        game.playRound(10);
        updateScore();
        updateHoles();
    }

    public void hole11() {
        game.playRound(11);
        updateScore();
        updateHoles();
    }

    public void hole12() {
        game.playRound(12);
        updateScore();
        updateHoles();
    }

    public void updateScore() {
        home6.setText(game.getPlayer1Score());
        home13.setText(game.getPlayer2Score());
        showPlaying();
        handleGameOver(); 
    }

    public void showPlaying() {
        if (game.getBoard().getPlayerPlaying() == true) {
            info.setText("It is " + game.getPlayer1() + "'s turn!");
            info.setStyle("-fx-background-color: green;");
        } else {
            info.setText("It is " + game.getPlayer2() + "'s turn!");
            info.setStyle("-fx-background-color: red;");
        }
    }

    public void handleGameOver() {
        if (game.getGameOver() == true) {
            //disable buttons if game is over
            hole0.setDisable(true); hole1.setDisable(true); hole2.setDisable(true); hole3.setDisable(true); hole4.setDisable(true); hole5.setDisable(true); 
            hole7.setDisable(true); hole8.setDisable(true); hole9.setDisable(true); hole10.setDisable(true); hole11.setDisable(true); hole12.setDisable(true); 
            info.setStyle("-fx-background-color: yellow;");

            //post winner
            if (Integer.parseInt(game.getPlayer1Score()) > Integer.parseInt(game.getPlayer2Score())) {
                info.setText("The game is over! " + game.getPlayer1() + " won!");
            } else {
                info.setText("The game is over! " + game.getPlayer2() + " won!");
            }
        }
        
    }

    public void updateHoles() {
        hole0.setText(String.valueOf(game.getBoard().getStones(0)));
        hole1.setText(String.valueOf(game.getBoard().getStones(1)));
        hole2.setText(String.valueOf(game.getBoard().getStones(2)));
        hole3.setText(String.valueOf(game.getBoard().getStones(3)));
        hole4.setText(String.valueOf(game.getBoard().getStones(4)));
        hole5.setText(String.valueOf(game.getBoard().getStones(5)));

        hole7.setText(String.valueOf(game.getBoard().getStones(7)));
        hole8.setText(String.valueOf(game.getBoard().getStones(8)));
        hole9.setText(String.valueOf(game.getBoard().getStones(9)));
        hole10.setText(String.valueOf(game.getBoard().getStones(10)));
        hole11.setText(String.valueOf(game.getBoard().getStones(11)));
        hole12.setText(String.valueOf(game.getBoard().getStones(12)));
    }





}
