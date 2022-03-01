package Kalaha;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class KalahaController {

    private Game game;
    private Board board;

    @FXML
    private Label score1;

    @FXML
    private Label score2;

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
        board = new Board(6);
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

        updateHoles();
    }

    public void updateScore() {
        score1.setText(game.getPlayer1Score());
        score2.setText(game.getPlayer2Score());
    }

    public void updateHoles() {
        hole00.setText(String.valueOf(board.getStones(0)));
        hole1.setText(String.valueOf(board.getStones(1)));
        hole2.setText(String.valueOf(board.getStones(2)));
        hole3.setText(String.valueOf(board.getStones(3)));
        hole4.setText(String.valueOf(board.getStones(4)));
        hole5.setText(String.valueOf(board.getStones(6)));

        hole0.setText(String.valueOf(board.getStones(7)));
        hole1.setText(String.valueOf(board.getStones(8)));
        hole2.setText(String.valueOf(board.getStones(9)));
        hole3.setText(String.valueOf(board.getStones(10)));
        hole4.setText(String.valueOf(board.getStones(11)));
        hole5.setText(String.valueOf(board.getStones(12)));
    }





}
