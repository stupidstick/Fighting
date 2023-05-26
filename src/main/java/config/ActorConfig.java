package config;

public class ActorConfig {
    private static double width;
    private static double height;
    private static double speed;
    private static double hp;
    private static double blinkDistance;
    private static double attackDistance;
    private static double damage;
    private static int attackDelay;
    static{
        hp = 100;
        width = 75;
        height = 150;
        speed = 5;
        blinkDistance = 200;
        attackDistance = 50;
        damage = 20;
        attackDelay = 500;
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

    public static double getBlinkDistance() {
        return blinkDistance;
    }

    public static void setBlinkDistance(double blinkDistance) {
        ActorConfig.blinkDistance = blinkDistance;
    }

    public static double getAttackDistance() {
        return attackDistance;
    }

    public static void setAttackDistance(double attackDistance) {
        ActorConfig.attackDistance = attackDistance;
    }


    public static double getDamage() {
        return damage;
    }

    public static void setDamage(double damage) {
        ActorConfig.damage = damage;
    }

    public static int getAttackDelay() {
        return attackDelay;
    }

    public static void setAttackDelay(int attackDelay) {
        ActorConfig.attackDelay = attackDelay;
    }
}
