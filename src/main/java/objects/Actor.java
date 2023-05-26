package objects;

import config.ActorConfig;
import config.FightAreaConfig;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import paths.Path;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Map;

public class Actor extends Pane{
    private Actor opp;
    private String name;
    private DoubleProperty hp;
    private StringProperty currentAction;
    Thread actionThread;
    private double speed;
    public Actor(double cordX, String name) {
        this.name = name;
        hp = new SimpleDoubleProperty(ActorConfig.getHp());
        currentAction = new SimpleStringProperty("idle");
        speed = ActorConfig.getSpeed();
        setPrefSize(ActorConfig.getWidth(), ActorConfig.getHeight());
        setMaxSize(ActorConfig.getWidth(), ActorConfig.getHeight());
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
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (currentAction.get().equals("moveRight")){
                                    setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                                    setLayoutX(getLayoutX() + speed);
                                    if (getLayoutX() + getPrefWidth() > FightAreaConfig.getWidth()){
                                        setLayoutX(FightAreaConfig.getWidth() - getPrefWidth());
                                    }
                                    if (getBoundsInParent().intersects(opp.getBoundsInParent())){
                                        setLayoutX(getLayoutX() - speed);
                                    }
                                }

                                if (currentAction.get().equals("moveLeft")){
                                    setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                                    setLayoutX(getLayoutX() - speed);
                                    if (getLayoutX() < 0){
                                        setLayoutX(0);
                                    }
                                    if (getBoundsInParent().intersects(opp.getBoundsInParent())){
                                        setLayoutX(getLayoutX() + speed);
                                    }
                                }

                                if (currentAction.get().equals("blink")){
                                    int orientation = (getNodeOrientation() == NodeOrientation.LEFT_TO_RIGHT) ? 1 : -1;
                                    setLayoutX(getLayoutX() + orientation * ActorConfig.getBlinkDistance());
                                    if (getLayoutX() < 0){
                                        setLayoutX(0);
                                    }
                                    else if (getLayoutX() + getPrefWidth() > FightAreaConfig.getWidth()){
                                        setLayoutX(FightAreaConfig.getWidth() - getPrefWidth());
                                    }
                                    if (getBoundsInParent().intersects(opp.getBoundsInParent())){
                                        if (orientation == 1){
                                            setLayoutX(opp.getLayoutX() - speed - getWidth());
                                        }
                                        else{
                                            setLayoutX(opp.getLayoutX() + speed + getWidth());
                                        }
                                    }
                                    setCurrentAction("idle");
                                }
                            }
                        });

                    }
                    lastUpdate.set(now);
                }
            }
        }
    };

    public double getHp() {
        return hp.get();
    }

    public DoubleProperty hpProperty() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp.set(hp);
    }

    public Actor getOpp() {
        return opp;
    }

    public void setOpp(Actor opp) {
        this.opp = opp;
    }

    public String getCurrentAction() {
        return currentAction.get();
    }

    public StringProperty currentActionProperty() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction.set(currentAction);
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
