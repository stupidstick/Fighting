package dto;

import java.io.Serializable;

public class StartFightDTO implements Serializable {
    private Boolean isHost;

    private String userName;
    private String oppName;

    public StartFightDTO(Boolean isHost, String userName, String oppName) {
        this.isHost = isHost;
        this.userName = userName;
        this.oppName = oppName;
    }

    public Boolean isHost() {
        return isHost;
    }

    public void setHost(Boolean host) {
        isHost = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOppName() {
        return oppName;
    }

    public void setOppName(String oppName) {
        this.oppName = oppName;
    }
}
