package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitController implements Initializable {


    private void addClientDispatcherListener(){
        Main.dispatcherProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try{
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
