package controller;

import animations.AnimationView;
import animations.AnimationsData;
import config.FightAreaConfig;
import dto.HpInfoDTO;
import dto.StartFightDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import main.Main;
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
    private AnimationView leftAnimation;
    private AnimationView rightAnimation;
    public void setNames(StartFightDTO data) {
        if (data.isHost()) {
            oppActor = new OppActor(FightAreaConfig.getWidth() - 100, data.getOppName());
            mainActor = new MainActor(100, data.getUserName());
            actors.put("left", mainActor);
            actors.put("right", oppActor);
        } else {
            oppActor = new OppActor(100, data.getOppName());
            mainActor = new MainActor(FightAreaConfig.getWidth() - 100, data.getUserName());
            actors.put("left", oppActor);
            actors.put("right", mainActor);
        }

        mainActor.setOpp(oppActor);
        oppActor.setOpp(mainActor);

        leftAnimation = new AnimationView(actors.get("left"), AnimationsData.getLeftActorAnimations());
        rightAnimation = new AnimationView(actors.get("right"), AnimationsData.getRightActorAnimations());

        addHPListeners();

        fightArea.getChildren().setAll(mainActor, oppActor, leftAnimation, rightAnimation);


        mainActor.setStyle("-fx-background-color: blue");
        mainPane.addEventFilter(KeyEvent.KEY_PRESSED, mainActor.getPressEvent());
        mainPane.addEventFilter(KeyEvent.KEY_RELEASED, mainActor.getReleasedEvent());
        leftActorLabel.setText(actors.get("left").getName());
        rightActorLabel.setText(actors.get("right").getName());
    }


    private void addHPListeners(){
        actors.get("left").hpProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Main.getClient().sendHP(actors.get("left").getHp(), actors.get("right").getHp());
                leftHP.setText(String.valueOf(actors.get("left").getHp()));
                if (actors.get("left").getHp() <= 0){
                    try{
                        playDeath(actors.get("left"));
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                }
            }
        });
        actors.get("right").hpProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Main.getClient().sendHP(actors.get("left").getHp(), actors.get("right").getHp());
                rightHP.setText(String.valueOf(actors.get("right").getHp()));
                if (actors.get("right").getHp() <= 0){
                    try{
                        playDeath(actors.get("right"));
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                }
            }
        });
    }

    private void playDeath(Actor actor){
        synchronized (actor.getCurrentAction()){
            actor.setCurrentAction("death");

            mainPane.removeEventFilter(KeyEvent.KEY_PRESSED, mainActor.getPressEvent());
            mainPane.removeEventFilter(KeyEvent.KEY_RELEASED, mainActor.getPressEvent());
        }
    }

    private void addClientDispatcherListener(){
        Main.dispatcherProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                try {
                    if (Main.getDispatcher() instanceof HpInfoDTO){
                        HpInfoDTO hpInfo = (HpInfoDTO) Main.getDispatcher();
                        actors.get("left").setHp(hpInfo.getLeftHP());
                        actors.get("right").setHp(hpInfo.getRightHP());
                    }
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize();
        actors = new HashMap<>();
        addClientDispatcherListener();
    }
}