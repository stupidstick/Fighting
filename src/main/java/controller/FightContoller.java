package controller;

import config.FightAreaConfig;
import dto.StartFightDTO;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import objects.Actor;
import objects.MainActor;
import objects.OppActor;
import view.FightView;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FightContoller extends FightView implements Initializable {
    private Map<String, Actor> actors;
    private MainActor mainActor;
    private OppActor oppActor;
    public void setNames(StartFightDTO data) {
        if (data.isHost()) {
            mainActor = new MainActor(100, data.getUserName());
            oppActor = new OppActor(FightAreaConfig.getWidth() - 100, data.getOppName());
            actors.put("left", mainActor);
            actors.put("right", oppActor);
        } else {
            mainActor = new MainActor(FightAreaConfig.getWidth() - 100, data.getUserName());
            oppActor = new OppActor(100, data.getOppName());
            actors.put("left", oppActor);
            actors.put("right", mainActor);
        }

        fightArea.getChildren().setAll(mainActor, oppActor);


        mainActor.setStyle("-fx-background-color: blue");
        mainPane.addEventFilter(KeyEvent.KEY_PRESSED, mainActor.getPressEvent());
        mainPane.addEventFilter(KeyEvent.KEY_RELEASED, mainActor.getReleaseEvent());
        firstActorLabel.setText(actors.get("left").getName());
        secondActorLabel.setText(actors.get("right").getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize();
        actors = new HashMap<>();
    }
}