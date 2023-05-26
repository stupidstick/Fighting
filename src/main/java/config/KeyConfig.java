package config;

import javafx.scene.input.KeyCode;

public class KeyConfig {
    private static KeyCode moveLeft;
    private static KeyCode moveRight;
    private static KeyCode blink;
    private static KeyCode attack;

    static{
        moveLeft = KeyCode.A;
        moveRight = KeyCode.D;
        blink = KeyCode.F;
        attack = KeyCode.SPACE;
    }

    public static KeyCode getMoveLeft() {
        return moveLeft;
    }

    public static void setMoveLeft(KeyCode moveLeft) {
        KeyConfig.moveLeft = moveLeft;
    }

    public static KeyCode getMoveRight() {
        return moveRight;
    }

    public static void setMoveRight(KeyCode moveRight) {
        KeyConfig.moveRight = moveRight;
    }

    public static KeyCode getBlink() {
        return blink;
    }

    public static void setBlink(KeyCode blink) {
        KeyConfig.blink = blink;
    }

    public static KeyCode getAttack() {
        return attack;
    }

    public static void setAttack(KeyCode attack) {
        KeyConfig.attack = attack;
    }
}
