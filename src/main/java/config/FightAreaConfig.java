package config;

import controller.FightContoller;
import javafx.fxml.FXMLLoader;
import main.Main;

public class FightAreaConfig {
    private static double width;
    private static double height;

    public static double getWidth() {
        return width;
    }

    public static void setWidth(double width) {
        FightAreaConfig.width = width;
    }

    public static double getHeight() {
        return height;
    }

    public static void setHeight(double height) {
        FightAreaConfig.height = height;
    }
}
