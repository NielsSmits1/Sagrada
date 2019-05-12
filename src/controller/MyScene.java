package controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
    private InlogController inlog = new InlogController(this);
    private GameController game = new GameController(this);
    public MyScene() {
        super(new Pane());
//        setRoot(game.showOptions());

         inlog.showInlog();
        setRoot(inlog.show());

    }

}
