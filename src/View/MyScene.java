package View;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
    private InlogPane launcher;
    private RootPane game;
    //private RootPane game;

    public MyScene() {
        super(new Pane(), 500, 500);
        launcher = new InlogPane(this);
        game = new RootPane();
        
        setRoot(game);

    }

}
