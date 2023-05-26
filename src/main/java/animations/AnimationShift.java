package animations;

public class AnimationShift {
    private double leftShift;
    private double rightShift;
    private double topShift;
    public AnimationShift(double leftShift, double rightShift, double topShift){
        this.leftShift = leftShift;
        this.rightShift = rightShift;
        this.topShift = topShift;
    }

    public double getLeftShift() {
        return leftShift;
    }

    public void setLeftShift(double leftShift) {
        this.leftShift = leftShift;
    }

    public double getRightShift() {
        return rightShift;
    }

    public void setRightShift(double rightShift) {
        this.rightShift = rightShift;
    }

    public double getTopShift() {
        return topShift;
    }

    public void setTopShift(double topShift) {
        this.topShift = topShift;
    }
}
