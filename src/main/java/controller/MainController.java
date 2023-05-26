package controller;

import client.Client;
import dto.CreateLobbyResponseDTO;
import dto.LobbiesListDTO;
import dto.StartFightDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MultipleSelectionModel;
import main.Main;
import view.MainView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends MainView implements Initializable {
    private ObservableList<String> lobbiesList;

    private void setLobbiesList(){
        lobbiesList = FXCollections.observableArrayList();
        lobbiesListView.setItems(lobbiesList);
    }

    @FXML
    private void join(ActionEvent event){
        try {
            MultipleSelectionModel selectionModel = lobbiesListView.getSelectionModel();
            Main.getClient().joinLobby(selectionModel.getSelectedItem().toString());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    private void createLobby(ActionEvent event){
        try {
            Main.getClient().createLobby();
            synchronized (Main.getWaiter()){
                Main.getWaiter().wait();
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void getLobbiesList(){
        try {
            Main.getClient().getLobbiesList();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void addClientDispatcherListener(){
        Main.dispatcherProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try{
                    if (Main.getDispatcher() instanceof CreateLobbyResponseDTO){
                        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Wait.fxml"));
                        Scene scene = new Scene(loader.load(), 800, 600);
                        Main.getCurrentStage().setScene(scene);
                    }

                    if (Main.getDispatcher() instanceof LobbiesListDTO){
                        lobbiesList.setAll(((LobbiesListDTO) Main.getDispatcher()).getLobbies());
                    }

                    if (Main.getDispatcher() instanceof StartFightDTO){
                        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Fight.fxml"));
                        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                        ((FightContoller) (fxmlLoader.getController())).setNames((StartFightDTO) Main.getDispatcher());
                        Main.getCurrentStage().setScene(scene);
                    }

                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addClientDispatcherListener();
        setLobbiesList();
        getLobbiesList();
    }
}
