package controller;

import View.Menubar;
import javafx.application.Platform;
import model.MenuBarModel;

public class MenubarController {

	private Menubar menu;
	private MenuBarModel model;
	
	
	
	
	public MenubarController() {
		CreateMenubarController();
	}
	
	public void CreateMenubarController() {
		menu = new Menubar();
		model = new MenuBarModel();
		
		menu.getExit().setOnAction(e -> Exit());
		menu.getLogout().setOnAction(e -> model.logout());
		menu.getStats().setOnAction(e -> model.showStats());
	}
	public void Exit() {
		Platform.exit();
	}

	public Menubar getMenu() {
		return menu;
	}
	
}
