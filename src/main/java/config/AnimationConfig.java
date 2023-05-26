package config;

import animations.AnimationShift;

public class AnimationConfig {
    private static AnimationShift idleShift;
    private static AnimationShift moveShift;
    private static AnimationShift attackShift;
    private static AnimationShift deathShift;

    static {
        idleShift = new AnimationShift(-30, 0, 0);
        moveShift = new AnimationShift(-70, 0, 0);
        attackShift = new AnimationShift(-32, -190, -50);
        deathShift = new AnimationShift(0, 0, 0);
    }


    public static AnimationShift getIdleShift() {
        return idleShift;
    }

    public static void setIdleShift(AnimationShift idleShift) {
        AnimationConfig.idleShift = idleShift;
    }

    public static AnimationShift getMoveShift() {
        return moveShift;
    }

    public static void setMoveShift(AnimationShift moveShift) {
        AnimationConfig.moveShift = moveShift;
    }

    public static AnimationShift getAttackShift() {
        return attackShift;
    }

    public static void setAttackShift(AnimationShift attackShift) {
        AnimationConfig.attackShift = attackShift;
    }

    public static AnimationShift getDeathShift() {
        return deathShift;
    }

    public static void setDeathShift(AnimationShift deathShift) {
        AnimationConfig.deathShift = deathShift;
    }
}
