package view;

import config.FightAreaConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public abstract class FightView {
    @FXML
    protected Label leftActorLabel;
    @FXML
    protected Label rightActorLabel;
    @FXML
    protected Label leftHP;
    @FXML
    protected Label rightHP;
    @FXML
    public Pane fightArea;
    @FXML
    public AnchorPane mainPane;
    public void initialize(){
        FightAreaConfig.setWidth(fightArea.getPrefWidth());
        FightAreaConfig.setHeight(fightArea.getPrefHeight());
    }
}
