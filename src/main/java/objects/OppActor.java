package objects;

import dto.ActionDTO;
import dto.PositionDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import main.Main;

public class OppActor extends Actor{
    private ChangeListener clientDispatcherListener;

    public OppActor(double cordX, String name) {
        super(cordX, name);
        addClientDispatcherListener();
    }

    private void addClientDispatcherListener(){
        clientDispatcherListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (Main.getDispatcher() instanceof ActionDTO){
                    setCurrentAction(((ActionDTO) Main.getDispatcher()).getAction());
                }
                if (Main.getDispatcher() instanceof PositionDTO){
                    setLayoutX(((PositionDTO) Main.getDispatcher()).getCordX());
                }
            }
        };
        Main.dispatcherProperty().addListener(clientDispatcherListener);
    }

    public ChangeListener getClientDispatcherListener() {
        return clientDispatcherListener;
    }

    public void setClientDispatcherListener(ChangeListener clientDispatcherListener) {
        this.clientDispatcherListener = clientDispatcherListener;
    }
}
