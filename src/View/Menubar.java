package View;

import controller.HomeController;
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
	private MenuItem logout;
	private MenuItem exit;
	private MenuItem filter;
	private MenuItem stats;
	private MyScene main;
	private Alert alert = new Alert(AlertType.INFORMATION);
	private PlayerController self;
	private HomeController home;
	
	public Menubar(MyScene main, PlayerController self){
		home = new HomeController(self);
		this.main = main;
		this.self = self;

		creatMenu();
	}
	
	private void creatMenu() {
		
		options = new Menu("opties");
		
		logout = new MenuItem("Log-out");
		logout.setOnAction(E -> logOut());
		exit = new MenuItem("Afsluiten");
		exit.setOnAction(E -> exit());
		
		filter = new MenuItem("Filter");
		stats = new MenuItem("Statistieken");
		stats.setOnAction(E -> showStats());
		
		//menu.getExit().setOnAction(e -> model.Exit());
		//menu.getLogout().setOnAction(e -> model.logout());
		
		options.getItems().addAll(logout, stats, filter, exit);
		this.getMenus().add(options);
		
	}

	private void showStats() {
		alert.setHeaderText(home.getStats());
		// test
		alert.showAndWait();
	}

	private void exit() {
		Platform.exit();
	}

	private void logOut() {
		main.setRoot(new InlogPane(main));
	}

	public MenuItem getExit() {
		return exit;
	}

	public MenuItem getLogout() {
		return logout;
	}
	
	
	
	

}
