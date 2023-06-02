package controller;

import client.Client;
import dto.SignInResponseDTO;
import dto.SignUpResponseDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;

import java.net.URL;
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
            Main.getClient().signIn(loginField.getText(), passwordField.getText());
            synchronized (Main.getWaiter()){
                Main.getWaiter().wait();
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }


    @FXML
    private void signUp(){
        try{
            Main.getClient().signUp(loginField.getText(), passwordField.getText());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void addClientDispatcherListener(){
        Main.dispatcherProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (Main.getDispatcher() instanceof SignInResponseDTO){
                    SignInResponseDTO response = (SignInResponseDTO) Main.getDispatcher();
                    if (response.isValue()){
                        Main.getCurrentStage().hide();
                        try{
                            Main.createMainStage();
                        }
                        catch (Exception exception){
                            System.out.println(exception.getMessage());
                        }
                    }
                    else{
                        infoLabel.setText("Wrong login or password");
                    }
                }

                if (Main.getDispatcher() instanceof SignUpResponseDTO){
                    SignUpResponseDTO response = (SignUpResponseDTO) Main.getDispatcher();
                    if (response.isValue()){
                        infoLabel.setText("Registration complete");
                    }
                    else{
                        infoLabel.setText("OOPS");
                    }
                }
            }
        });
    }

    public void exit(){
        Main.getClient().close();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       addClientDispatcherListener();

    }
}
