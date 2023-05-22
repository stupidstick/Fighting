package config;

public class ActorConfig {
    private static double width;
    private static double height;
    private static double speed;
    private static double hp;
    static{
        hp = 100;
        width = 50;
        height = 100;
        speed = 5;
    }

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        ActorConfig.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        ActorConfig.height = height;
    }

    public static double getSpeed() {
        return speed;
    }

    public static void setSpeed(double speed) {
        ActorConfig.speed = speed;
    }

    public static double getHp() {
        return hp;
    }

    public static void setHp(double hp) {
        ActorConfig.hp = hp;
    }
}
