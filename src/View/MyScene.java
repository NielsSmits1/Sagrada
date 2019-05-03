package View;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
    private InlogPane launcher;
    private RootPane game;
    private PatterncardSelect pattern;
    //private RootPane game;


    public MyScene() {
        super(new Pane());
        launcher = new InlogPane(this);
//      game = new RootPane();
        pattern = new PatterncardSelect(this);
        
        setRoot(pattern);

    }

    public void setRoot(BoardPane p) {
    	setRoot(p);
    }
}
