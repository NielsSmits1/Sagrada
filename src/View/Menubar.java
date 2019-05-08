package View;

import controller.PlayerController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Menubar extends MenuBar {

	private Menu options;
	private Menu games;
	private MenuItem logout;
	private MenuItem exit;
	private MenuItem filter;
	private MenuItem stats;
	private MyScene main;
	
	private PlayerController self;

	public Menubar() {
		creatMenu();
	}

	public void creatMenu() {

		options = new Menu("opties");

		logout = new MenuItem("Log-out");
		logout.setOnAction(E -> logOut());
		exit = new MenuItem("Afsluiten");
//		exit.setOnAction(E -> exit());

		filter = new MenuItem("Filter");
		stats = new MenuItem("Statistieken");
//		stats.setOnAction(E -> showStats());

		// menu.getExit().setOnAction(e -> model.Exit());
		// menu.getLogout().setOnAction(e -> model.logout());

		options.getItems().addAll(logout, stats, filter, exit);
		this.getMenus().add(options);

	}

		
	

	public MenuItem getExit() {
		return exit;
	}

	public MenuItem getLogout() {
		return logout;
	}

	
	public MenuItem getStats() {
		return stats;
	}
	
	
}
