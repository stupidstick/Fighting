package server;

import database.Database;
import dto.*;
import main.Main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class MonoThreadClientHandler extends Thread{
    String username;
    Socket client;
    MonoThreadClientHandler oppClientHandler;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public MonoThreadClientHandler(Socket client){
        this.client = client;
        try {
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (!client.isClosed()) {

                Object message = in.readObject();

                if (message instanceof SignInDTO){
                    logInUser(((SignInDTO) message).getUser());
                }


                if (message instanceof SignUpDTO){
                    regUser(((SignUpDTO) message).getUser());
                }

                if (message instanceof CreateLobbyDTO){
                    createLobby();
                }

                if (message instanceof GetLobbiesListDTO){
                    sendLobbiesList();
                }

                if (message instanceof JoinLobbyDTO){
                    joinOpp(((JoinLobbyDTO) message).getName());
                }

                if (message instanceof ActionDTO){
                    oppClientHandler.sendAction((ActionDTO) message);
                }

                if (message instanceof PositionDTO){
                    oppClientHandler.sendPosition((PositionDTO) message);
                }
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        }



    public void sendLobbiesList(){
        List<String> lobbies = Server.getLobbies().stream().map(MonoThreadClientHandler::getUsername).toList();
        try{
            out.writeObject(new LobbiesListDTO(lobbies));
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void regUser(UserDTO user){
        try{
            SignUpResponseDTO response = new SignUpResponseDTO(Database.addUser(user.getLogin(), user.getPassword()));
            out.writeObject(response);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void logInUser(UserDTO user){
        try{
            SignInResponseDTO response = new SignInResponseDTO(Database.checkUser(user.getLogin(), user.getPassword()));
            if (response.isValue()){
                username = user.getLogin();
            }
            out.writeObject(response);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void createLobby(){
        try {
            if (!Server.getLobbies().contains(this)){
                Server.getLobbies().add(this);
                out.writeObject(new CreateLobbyResponseDTO(true));
            }
            else{
                out.writeObject(new CreateLobbyResponseDTO(false));
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void joinOpp(String username){
        for (MonoThreadClientHandler client: Server.getClients()){
            if (client.getUsername().equals(username)){
                oppClientHandler = client;
                oppClientHandler.setOppClientHandler(this);
                startFight(false);
                oppClientHandler.startFight(true);
                Server.getLobbies().remove(oppClientHandler);
                break;
            }
        }
    }

    private void startFight(Boolean isHost){
        try{
            StartFightDTO data = new StartFightDTO(isHost, username, oppClientHandler.getUsername());
            out.writeObject(data);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void sendAction(ActionDTO action){
        try{
            out.writeObject(action);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void sendPosition(PositionDTO position){
        try {
            out.writeObject(position);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MonoThreadClientHandler getOppClientHandler() {
        return oppClientHandler;
    }

    public void setOppClientHandler(MonoThreadClientHandler oppClientHandler) {
        this.oppClientHandler = oppClientHandler;
    }
}
