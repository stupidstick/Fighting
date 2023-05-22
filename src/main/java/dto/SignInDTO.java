package dto;

import java.io.Serializable;

public class SignInDTO implements Serializable {
    UserDTO user;
    public SignInDTO(UserDTO user){
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
