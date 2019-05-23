package View;

import java.util.ArrayList;

import controller.HomeController;
import controller.InlogController;
import controller.PlayerController;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Menubar extends MenuBar {

	private Menu options;
	private Menu games;
	private MenuItem logout;
	private MenuItem exit;
	private MenuItem filter;
	private MenuItem stats;
	private MenuItem help;
	private MyScene main;

	private Alert alert = new Alert(AlertType.INFORMATION);
	private PlayerController self;
	private HomeController home;
    private gameRules rules = new gameRules();
	
	
	private ArrayList<Menu> gameList = new ArrayList<>();
	private int x = 0;
	
	public Menubar(MyScene main, PlayerController self){
		this.main = main;
		this.self = self;
		home = new HomeController(main, self.getPlayer());
	

		creatMenu();
	}
	
	public Menubar() {
		// TODO Auto-generated constructor stub
	}

	private void creatMenu() {
		

		options = new Menu("opties");

		logout = new MenuItem("Log-out");
//		logout.setOnAction(E -> logOut());
		exit = new MenuItem("Afsluiten");
//		exit.setOnAction(E -> exit());

		filter = new MenuItem("Filter");
		help = new MenuItem("spelregels");
		stats = new MenuItem("Statistieken");
		stats.setOnAction(E -> showStats());
		
		//menu.getExit().setOnAction(e -> model.Exit());
		//menu.getLogout().setOnAction(e -> model.logout());
		
		options.getItems().addAll(logout, stats, filter,help, exit);
		this.getMenus().add(options);
	}

	private void showStats() {
		alert.setHeaderText(home.getStats());
		// test
		alert.showAndWait();

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

	//done
	public void creatNewTabs() {
		Menu gamex = new Menu("game" + x);
		this.getMenus().add(gamex);
		gameList.add(gamex);
		x ++;
	}
	
	
	
}
