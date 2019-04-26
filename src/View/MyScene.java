package View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MyScene extends Scene {
    private InlogPane launcher;
    private RootPane game;
    private VBox vbox;

    public MyScene() {
        super(new Pane(), 500, 500);
        launcher = new InlogPane(this);
        game = new RootPane();
       
        setRoot(game);
    }

}
