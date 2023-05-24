package dto;

import java.io.Serializable;

public class ActionDTO implements Serializable {
    private String action;

    public ActionDTO(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
