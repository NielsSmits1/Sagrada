package controller;

import View.InlogPane;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
    public MyScene() {
        super(new Pane());
        InlogController inlog = new InlogController(this);
        inlog.show();
        //setRoot(inlog.showInlog());
    }

}
