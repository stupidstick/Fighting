package dto;

import java.io.Serializable;

public class CreateLobbyResponseDTO implements Serializable {
    private Boolean value;
    public CreateLobbyResponseDTO(Boolean value){
    this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
