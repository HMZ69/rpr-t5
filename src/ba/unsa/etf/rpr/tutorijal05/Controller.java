package ba.unsa.etf.rpr.tutorijal05;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    private SimpleStringProperty operatorText;
    private SimpleStringProperty numberText;

    public SimpleStringProperty operatorTextProperty() {
        return operatorText;
    }
    public SimpleStringProperty numberTextProperty() {
        return numberText;
    }

    public String getOperatorText() {
        return operatorText.get();
    }
    public String getNumberText() {
        return numberText.get();
    }

    public Controller() {
        operatorText = new SimpleStringProperty("");
        numberText = new SimpleStringProperty("");
    }


    @FXML
    private Label result;
    private long n1 = 0;
    private long n2 = 0;
    private String operator = "";
    private boolean start = true;

    public Object calculate(long n1, long n2, String operator) {
        switch (operator) {
            case("+"):
                return n1 + n2;
            case("-"):
                return n1 - n2;
            case ("/"):
                if (n2 == 0)
                    return "inf";
                return (double)n1 / n2;
            case("%"):
                if (n2 == 0)
                    return "inf";
                return n1 % n2;
            case("X"):
                return n1 * n2;
            case("."):
                return (double) (n1 * 10 + n2) / 10;
            default:
                return 0;
        }
    }

    /*public double calculate2(double n1) {
        if (n1 != 0)
            return n1 * (-1);
        return 0;
    }*/

    @FXML
    public void processNumbers(ActionEvent event) {
        if (start) {
            result.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        result.setText(result.getText() + value);
    }

    @FXML
    public void processOperators(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        if (!value.equals("=")) {
            operator = value;
            n1 = Long.parseLong(result.getText());
            result.setText("");
        }
        else {
            n2 = Long.parseLong(result.getText());
            if (!operator.equals("+/-")) {
                Object output = calculate(n1, n2, operator);
                result.setText(output.toString());
                operator = "";
                start = true;
            }
            else {
                result.setText("Ovo ne znam namjestiti/mrsko mi razmisljati");
                operator = "";
                start = true;
            }
        }
    }
}
