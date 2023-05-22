package dto;

import java.io.Serializable;
import java.util.List;

public class LobbiesListDTO implements Serializable {
    private List<String> lobbies;
    public LobbiesListDTO(List<String> lobbies){
        this.lobbies = lobbies;
    }

    public List<String> getLobbies() {
        return lobbies;
    }

    public void setLobbies(List<String> lobbies) {
        this.lobbies = lobbies;
    }
}
