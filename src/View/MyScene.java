package View;

import controller.InlogController;
import controller.MenubarController;
import controller.PlayerController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.InlogModel;
import model.MenubarModel;
import model.MySceneModel;

public class MyScene extends Scene {

	private RootPane game;
	private Home home;
	private PlayerController player;
	private MenubarController menuController;
	private MenubarModel menuModel;
	private Menubar menu;

	private InlogController inlogcon;
	private InlogModel inlogmodel;
	private InlogPane inlogPane;
	
	private MySceneModel sceneModel;

	public MyScene() {
		super(new Pane(), 500, 500);
		
		inlogPane = new InlogPane(this);
		inlogmodel = new InlogModel(inlogPane);
		inlogcon = new InlogController(inlogmodel, inlogPane);
		
		
		sceneModel = new MySceneModel(this);
		

		game = new RootPane();
		player = new PlayerController(getUserAgentStylesheet());
		home = new Home(player, this);
		
		menu = new Menubar();
		menuModel = new MenubarModel(menu);
		menuController = new MenubarController(menuModel, menu);

		setRoot(new VBox(sceneModel.createscene(inlogPane)));
	}

}
