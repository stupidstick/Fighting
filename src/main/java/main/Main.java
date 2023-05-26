package main;

import client.Client;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Client client;
    private static Object waiter;
    private static Stage currentStage;
    private static SimpleObjectProperty dispatcher;
    @Override
    public void start(Stage stage) throws IOException {
        client = new Client();
        waiter = new Object();
        dispatcher = new SimpleObjectProperty(new Object());
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
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        currentStage = stage;
    }

    public static void createAuthStage() throws  IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Fighting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        currentStage = stage;
    }

        public static void createMainStage() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        currentStage = stage;
    }

    public static void createFightingStage() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Fight.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setTitle("Fighting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        currentStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public static Object getWaiter() {
        return waiter;
    }

    public static void setWaiter(Object waiter) {
        Main.waiter = waiter;
    }

    public static Object getDispatcher() {
        return dispatcher.get();
    }

    public static SimpleObjectProperty dispatcherProperty() {
        return dispatcher;
    }

    public static void setDispatcher(Object dispatcher) {
        Main.dispatcher.set(dispatcher);
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        Main.currentStage = currentStage;
    }
}