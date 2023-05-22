package controller;

import dto.CommandDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;
    @FXML
    Label infoLabel;
    @FXML
    private void signIn(ActionEvent event){
        try{
            if (Main.getClient().signIn(loginField.getText(), passwordField.getText())){
                infoLabel.setText("Login Successful");
                ((Node) event.getSource()).getScene().getWindow().hide();
                Main.getClient().start();
                Main.createMainStage();
            }
            else {
                infoLabel.setText("Wrong login or password");
            }

        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    private void reg(){
        try{
            if (Main.getClient().register(loginField.getText(), passwordField.getText())){
                infoLabel.setText("Registration complete");
            }
            else{
                infoLabel.setText("OOPS");
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
