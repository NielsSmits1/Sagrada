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
	private GameController game;
	private ChallengesController challenges;
	
	private Runnable multi;

	public InlogController(MyScene myScene) {
		
		scene = myScene;
		
		
	}

	public InlogPane showInlog() {
		inlog = new InlogPane();
		inlog.getLoginButton().setOnAction(e -> login());
		inlog.getLoginButton().setOnAction(e -> register());
		return inlog;
	}
	
	public InlogPane show() {
        inlog = new InlogPane();
		scene.setRoot(inlog);
		return inlog;
	}
	

	public EventHandler<ActionEvent> register() {
		player = new Player(inlog.getUsernameText());
		if(inlog.getUsernameText().equals("") || inlog.getPasswordText().equals("") || !player.checkUsernameExists()) {
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
		
		home = new HomeController(scene, player);
		challenges = new ChallengesController(home);
		//game = new GameController(scene);
		menu = new MenubarController(scene, this, controller);			
//		scene.setRoot(new VBox(menu.getMenubar(),game.showOptions()));
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

	public PlayerController getController() {
		return controller;
	}

	public GameController getGame() {
		return game;
	}

	public ChallengesController getChallenges() {
		return challenges;
	}
	
	
	
	
	
		
}
