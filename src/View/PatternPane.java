package View;

import javafx.scene.layout.StackPane;

public class PatternPane extends StackPane{

	private DicePane template;
	private DicePane dice;
	private BoardPane boardPane;
	private int xPos;
	private int yPos;
	///*
		//BoardPane is required to get and delete the selected DicePane from RootPane. The required DicePane will become the template
		///**
	public PatternPane(BoardPane p, DicePane d, int x, int y) {
		template = d;
		boardPane = p;
		xPos = x;
		yPos = y;
//		System.out.println("" + xPos + " " + yPos);
		///*
		//If selected is not null, the dice will become the selected DicePane. Dice can't be clicked again when this happens, the selected DicePane will be set to null and dice will be added to the stackPane.
		//Dice will be 'pasted' on the template.
		///**
		setOnMouseClicked(e -> handle());
		getChildren().add(template);
		
	}
	
	public PatternPane(BoardPane p, DicePane d) {
		template = d;
		boardPane = p;
//		System.out.println("" + xPos + " " + yPos);
		///*
		//If selected is not null, the dice will become the selected DicePane. Dice can't be clicked again when this happens, the selected DicePane will be set to null and dice will be added to the stackPane.
		//Dice will be 'pasted' on the template.
		///**
		getChildren().add(template);
		
	}
	
	public PatternPane(DicePane d) {
		template = d;
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
//	public DicePane getSelected() {
//		return boardPane.getSelected();
//	}
	
	public void giveCords() {
		boardPane.giveCords(xPos, yPos);
	}
	
//	public void deleteSelected() {
//		boardPane.deleteSelected();
//	}
	public int getDiceNumber() {
		return dice.getDieNumber();
	}
	public String getDiceColor() {
		return dice.getColor();
	}
	
//	public int getNumber() {
//		return number;
//	}
	
	public String getColor() {
		return dice.getColor();
	}

	public DicePane getDice() {
		return dice;
	}
	
	public int getEyes() {
		if(dice != null) {
			return dice.getValue();
		}
		return 0;
	}
	
	public int getX() {
		return xPos;
	}
	

	public int getY() {
		return yPos;
	}
	
	public void setDice(DicePane selected) {
		if(getChildren().size() > 1) {
			getChildren().remove(1);
		}
		dice = selected;
		dice.setMouseTransparent(true);
		getChildren().add(dice);
	}
	
	public void moveDice(int dieNumber, String color) {
		boardPane.moveDice(dieNumber,color, xPos, yPos);
	}
	
//	public void moveDiceAccepted(DicePane selected) {
//		System.out.println(dienumber + diecolor);
//		System.out.println(boardPane.getSelected().getEyes());
//		dice = new DicePane(boardPane.getSelected().getEyes(), diecolor, dienumber);
//		getChildren().add(dice);
//	}
	
	public void handle() {
		if(boardPane.getAllowsMovement()) {
			if(boardPane.getSelected() == null) {
				boardPane.setSelectedPatternPane(this);
				return;
			}else {
				moveDice(boardPane.getSelected().getDiceNumber(), boardPane.getSelected().getDiceColor());
				
			}
			
		}
		giveCords();
	}
	
	public void setDiceNull() {
		dice = null;
//		getChildren().remove(1);
	}
	
//	public boolean getNearDice(DicePane p) {
//		return boardPane.getNearDice(this, p);
//	}
	

	
}
