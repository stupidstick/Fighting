package objects;

import config.ActorConfig;
import config.KeyConfig;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.input.KeyEvent;
import main.Main;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
                synchronized (getCurrentAction()){
                    if (!getCurrentAction().equals("attack")){
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

                        if (keyEvent.getCode() == KeyConfig.getBlink() && (new Date().getTime() - getBlinkLastUse() > ActorConfig.getBlinkCD())){
                            synchronized (getCurrentAction()){
                                setCurrentAction("blink");
                                setBlinkLastUse(new Date().getTime());
                                Main.getClient().sendAction(getCurrentAction());
                            }
                        }
                        if (keyEvent.getCode() == KeyConfig.getAttack() ){
                            synchronized (getCurrentAction()){
                                if (!getCurrentAction().equals("attack")){
                                    setCurrentAction("attack");
                                    Main.getClient().sendAction(getCurrentAction());
                                    Timer timer = new Timer();
                                    timer.schedule(dispatchAttack(), ActorConfig.getAttackDelay());
                                }
                                //attackDispatcher();
                            }
                        }
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


    private TimerTask dispatchAttack(){
        return new TimerTask() {
            @Override
            public void run() {
                try{
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
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
                    });
                    Thread.sleep(ActorConfig.getAttackDelay());
                    synchronized (getCurrentAction()){
                        setCurrentAction("idle");
                        System.out.println("stop");
                        Main.getClient().sendAction(getCurrentAction());
                    }
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        };
    }

    private void attackDispatcher(){

    }
    
    
    public EventHandler getPressEvent(){
        return pressEvent;
    }

    public EventHandler getReleasedEvent(){
        return releasedEvent;
    }

}
