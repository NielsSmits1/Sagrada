package controller;


import javax.imageio.metadata.IIOMetadataController;

import View.InlogPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MyScene extends Scene {
    private InlogController inlog = new InlogController(this);

    public MyScene() {
        super(new Pane());
        setRoot(inlog.showInlog());
    }

}
