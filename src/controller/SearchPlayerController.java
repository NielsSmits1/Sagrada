package controller;

import View.SearchPlayerPane;
import javafx.scene.Node;
import javafx.scene.Parent;

public class SearchPlayerController  {

	public SearchPlayerPane getSearchPlayerPane(HomeController controller) {
		SearchPlayerPane spp = new SearchPlayerPane(controller);
		return spp;
	}

}
