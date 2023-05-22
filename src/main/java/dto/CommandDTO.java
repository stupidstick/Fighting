package dto;

import java.io.Serializable;

public class CommandDTO implements Serializable {
    private String command;
    private Object object;
    public CommandDTO(String command){
        this.command = command;
    }

    public void setCommand(String command){
        this.command = command;
    }

    public String getCommand(){
        return command;
    }

    public void setObject(Object object){
        this.object = object;
    }

    public Object getObject(){
        return object;
    }
}
