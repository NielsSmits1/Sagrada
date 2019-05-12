package controller;

import View.Menubar;
import javafx.scene.Node;
import javafx.scene.Parent;

public class MenubarController {

	private Menubar menu;

	public MenubarController() {
		menu = new Menubar();
	}

	public Menubar getMenubar() {

		return menu;
	}

}
