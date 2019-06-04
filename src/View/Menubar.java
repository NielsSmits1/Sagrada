package View;

import java.util.ArrayList;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Menubar extends MenuBar {

	private Menu options;
	private MenuItem logout;
	private MenuItem exit;
	private MenuItem stats;
	private MenuItem help;
	private MenuItem home;
	private gameRules rules = new gameRules();

	private ArrayList<Menu> gameList = new ArrayList<>();
	private int x = 0;

	public Menubar(MyScene main) {
		creatMenu();
	}

	private void creatMenu() {
		options = new Menu("opties");
		logout = new MenuItem("Log-out");
		exit = new MenuItem("Afsluiten");
		home = new MenuItem("ga terug naar home / refresh home");
		help = new MenuItem("spelregels");
		stats = new MenuItem("Statistieken");
		options.getItems().addAll(logout, stats, home, help, exit);
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

	public void creatNewTabs() {
		Menu gamex = new Menu("game" + x);
		this.getMenus().add(gamex);
		gameList.add(gamex);
		x++;
	}

	public void addGameItem(Menu m) {
		this.getMenus().add(m);

	}

	public MenuItem getHome() {
		return home;
	}

}
