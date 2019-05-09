package controller;

import View.InlogPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import model.Player;

public class InlogController{
	private InlogPane inlog;
	private HomeController home;
	private Player player;
	private MyScene scene;

	public InlogController(MyScene myScene) {
		scene = myScene;
	}

	public InlogPane showInlog() {
		inlog = new InlogPane(this.login(), this.register());
		return inlog;
	}
	
	public void show() {
		inlog = new InlogPane();
		scene.setRoot(inlog);
	}
	

	private EventHandler<ActionEvent> register() {
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

	private EventHandler<ActionEvent> login() {
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
	private void buildHome() {
		//build and show
		home = new HomeController(scene, player);
		scene.setRoot(home.showHome());
	}
	
	
}
