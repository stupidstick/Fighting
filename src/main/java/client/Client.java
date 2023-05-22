package client;

import dto.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Client extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ObservableList<String> lobbiesList;

    public Client(){
    }

    public void connect(String host, int port) throws IOException {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
    }

    public boolean signIn(String login, String password) throws IOException, ClassNotFoundException{
        SignInDTO request = new SignInDTO(new UserDTO(login, password));
        out.writeObject(request);
        AuthResponseDTO response = (AuthResponseDTO) in.readObject();
        return response.isValue();
    }

    public boolean register(String login, String password) throws IOException, ClassNotFoundException{
        RegDTO request = new RegDTO(new UserDTO(login, password));
        out.writeObject(request);
        AuthResponseDTO response = (AuthResponseDTO) in.readObject();
        return response.isValue();
    }

    public boolean createLobby() throws IOException, ClassNotFoundException{
        CreateLobbyDTO request = new CreateLobbyDTO();
        out.writeObject(request);
        return ((CreateLobbyResponse) in.readObject()).getValue();
    }

    public void updateLobbiesList(List<String> list){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lobbiesList.setAll(list);
            }
        });
    }

    @Override
    public void run() {
        while (!socket.isClosed()){
            try{
                Object message = in.readObject();

            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    public void close(){
        try{

            out.close();
            in.close();
            socket.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public ObservableList<String> getLobbiesList() {
        return lobbiesList;
    }

    public void setLobbiesList(ObservableList<String> lobbiesList) {
        this.lobbiesList = lobbiesList;
    }
}
