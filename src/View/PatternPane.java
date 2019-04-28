package View;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class PatternPane extends StackPane{

	private DicePane template;
	private DicePane dice;
	private BoardPane boardPane;
	
	///*
		//BoardPane is required to get and delete the selected DicePane from RootPane. The required DicePane will become the template
		///**
	public PatternPane(BoardPane p, DicePane d) {
		template = d;
		boardPane = p;
		///*
		//If selected is not null, the dice will become the selected DicePane. Dice can't be clicked again when this happens, the selected DicePane will be set to null and dice will be added to the stackPane.
		//Dice will be 'pasted' on the template.
		///**
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(getSelected() != null) {
					if(template.getValue() != 0) {
						if(template.getValue() == getSelected().getValue()) {
							dice = getSelected();
							dice.setMouseTransparent(true);
							getChildren().add(dice);
							deleteSelected();
							return;
						}
						return;
					}
					
					if(template.getColor().equals("WHITE")) {
						dice = getSelected();
						dice.setMouseTransparent(true);
						getChildren().add(dice);
						deleteSelected();
						return;
					}else {
						if(template.getColor().equals(getSelected().getColor())) {
							dice = getSelected();
							dice.setMouseTransparent(true);
							getChildren().add(dice);
							deleteSelected();
							return;
						}
					}
					
					
				}
//				getClicked();
				
			}
			
		});
		getChildren().add(template);
		
	}
	
	///*
		//Sets the color of template.
		///**
	
	public void setColor(String color) {
		template.setColor(color);
	}
	
	///*
		//Sets the color of template to white.
		///**
	
	public void setWhite() {
		template.setWhite();
	}
	
	
//	public void setDice(DicePane p) {
//		dice = p;
//	}
	
//	public void getClicked() {
//		boardPane.getClicked(this);
//	}
	
	///*
		//get and delete the selected DicePane in rootPane.
		///**
	public DicePane getSelected() {
		return boardPane.getSelected();
	}
	
	public void deleteSelected() {
		boardPane.deleteSelected();
	}
	
	public String getDiceColor() {
		return dice.getColor();
	}
	
//	public DicePane getDice() {
//		return dice;
//	}
//	
//	public boolean getNearDice(DicePane p) {
//		return boardPane.getNearDice(this, p);
//	}
	
	
}
