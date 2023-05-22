package main;

import client.Client;
import controller.FightContoller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Client client;
    private static Object object = new Object();
    @Override
    public void start(Stage stage) throws IOException {

        client = new Client();

        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Fight.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Fighting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();*/
        createIpChooserStage();
    }

    public static Client getClient(){
        return client;
    }

    public static void createIpChooserStage() throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("IpChooser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Fighting");
        try{
            object.wait();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void createAuthStage() throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Fighting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void createMainStage() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void createFightingStage() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Fight.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Fighting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}