package animations;

import config.ActorConfig;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import objects.Actor;

import java.util.*;

public class AnimationView extends ImageView {
    private Actor actor;
    private Map <String, Animation> animations;
    private Animation currentAnimation;
    public AnimationView(Actor actor, Map<String, Animation> animations){
        this.actor = actor;
        this.animations = animations;
        currentAnimation = animations.get(actor.getCurrentAction());
        setImage(currentAnimation.getAnimation());
        updatePosition();
        setPositionListener();
        setActionListener();
    }

    public void setPositionListener(){
        actor.layoutXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                updatePosition();
            }
        });

        actor.nodeOrientationProperty().addListener(new ChangeListener<NodeOrientation>() {
            @Override
            public void changed(ObservableValue<? extends NodeOrientation> observableValue, NodeOrientation nodeOrientation, NodeOrientation t1) {
                updatePosition();
            }
        });
    }

    public void setActionListener(){
        actor.currentActionProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (actor.getCurrentAction().equals("idle")){
                    currentAnimation = animations.get("idle");
                    setImage(currentAnimation.getAnimation());
                }
                if (actor.getCurrentAction().equals("moveLeft") || actor.getCurrentAction().equals("moveRight")){
                    currentAnimation = animations.get("move");
                    setImage(currentAnimation.getAnimation());
                }
                if (actor.getCurrentAction().equals("attack")){
                    currentAnimation = animations.get("attack");
                    setImage(currentAnimation.getAnimation());
                }
                if (actor.getCurrentAction().equals("death")){
                    currentAnimation = animations.get("death");
                    setImage(currentAnimation.getAnimation());
                    new Timer().schedule(getFinalDeath(), ActorConfig.getFinalDeathDelay());
                }
                updatePosition();
            }
        });
    }

    private TimerTask getFinalDeath(){
        return new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        currentAnimation = animations.get("finalDeath");
                        setImage(currentAnimation.getAnimation());
                        updatePosition();
                    }
                });
            }
        };
    }


    private void updatePosition(){
        setLayoutY(actor.getLayoutY() + currentAnimation.getShift().getTopShift());
        setNodeOrientation(actor.getNodeOrientation());
        if (getNodeOrientation() == NodeOrientation.LEFT_TO_RIGHT){
            setLayoutX(actor.getLayoutX() + currentAnimation.getShift().getLeftShift());
        }
        else if (getNodeOrientation() == NodeOrientation.RIGHT_TO_LEFT){
            setLayoutX(actor.getLayoutX() + currentAnimation.getShift().getRightShift());
        }
    }

}
