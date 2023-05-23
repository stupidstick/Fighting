package dto;

import java.io.Serializable;

public class SignUpDTO implements Serializable {
    UserDTO user;
    public SignUpDTO(UserDTO user){
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
