package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.Main;
import view.MainView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends MainView implements Initializable {
    private ObservableList<String> lobbiesList;

    private void setLobbiesList(){
        lobbiesList = FXCollections.observableArrayList();
        lobbiesListView.setItems(lobbiesList);
        Main.getClient().setLobbiesList(lobbiesList);
    }

    @FXML
    private void join(ActionEvent event){
        try{
            System.out.println("join");
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Fight.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(scene);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    private void createLobby(ActionEvent event){
        try{
            if (Main.getClient().createLobby()){
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("Wait.fxml"));
                Scene scene = new Scene(loader.load(), 800, 600);
                ((Stage) (((Node) event.getSource()).getScene().getWindow())).setScene(scene);
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLobbiesList();
    }
}
