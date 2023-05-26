package animations;

import javafx.scene.image.Image;

public class Animation {
    private String name;
    private Image animation;
    private AnimationShift shift;

    public Animation(String name, Image animation, AnimationShift shift){
        this.name = name;
        this.animation = animation;
        this.shift = shift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getAnimation() {
        return animation;
    }

    public void setAnimation(Image animation) {
        this.animation = animation;
    }

    public AnimationShift getShift() {
        return shift;
    }

    public void setShift(AnimationShift shift) {
        this.shift = shift;
    }
}
