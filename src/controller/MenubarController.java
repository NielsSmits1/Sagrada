package controller;

import View.Menubar;
import model.MenuBarModel;

public class MenubarController {
	
	private Menubar menu;
	private MenuBarModel menuModel;
	
	private PlayerController self;

	
	public MenubarController(PlayerController self) {
		this.self = self;
		menu = new Menubar();
		menuModel = new MenuBarModel();
		creatMenuBar();
		menu.getExit().setOnAction(e -> menuModel.Exit());
		menu.getStats().setOnAction(e -> menuModel.showStats(self));
		menu.getLogout().setOnAction(e -> menuModel.logOut());
		
	}
	
	public void creatMenuBar() {
		menu.creatMenu();
		
	}
	
	
}
