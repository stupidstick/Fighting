package objects;

import config.ActorConfig;
import config.KeyConfig;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.input.KeyEvent;
import main.Main;

public class MainActor extends Actor{
    private EventHandler pressEvent;
    private EventHandler releasedEvent;
    
    public MainActor(double cordX, String name) {
        super(cordX, name);
        setPressEvent();
        setReleasedEvent();
    }

    private void setPressEvent(){
        pressEvent = new EventHandler() {
            @Override
            public void handle(Event event) {
                KeyEvent keyEvent = (KeyEvent) event;
                if (keyEvent.getCode() == KeyConfig.getMoveLeft()) {
                    synchronized (getCurrentAction()) {
                        setCurrentAction("moveLeft");
                        Main.getClient().sendAction(getCurrentAction());
                    }
                }

                if (keyEvent.getCode() == KeyConfig.getMoveRight()) {
                    synchronized (getCurrentAction()) {
                        setCurrentAction("moveRight");
                        Main.getClient().sendAction(getCurrentAction());
                    }
                }

                if (keyEvent.getCode() == KeyConfig.getBlink()){
                    synchronized (getCurrentAction()){
                        setCurrentAction("blink");
                        Main.getClient().sendAction(getCurrentAction());
                    }

                }

                if (keyEvent.getCode() == KeyConfig.getAttack()){
                    synchronized (getCurrentAction()){
                        setCurrentAction("attack");
                        Main.getClient().sendAction(getCurrentAction());
                        attackDispatcher();
                    }
                }

            }
        };
    }
    
    private void setReleasedEvent(){
        releasedEvent = new EventHandler() {
            @Override
            public void handle(Event event) {
                KeyEvent keyEvent = (KeyEvent) event;
                if ((getCurrentAction().equals("moveRight") && keyEvent.getCode() == KeyConfig.getMoveRight()) || (getCurrentAction().equals("moveLeft") && keyEvent.getCode() == KeyConfig.getMoveLeft())){
                    synchronized (getCurrentAction()) {
                        setCurrentAction("idle");
                        Main.getClient().sendAction(getCurrentAction());
                        Main.getClient().sendPosition(getLayoutX());
                    }
                }
            }
        };
    }

    private void attackDispatcher(){
        int orientation = (getNodeOrientation() == NodeOrientation.LEFT_TO_RIGHT) ? 1 : -1;
        setWidth(getWidth() + ActorConfig.getAttackDistance());

        if (orientation == -1){
            setLayoutX(getLayoutX() - ActorConfig.getAttackDistance());
        }

        if (getBoundsInParent().intersects(getOpp().getBoundsInParent())){
            getOpp().setHp(getOpp().getHp() - ActorConfig.getDamage());
        }

        if (orientation == -1){
            setLayoutX(getLayoutX() + ActorConfig.getAttackDistance());
        }
        setWidth(getWidth() - ActorConfig.getAttackDistance());
    }
    
    
    public EventHandler getPressEvent(){
        return pressEvent;
    }

    public EventHandler getReleasedEvent(){
        return releasedEvent;
    }

}
