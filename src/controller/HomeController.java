package controller;

import model.Player;

public class HomeController {
	private Player player;

	public HomeController(PlayerController self) {
		player = self.getPlayer();
	}

}
