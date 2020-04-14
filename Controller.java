package com.shubhankar.JavaFxapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label welcomeLabel;
    @FXML
    public ChoiceBox<String> choiceBoxx;
    @FXML
    public TextField userTextField;
    @FXML
    public Button convertButton;

    private static final String C_TO_F_TEXT = "Celsius to Fahrenheit";
    private static final String F_TO_C_TEXT = "Fahrenheit to Celsius";

    private boolean isC_TO_F = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choiceBoxx.getItems().add(C_TO_F_TEXT);
        choiceBoxx.getItems().add(F_TO_C_TEXT);
        choiceBoxx.setValue(C_TO_F_TEXT);

        choiceBoxx.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals(C_TO_F_TEXT)){
                    isC_TO_F = true;
                } else{
                    isC_TO_F = false;
                }
            }
        });

        convertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                covert();
            }
        });
    }

    private void covert() {
        String input = userTextField.getText();
        float enterTemperature = 0.0f;
        try {
            enterTemperature = Float.parseFloat(input);
        } catch(Exception exception) {
            warnUser();
            return;
        }
        float newTemperature = 0.0f;
        if(isC_TO_F){
            newTemperature = (enterTemperature*9/5)+32;
        } else {
            newTemperature = (enterTemperature-32)*5/9;
        }
        display(newTemperature);
    }

    private void warnUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please enter a valid temperature");
        alert.show();
    }

    private void display(float newTemperature) {
        String unit = isC_TO_F? "F" : "C";
        System.out.println("the new temperature is: "+newTemperature + unit);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("the new temperature is: "+newTemperature + unit);
        alert.show();
    }
}
