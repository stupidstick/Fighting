package dto;

import java.io.Serializable;

public class SignInResponseDTO implements Serializable {
    private boolean value;
    public SignInResponseDTO(boolean value){
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
