package controller;

import View.Menubar;
import model.MenubarModel;

public class MenubarController {

	private MenubarModel model;
	private Menubar menu;
	
	public MenubarController(MenubarModel model, Menubar menu) {
		this.model = model;
		this.menu = menu;
		
	

		menu.getExit().setOnAction(e -> model.Exit());
		menu.getLogout().setOnAction(e -> model.logout());
	}
	
	
}
