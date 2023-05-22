package controller;

import config.FightAreaConfig;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import objects.Actor;
import view.FightView;

import java.net.URL;
import java.util.ResourceBundle;

public class FightContoller extends FightView implements Initializable {

    private Actor actor_1;
    private Actor actor_2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize();
        actor_1 = new Actor(100);
        mainPane.addEventFilter(KeyEvent.KEY_PRESSED, actor_1.getPressEvent());
        mainPane.addEventFilter(KeyEvent.KEY_RELEASED, actor_1.getReleasedEvent());
        actor_2 = new Actor(FightAreaConfig.getWidth() - 100);
        fightArea.getChildren().setAll(actor_1, actor_2);

    }
}