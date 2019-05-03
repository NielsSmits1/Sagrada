package View;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Menubar extends MenuBar {

	private Menu options;
	private MenuItem logout;
	private MenuItem exit;
	private MenuItem filter;
	private MenuItem stats;
	
	public Menubar(){
		creatMenu();
	}
	
	private void creatMenu() {
		
		options = new Menu("opties");
		
		logout = new MenuItem("Log-out");
		exit = new MenuItem("Afsluiten");
		
		filter = new MenuItem("Filter");
		stats = new MenuItem("Statistieken");
		
		//menu.getExit().setOnAction(e -> model.Exit());
		//menu.getLogout().setOnAction(e -> model.logout());
		
		options.getItems().addAll(logout, exit, filter, stats);
		this.getMenus().add(options);
		
	}

	public MenuItem getExit() {
		return exit;
	}

	public MenuItem getLogout() {
		return logout;
	}
	
	
	
	

}
