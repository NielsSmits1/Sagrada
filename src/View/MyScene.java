package View;



import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
    private InlogPane launcher;
    private RootPane game;
    private PatterncardSelect pattern;



    public MyScene() {
        super(new Pane());
        launcher = new InlogPane(this);

        pattern = new PatterncardSelect(this);
        
        setRoot(launcher);

    }
}
