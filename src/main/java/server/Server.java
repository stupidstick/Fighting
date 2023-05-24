package server;

import database.Database;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<MonoThreadClientHandler> clients;
    public static ObservableList<MonoThreadClientHandler> lobbies;

    static {
        clients = new ArrayList<>();
        lobbies = FXCollections.observableArrayList();
    }

    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(3345);
            addLobbiesListener();
            Database.connection("192.168.0.11", "5432", "postgres", "admin");
            Database.createTable();
            while (true){
                Socket client = server.accept();
                System.out.println("client accept");
                try{
                    MonoThreadClientHandler clientThread = new MonoThreadClientHandler(client);
                    clients.add(clientThread);
                    clientThread.start();
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private static void addLobbiesListener(){
        lobbies.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                clients.forEach(MonoThreadClientHandler::sendLobbiesList);
            }
        });
    }

    public static List<MonoThreadClientHandler> getClients() {
        return clients;
    }

    public static List<MonoThreadClientHandler> getLobbies() {
        return lobbies;
    }
}
