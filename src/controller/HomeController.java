package controller;

import View.*;
import java.util.ArrayList;

import javafx.scene.Parent;
import model.Player;

public class HomeController {
	private HomePane home;
	private SearchPlayerController sp;
	private ChallengerController cpp;
	private ChallengesController cp;
	private MenubarController mb;
	private Player player;
	private Player self;

	public HomeController(MyScene scene, Player self) {
		this.self = self;
		sp = new SearchPlayerController();
		cpp = new ChallengerController();
		cp = new ChallengesController();
		mb = new MenubarController();
	}

	public String getUsername() {
		return self.getUsername();
	}

	public void buildPlayer(String u) {
		player = new Player(u);
	}
	
	public void buildPlayer(String u, String pw) {
		player = new Player(u, pw);
	}
	public boolean usernameExist(String u) {
		buildPlayer(u);
		if(player.checkUsername().isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isInGame(String username, PlayerController self) {
		String u;
		this.player = new Player(username);
		for(ArrayList<Object> a: self.getPlayer().checkPlayerInGame()) {
			u = (String) a.get(0);
			if(u.equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getSelf() {
		return this.self;
	}

	public Parent showHome() {
		home = new HomePane(sp.getSearchPlayerPane(), cpp.getChallengerPane(), cp.getChallengesPane(), mb.getMenubar());
		return home;
	}

}
