package Kalaha;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class KalahaController {

    private Game game;
    private Board board;

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
        game = new Game("Jon", "Jarl", true, 6);
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
        game.playRound(11);
        updateScore();
        updateHoles();
    }

    public void updateScore() {
        home6.setText(game.getPlayer1Score());
        home13.setText(game.getPlayer2Score());
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
