package objects;

import config.ActorConfig;
import config.FightAreaConfig;
import config.KeyConfig;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;

public class Actor extends Pane{
    private String name;
    private double hp;
    private String currentAction;
    Thread actionThread;
    private double speed;
        public Actor(double cordX, String name) {
            this.name = name;
            hp = ActorConfig.getHp();
            currentAction = "idle";
            speed = ActorConfig.getSpeed();
            setPrefSize(ActorConfig.getWidth(), ActorConfig.getHeight());
            setCord(cordX);
            setStyle("-fx-background-color: red");
            actionThread = new Thread(actionDispatcher);
            actionThread.start();
        }
        public void setCord(double x){
            setLayoutX(x);
            setLayoutY(FightAreaConfig.getHeight() - this.getPrefHeight());
        }

    Runnable actionDispatcher = new Runnable() {
        @Override
        public void run() {
            SimpleLongProperty lastUpdate = new SimpleLongProperty(0);
            SimpleLongProperty interval = new SimpleLongProperty(20);
            while (true){

                long now = new Date().getTime();
                if (now - lastUpdate.get() > interval.get()){
                    synchronized (currentAction){
                        if (currentAction.equals("moveRight")){
                            setLayoutX(getLayoutX() + speed);
                            if (getLayoutX() + getPrefWidth() > FightAreaConfig.getWidth()){
                                setLayoutX(FightAreaConfig.getWidth() - getPrefWidth());
                            }
                        }

                        if (currentAction.equals("moveLeft")){
                            setLayoutX(getLayoutX() - speed);
                            if (getLayoutX() < 0){
                                setLayoutX(0);
                            }
                        }
                    }
                    lastUpdate.set(now);
                }
            }
        }
    };




    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
