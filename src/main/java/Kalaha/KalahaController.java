package Kalaha;

import javafx.fxml.FXML;

public class KalahaController {







    @FXML
    private TextField firstNumber, secondNumber, operator;

    @FXML
    private Label result;

    private Calculator calculator;

    private void initCalculator(String operator) {
        calculator = new Calculator(operator);
    }

    @FXML
    private void handleButtonClick() {
        initCalculator(operator.getText());
        try {
            int result = calculator.calculate(Integer.parseInt(firstNumber.getText()),
                    Integer.parseInt(secondNumber.getText()));
            this.result.setText(firstNumber.getText() + " " + operator.getText() + " " + secondNumber.getText() + " = "
                    + String.valueOf(result));
        } catch (NumberFormatException e) {
            result.setText("Et eller begge tallene er ugyldige");
        }
    }

}
