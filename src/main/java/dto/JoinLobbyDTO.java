package dto;

import java.io.Serializable;

public class JoinLobbyDTO implements Serializable {
    private String name;

    public JoinLobbyDTO(String username){
        this.name = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
