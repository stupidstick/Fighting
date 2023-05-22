package dto;

import java.io.Serializable;

public class RegDTO implements Serializable {
    private UserDTO user;
    public RegDTO(UserDTO user){
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
