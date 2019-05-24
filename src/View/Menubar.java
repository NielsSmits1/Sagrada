package View;

import java.util.ArrayList;

import controller.HomeController;
import controller.InlogController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Menubar extends MenuBar {

	private Menu options;
	private MenuItem logout;
	private MenuItem exit;
	private MenuItem filter;
	private MenuItem stats;
	private MenuItem help;
	private gameRules rules = new gameRules();
	
	
	private ArrayList<Menu> gameList = new ArrayList<>();
	private int x = 0;
	
	public Menubar(){
		creatMenu();
	}

	private void creatMenu() {
		options = new Menu("opties");
		logout = new MenuItem("Log-out");
		exit = new MenuItem("Afsluiten");
		filter = new MenuItem("Filter");
		help = new MenuItem("spelregels");
		stats = new MenuItem("Statistieken");
		options.getItems().addAll(logout, stats, filter,help, exit);
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
	
	public MenuItem getHelp() {
		return help;
	}

	public gameRules getRules() {
		return rules;
	}

	public void addGameItem(GamePane gamePane, int id) {
		Menu m = new Menu("Gamenummer : " + id);
		this.getMenus().add(m);
		
		
	}
	
	
	
}
