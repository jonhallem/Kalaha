package Kalaha;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class KalahaController {

    private Game game;

    @FXML
    private Label info;

    @FXML
    private TextArea player1Name;

    @FXML
    private TextArea player2Name;

    @FXML
    private CheckBox playerStarting;

    @FXML
    private TextField startingStones;

    @FXML
    private Button startGame;

    @FXML
    private Button home6;

    @FXML
    private Button home13;

    @FXML
    public Label hole00;
    @FXML
    private Button hole0;
    @FXML
    public Button hole1;
    @FXML
    public Button hole2;
    @FXML
    public Button hole3;
    @FXML
    public Button hole4;
    @FXML
    public Button hole5;
    @FXML
    public Button hole7;
    @FXML
    public Button hole8;
    @FXML
    public Button hole9;
    @FXML
    public Button hole10;
    @FXML
    public Button hole11;
    @FXML
    public Button hole12;


    public void initialize() {
    }

    public void startGame() {
        game = new Game(player1Name.getText(), player2Name.getText(), playerStarting.isSelected(), Integer.parseInt(startingStones.getText()));
        info.setText("Game created!");
        player1Name.setVisible(false);
        player2Name.setVisible(false);
        playerStarting.setVisible(false);
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
        if (game.getBoard().getPlayerPlaying() == true) {
            info.setText("It is " + game.getPlayer1() + "'s turn!");
            info.setTextFill(Color.GREEN);
        } else {
            info.setText("It is " + game.getPlayer2() + "'s turn!");
            info.setTextFill(Color.RED);
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
