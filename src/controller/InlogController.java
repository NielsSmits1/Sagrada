package controller;

import View.InlogPane;
import View.MyScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.Player;

public class InlogController{
	private InlogPane inlog;
	private HomeController home;
	private Player player;
	private MyScene scene;
	private MenubarController menu;
	
	private Runnable multi;

	public InlogController(MyScene myScene) {
		scene = myScene;
		scene.setOnKeyPressed(e -> keyPress(e));
	}

	private void keyPress(KeyEvent e) {
		if(e.getCode() == KeyCode.ENTER) {
			login();
		}
	}

	public InlogPane showInlog() {
		inlog = new InlogPane();
		inlog.getLoginButton().setOnAction(e -> login());
		inlog.getRegisterButton().setOnAction(e -> register());
		return inlog;
	}

	public EventHandler<ActionEvent> register() {
		player = new Player(inlog.getUsernameText(), inlog.getPasswordText());
		if(inlog.getUsernameText().equals("") || inlog.getPasswordText().equals("") || player.checkUsernameExists()) {
			inlog.giveErrorBox();
		}else {
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
				buildHome();
			}else {
				inlog.giveErrorBox();
			}
		}
		return null;
	}
	public void buildHome() {
		menu = new MenubarController(scene, this, player);
		home = new HomeController(player, menu);
		scene.setRoot(new VBox(menu.getMenubar(),home.showHome()));
		
	}
	
	public InlogPane getInlog() {
		return inlog;
	}

	public HomeController getHome() {
		return home;
	}

	public Player getPlayer() {
		return player;
	}

	public MyScene getScene() {
		return scene;
	}

	public MenubarController getMenu() {
		return menu;
	}
	
}
