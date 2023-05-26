package animations;

import config.AnimationConfig;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import paths.Path;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimationsData {

    private static Animation getIdleAnimation(String path){
        Animation animation;
        try{
            animation = new Animation("idle", new Image(new FileInputStream(path + "idle.gif")), AnimationConfig.getIdleShift());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            animation = null;
        }
        return animation;
    }

    private static Animation getAttackAnimation(String path){
        Animation animation;
        try{
            animation = new Animation("attack", new Image(new FileInputStream(path + "attack.gif")), AnimationConfig.getAttackShift());
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            animation = null;
        }
        return animation;
    }

    private static Map<String, Animation> getAnimations(String path){
        Map<String, Animation> animations = new HashMap<>();

        Animation animation = getIdleAnimation(path);
        animations.put(animation.getName(), animation);

        animation = getAttackAnimation(path);
        animations.put(animation.getName(), animation);

        return animations;
    }

    public static Map<String, Animation> getLeftActorAnimations(){
        return getAnimations(Path.PATH_TO_LEFT_ACTOR_RESOURCES);
    }

    public static Map<String, Animation> getRightActorAnimations(){
        return getAnimations(Path.PATH_TO_RIGHT_ACTOR_RESOURCES);
    }



}
