package View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MyScene extends Scene {
	private InlogPane launcher;
	private RootPane game;
	private PatterncardSelect pattern;
	private Menubar menu;
	private MyScene scene;

	public MyScene() {
		super(new Pane());
		launcher = new InlogPane(this);
		pattern = new PatterncardSelect(this);
		setRoot(launcher);

	}

	
	
}
