package client;

import dto.*;
import javafx.application.Platform;
import main.Main;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client(){
    }

    public void connect(String host, int port) throws IOException {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            start();
    }

    public void signIn(String login, String password) throws IOException{
        SignInDTO request = new SignInDTO(new UserDTO(login, password));
        out.writeObject(request);
    }

    private void dispatchCommand(Object response){
        synchronized (Main.getWaiter()){
            Main.getWaiter().notify();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Main.dispatcherProperty().set(response);
            }
        });
    }


    public void signUp(String login, String password) throws IOException, ClassNotFoundException{
        SignUpDTO request = new SignUpDTO(new UserDTO(login, password));
        out.writeObject(request);
    }

    public void createLobby() throws IOException{
        CreateLobbyDTO request = new CreateLobbyDTO();
        out.writeObject(request);
    }

    public void getLobbiesList() throws IOException{
        GetLobbiesListDTO request = new GetLobbiesListDTO();
        out.writeObject(request);
    }

    public void joinLobby(String lobby) throws IOException{
        JoinLobbyDTO request = new JoinLobbyDTO(lobby);
        out.writeObject(request);
    }

    public void sendAction(String action){
        try {
            out.writeObject(new ActionDTO(action));
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void sendPosition(double cordX){
        try {
            out.writeObject(new PositionDTO(cordX));
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void run() {
        while (!socket.isClosed()){
            try{
                Object message = in.readObject();
                System.out.println(message);
                if (message instanceof SignInResponseDTO){
                    dispatchCommand(message);
                }
                if (message instanceof SignUpResponseDTO){
                    dispatchCommand(message);
                }
                if (message instanceof CreateLobbyResponseDTO){
                    dispatchCommand(message);
                }
                if (message instanceof LobbiesListDTO){
                    dispatchCommand(message);
                }
                if (message instanceof StartFightDTO){
                    dispatchCommand(message);
                }
                if (message instanceof ActionDTO){
                    dispatchCommand(message);
                }
                if (message instanceof PositionDTO){
                    dispatchCommand(message);
                }
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
}
