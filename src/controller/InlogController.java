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

	public Parent showInlog() {
		inlog = new InlogPane(this.login(), this.register());
		return inlog;
	}
	

	private EventHandler<ActionEvent> register() {
		return null;
		
	}

	private EventHandler<ActionEvent> login() {
		if(inlog.getUsernameText().equals("") || inlog.getPasswordText().equals("")) {
			inlog.giveErrorBox();
		}else {
			player = new Player(inlog.getUsernameText(), inlog.getPasswordText());
			if(player.getSelect().isEmpty()) {
				inlog.giveErrorBox();
			}else {
				home = new HomeController(scene, player);
				scene.setRoot(home.showHome());
			}
		}
		return null;
	}
	
	
}
