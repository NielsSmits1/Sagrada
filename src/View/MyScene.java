package View;


import controller.InlogController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MyScene extends Scene {
	private InlogController inlog = new InlogController(this);

	public MyScene() {
		super(new Pane());
         inlog.showInlog();
        setRoot(inlog.showInlog());

	}

}
