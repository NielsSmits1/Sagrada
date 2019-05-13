package controller;

import View.InlogPane;
import View.MyScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import model.Player;

public class InlogController{
	private InlogPane inlog;
	private HomeController home;
	private Player player;
	private MyScene scene;
	private MenubarController menu;
	private PlayerController controller;

	public InlogController(MyScene myScene) {
		
		scene = myScene;
	}

	public InlogPane showInlog() {
		inlog = new InlogPane(scene);
		inlog.getLoginButton().setOnAction(e -> login());
		inlog.getLoginButton().setOnAction(e -> register());
		return inlog;
	}
	
	public InlogPane show() {
//		inlog = new InlogPane();
		scene.setRoot(inlog);
		return inlog;
	}
	

	public EventHandler<ActionEvent> register() {
		player = new Player(inlog.getUsernameText());
		if(inlog.getUsernameText().equals("") || inlog.getPasswordText().equals("") || player.checkUsernameExists()) {
			inlog.giveErrorBox();
		}else {
			player = new Player(inlog.getUsernameText(), inlog.getPasswordText());
			player.addUser();
			buildHome();
		}
		return null;
	}

	public EventHandler<ActionEvent> login() {
		if(inlog.getUsernameText().equals("") || inlog.getPasswordText().equals("")) {
			inlog.giveErrorBox();
		}else {
			player = new Player(inlog.getUsernameText(), inlog.getPasswordText());
			if(player.checkLogin()) {
				inlog.giveErrorBox();
			}else {
				buildHome();
			}
		}
		return null;
	}
	public void buildHome() {
		//build and show
		controller = new PlayerController(player.getUsername());
		menu = new MenubarController(scene, this, controller);
		home = new HomeController(scene, player);
		scene.setRoot(new VBox(menu.getMenubar(),home.showHome()));
	}

	public InlogPane getInlog() {
		return inlog;
	}
	
	
	
	
}
