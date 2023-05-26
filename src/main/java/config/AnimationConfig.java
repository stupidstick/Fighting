package config;

import animations.AnimationShift;

public class AnimationConfig {
    private static AnimationShift idleShift;
    private static AnimationShift moveShift;
    private static AnimationShift attackShift;

    static {
        idleShift = new AnimationShift(-30, 0, 0);
        moveShift = new AnimationShift(0, 0, 0);
        attackShift = new AnimationShift(-32, -190, -50);
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
}
