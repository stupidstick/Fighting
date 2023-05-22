package dto;

import java.io.Serializable;

public class AuthResponseDTO implements Serializable {
    private boolean value;
    public AuthResponseDTO(boolean value){
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
