package dto;

import java.io.Serializable;

public class CreateLobbyResponse implements Serializable {
    private Boolean value;
    public CreateLobbyResponse(Boolean value){
    this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
