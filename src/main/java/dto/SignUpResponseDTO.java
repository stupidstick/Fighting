package dto;

import java.io.Serializable;

public class SignUpResponseDTO implements Serializable {
    private boolean value;
    public SignUpResponseDTO(boolean value){
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
