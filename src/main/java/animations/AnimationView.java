package animations;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import objects.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimationView extends ImageView {
    private Actor actor;
    private Map <String, Animation> animations;
    private Animation currentAnimation;
    public AnimationView(Actor actor, Map<String, Animation> animations){
        this.actor = actor;
        this.animations = animations;
        setImage(animations.get("idle").getAnimation());
        currentAnimation = animations.get("idle");
        updatePosition();
        setPositionListener();
        setActionListener();
    }
    public void setStartCord(){
        setLayoutX(actor.getLayoutX());
        setLayoutY(actor.getLayoutY());
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
                /*if (actor.getCurrentAction().equals("moveLeft") || actor.getCurrentAction().equals("moveRight")){
                    setImage(animations.get("move").getAnimation());
                }*/
                if (actor.getCurrentAction().equals("attack")){
                    currentAnimation = animations.get("attack");
                    setImage(currentAnimation.getAnimation());
                }

                updatePosition();
            }
        });
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