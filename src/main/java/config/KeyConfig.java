package config;

import javafx.scene.input.KeyCode;

public class KeyConfig {
    private static KeyCode moveLeft;
    private static KeyCode moveRight;

    static{
        moveLeft = KeyCode.A;
        moveRight = KeyCode.D;
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
}
