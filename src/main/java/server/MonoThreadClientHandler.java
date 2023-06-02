package server;

import database.Database;
import dto.*;

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
                    signIn(((SignInDTO) message).getUser());
                }


                if (message instanceof SignUpDTO){
                    signUp(((SignUpDTO) message).getUser());
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

                if (message instanceof HpInfoDTO){
                    oppClientHandler.sendHP((HpInfoDTO) message);
                }
                Server.getLobbies().forEach(obj -> System.out.println(obj.getUsername()));
                System.out.println("-----------");
            }
            Server.getClients().remove(this);
            Server.getLobbies().remove(this);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        }



    public void sendLobbiesList(){
        synchronized (Server.getLobbies()){
            List<String> lobbies = Server.getLobbies().stream().map(MonoThreadClientHandler::getUsername).toList();
            try{
                out.writeObject(new LobbiesListDTO(lobbies));
            }
            catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        }
    }

    private void signUp(UserDTO user){
        try{
            SignUpResponseDTO response = new SignUpResponseDTO(Database.addUser(user.getLogin(), user.getPassword()));
            out.writeObject(response);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private boolean checkUser(String username){
        for (MonoThreadClientHandler client: Server.getClients()){
            try {
                if (client.getUsername().equals(username)){
                    return true;
                }
            }
            catch (Exception ignored){}
        }
        return false;
    }

    private void signIn(UserDTO user){
        try{
            if (Database.checkUser(user.getLogin(), user.getPassword()) && !checkUser(user.getLogin())){
                out.writeObject(new SignInResponseDTO(true));
                username = user.getLogin();
            }
            else {
                out.writeObject(new SignInResponseDTO(false));
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    private void createLobby(){
        try {
            synchronized (Server.getLobbies()){
                if (!Server.getLobbies().contains(this)){
                    Server.getLobbies().add(this);
                    out.writeObject(new CreateLobbyResponseDTO(true));
                }
                else{
                    out.writeObject(new CreateLobbyResponseDTO(false));
                }
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
                synchronized (Server.getLobbies()){
                    Server.getLobbies().remove(oppClientHandler);
                }
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

    private void sendHP(HpInfoDTO hpInfoDTO){
        try{
            out.writeObject(hpInfoDTO);
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
