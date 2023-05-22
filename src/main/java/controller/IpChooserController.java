package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class IpChooserController implements Initializable {
    @FXML
    TextField ipField;
    @FXML
    TextField portField;

    @FXML
    private void ok(ActionEvent event){
        try {
            String ip = ipField.getText();
            int port = Integer.parseInt(portField.getText());
            Main.getClient().connect(ip, port);
            ((Node) event.getSource()).getScene().getWindow().hide();
            Main.createAuthStage();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ipField.setText("192.168.0.11");
        portField.setText("3345");
    }
}
