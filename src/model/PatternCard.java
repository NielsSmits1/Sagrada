package model;

import java.util.ArrayList;

import java.util.Random;
import Database.db;
import View.DicePane;
import View.PatternPane;
import controller.BoardController;

public class PatternCard {

	private ArrayList<Space> patternfield;
	private ArrayList<Space> randomPatternfield;
	private db database = new db();
	private ArrayList<ArrayList<Object>> p;
	private int patternId;
	private Random random;
	private int idgame;
	private int yourself;
	private boolean hasColorExamption;
	private boolean hasNumberExamption;
	private boolean hasNextToDiceExamption;
	private BoardController controller;

	public PatternCard(int number, int idgame, int ownId, BoardController bc) {
//		randomPatternfield = new ArrayList<Space>();
		patternfield = new ArrayList<>();
		controller = bc;
		this.idgame = idgame;
		yourself = ownId;
		random = new Random();
		setPatternId(number);
		p = getSelect();
		setPatternField();
		addCard();
//		generateRandomPatternCard();
//		insertRandomPatternCardIntoDB();
		hasColorExamption = false;
		hasNumberExamption = false;
		hasNextToDiceExamption = false;
	}

	public PatternCard() {
		random = new Random();
		patternfield = new ArrayList<>();
		setPatternId(random.nextInt(24) + 1);
		p = getSelect();
		setPatternField();
	}

	public ArrayList<ArrayList<Object>> getSelect() {
		return database
				.Select("SELECT * FROM patterncardfield WHERE patterncard_idpatterncard = " + getPatternId() + ";");
	}

	/// *
	// Fills the ArrayList that contains all of the spaces.
	/// **

	public void addCard() {
		for (int i = 0; i < patternfield.size(); i++) {
			addChosenCard(patternfield.get(i).getXPos(), patternfield.get(i).getYPos());
		}
	}

	private void setPatternField() {
		for (int i = 0; i < 20; i++) {
			patternfield.add(new Space());
			patternfield.get(i).setXPOS((int) p.get(i).get(1));
			patternfield.get(i).setYPOS((int) p.get(i).get(2));
			/// *
			// The color might be null, in that case the color will be set the white.
			/// **
			if ((String) p.get(i).get(3) == null) {
				patternfield.get(i).setColor("wit");
			} else {
				patternfield.get(i).setColor((String) p.get(i).get(3));
			}
			if (p.get(i).get(4) != null) {
				patternfield.get(i).setEyes((int) p.get(i).get(4));
			}
		}
	}

	public ArrayList<Space> getPatternField() {
		return patternfield;
	}

	public int getPatternId() {
		return patternId;
	}

	public void setPatternId(int patternId) {
		this.patternId = patternId;
	}

	public void changeField() {
		patternfield.clear();
		p = getSelect();
		setPatternField();
	}

	public void randomNumber() {
		patternId = random.nextInt(23) + 1;
	}

	public void addOptionToDB() {
		database.CUD("insert into tjpmsalt_db2.patterncardoption"
				+ "(patterncard_idpatterncard,player_idplayer) VALUES (" + patternId + "," + yourself + ");");
	}

	// TODO ADD THE CHOSEN PATTERNCARD TO PLAYERFRAMEFIELD

	private boolean checkFirstMove() {
		ArrayList<ArrayList<Object>> getQuery = database
				.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE idgame = " + idgame
						+ " && player_idplayer = " + yourself + " ORDER BY dienumber DESC LIMIT 1;");
		if (getQuery.get(0).get(0) == null) {
			return true;
		}
		return false;

	}

	public void addChosenCard(int xPos, int yPos) {

		database.CUD(
				"insert into tjpmsalt_db2.playerframefield (player_idplayer, position_x,position_y, idgame) VALUES ("
						+ yourself + "," + xPos + "," + yPos + "," + idgame + ");");
	}

	public void moveDie(int dienumber, String diecolor, int xPos, int yPos) {
		database.CUD("UPDATE playerframefield SET diecolor = '" + diecolor + "', dienumber = " + dienumber + " "
				+ "WHERE position_x = " + xPos + " AND position_y = " + yPos + " AND player_idplayer = " + yourself
				+ " AND idgame = " + idgame + ";");

	}

	public void setPositionEmpty(int dienumber, String diecolor, int xPos, int yPos) {
		database.CUD("UPDATE playerframefield SET diecolor = null, dienumber = null  WHERE player_idplayer = "
				+ yourself + " AND idgame = " + idgame + " AND dienumber = " + dienumber + " AND diecolor = '"
				+ diecolor + "';");
	}

	public boolean validateMove(int x, int y, int dienumber, String diecolor) {
		if (totalValidation(x, y, dienumber, diecolor)) {
			return true;
		} else {
			return false;
		}
	}

	public void setColorExamption() {
		hasColorExamption = true;
	}

	public void setNumberExamption() {
		hasNumberExamption = true;

	}

	private boolean totalValidation(int x, int y, int dienumber, String diecolor) {
		String color = diecolor;
		int old_x = 0;
		int old_y = 0;
		if (hasColorExamption || hasNumberExamption || hasNextToDiceExamption) {
			old_x = (int) getPosition(dienumber, diecolor).get(0).get(0);
			old_y = (int) getPosition(dienumber, diecolor).get(0).get(1);
			setPositionEmpty(dienumber, diecolor, x, y);
		}

		if (checkFirstMove()) {
			if (validateStartsInCorner(x, y) && validateColorTemplateBox(x, y, color)
					&& validateNumberTemplateBox(x, y, dienumber, color)) {

				addDiceToField(x, y, dienumber, color);
				if (hasColorExamption || hasNumberExamption || hasNextToDiceExamption) {
					controller.disableMovement(old_x, old_y);
				}
				System.out.println("Eerste move");
				return true;
			}
		} else {
			if (validateColorTemplateBox(x, y, color) && validateNumberTemplateBox(x, y, dienumber, color)
					&& isEmptyPlace(x, y) && validateNextToDice(x, y) && validateNearbyDice(x, y, dienumber, color)) {
				moveDie(dienumber, color, x, y);
				if (hasColorExamption || hasNumberExamption || hasNextToDiceExamption) {
					controller.disableMovement(old_x, old_y);
				}
				return true;
			} else {
				if (hasColorExamption || hasNumberExamption || hasNextToDiceExamption) {
					moveDie(dienumber, diecolor, old_x, old_y);
				}
				return false;
			}

		}
		return false;
	}

	// checks if color is correct
	private boolean validateColorTemplateBox(int x, int y, String diecolor) {
		if (hasColorExamption) {
			hasColorExamption = false;
			return true;
		}
		ArrayList<ArrayList<Object>> getQuery = database
				.Select("SELECT color FROM tjpmsalt_db2.patterncardfield WHERE patterncard_idpatterncard = " + patternId
						+ " && position_x = " + x + " && position_y = " + y);
		if (getQuery.get(0).get(0) == null) {
			return true;

		} else if (diecolor.equals((String) getQuery.get(0).get(0))) {
			return true;

		}
		return false;
	}

	private boolean validateNumberTemplateBox(int x, int y, int dienumber, String diecolor) {
		if (hasNumberExamption) {
			hasNumberExamption = false;
			return true;
		}
		ArrayList<ArrayList<Object>> getQuery = database
				.Select("SELECT value FROM tjpmsalt_db2.patterncardfield WHERE patterncard_idpatterncard = " + patternId
						+ " && position_x = " + x + " && position_y = " + y);
		ArrayList<ArrayList<Object>> dieEyes = database.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = "
				+ idgame + " && dienumber = " + dienumber + " && diecolor = '" + diecolor + "' ;");
		if (getQuery.get(0).get(0) == null) {
			return true;
		}
		if ((int) dieEyes.get(0).get(0) == (int) getQuery.get(0).get(0)) {
			return true;
		}
		return false;
	}

	// TODO niet in combinatie met checkfirstmove doen
	private boolean validateNearbyDice(int x, int y, int dienumber, String diecolor) {
		boolean isEmpty = false;

		if (y - 1 > 0) {
			while (isEmpty == false) {
				ArrayList<ArrayList<Object>> color = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> upPosition = database
						.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = "
								+ idgame + ";");
				if (upPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> upColor = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> upEyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ (int) upPosition.get(0).get(0) + " && diecolor = '" + (String) upColor.get(0).get(0)
								+ "'");
				String currentColor = diecolor;
				if ((currentColor.equals((String) upColor.get(0).get(0)))
						|| ((int) eyes.get(0).get(0) == (int) upEyes.get(0).get(0))) {
					isEmpty = true;
					return false;
				}
				isEmpty = true;
			}
		}
		isEmpty = false;

		if (y + 1 < 5) {
			while (isEmpty == false) {
				ArrayList<ArrayList<Object>> color = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> downPosition = database
						.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = "
								+ idgame + ";");
				if (downPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> downColor = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> downEyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ (int) downPosition.get(0).get(0) + " && diecolor = '"
								+ (String) downColor.get(0).get(0) + "'");
				String currentColor = diecolor;
				if ((currentColor.equals((String) downColor.get(0).get(0)))
						|| ((int) eyes.get(0).get(0) == (int) downEyes.get(0).get(0))) {
					isEmpty = true;
					return false;
				}
				isEmpty = true;
			}
		}
		isEmpty = false;

		if (x + 1 < 6) {
			while (isEmpty == false) {
				ArrayList<ArrayList<Object>> color = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> rightPosition = database
						.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = "
								+ idgame + ";");
				if (rightPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> rightColor = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> rightEyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ (int) rightPosition.get(0).get(0) + " && diecolor = '"
								+ (String) rightColor.get(0).get(0) + "'");
				String currentColor = diecolor;
				if ((currentColor.equals((String) rightColor.get(0).get(0)))
						|| ((int) eyes.get(0).get(0) == (int) rightEyes.get(0).get(0))) {
					isEmpty = true;
					return false;
				}
				isEmpty = true;
			}
		}
		isEmpty = false;

		if (x - 1 > 0) {
			while (isEmpty == false) {
				ArrayList<ArrayList<Object>> color = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> leftPosition = database
						.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = "
								+ idgame + ";");
				if (leftPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> leftColor = database
						.Select("SELECT diecolor FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> leftEyes = database
						.Select("SELECT eyes FROM tjpmsalt_db2.gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ (int) leftPosition.get(0).get(0) + " && diecolor = '"
								+ (String) leftColor.get(0).get(0) + "'");
				String currentColor = diecolor;
				if ((currentColor.equals((String) leftColor.get(0).get(0)))
						|| ((int) eyes.get(0).get(0) == (int) leftEyes.get(0).get(0))) {
					isEmpty = true;
					return false;
				}
				isEmpty = true;
			}
		}
		return true;
	}

	// TODO niet in combinatie met checkfirstmove doen
	private boolean validateNextToDice(int x, int y) {
		// above
		if (hasNextToDiceExamption) {
			hasNextToDiceExamption = false;
			return true;
		}
		if (y - 1 > 0) {
			ArrayList<ArrayList<Object>> upPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = " + idgame + ";");
			if (upPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}
		// up right
		if (y - 1 > 0 && x + 1 < 6) {
			ArrayList<ArrayList<Object>> upRightPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x + 1) + " && position_y = " + (y - 1) + " && idgame = " + idgame
							+ ";");
			if (upRightPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// right
		if (x + 1 < 6) {
			ArrayList<ArrayList<Object>> rightPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = " + idgame + ";");
			if (rightPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}
		// down right
		if (y + 1 < 5 && x + 1 < 6) {
			ArrayList<ArrayList<Object>> downRightPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x + 1) + " && position_y = " + (y + 1) + " && idgame = " + idgame
							+ ";");
			if (downRightPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// bottom
		if (y + 1 < 5) {
			ArrayList<ArrayList<Object>> downPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = " + idgame + ";");
			if (downPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// down left
		if (x - 1 > 0 && y + 1 < 5) {
			ArrayList<ArrayList<Object>> downLeftPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x - 1) + " && position_y = " + (y + 1) + " && idgame = " + idgame
							+ ";");
			if (downLeftPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// left
		if (x - 1 > 0) {
			ArrayList<ArrayList<Object>> leftPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = " + idgame + ";");
			if (leftPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// top left
		if (x - 1 > 0 && y - 1 > 0) {
			ArrayList<ArrayList<Object>> topLeftPosition = database
					.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x - 1) + " && position_y = " + (y - 1) + " && idgame = " + idgame
							+ ";");
			if (topLeftPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}
		return false;

	}

	private boolean isEmptyPlace(int x, int y) {
		if (database
				.Select("SELECT dienumber FROM tjpmsalt_db2.playerframefield WHERE position_x = " + x
						+ "&& position_y = " + y + " && player_idplayer = " + yourself + " && idgame = " + idgame)
				.get(0).get(0) == null) {
			return true;

		}
		return false;
	}

	private boolean validateStartsInCorner(int x, int y) {
		if ((x > 1 && x < 5) && (y > 1 && y < 4)) {
			return false;

		}
		return true;
	}

	private void addDiceToField(int x, int y, int dienumber, String color) {
		database.CUD("UPDATE tjpmsalt_db2.playerframefield SET dienumber = " + dienumber + ", diecolor = '" + color
				+ "' WHERE player_idplayer = " + yourself + " AND position_x = " + x + " AND position_y = " + y
				+ " AND idgame = " + idgame + ";");
	}

	private ArrayList<ArrayList<Object>> getPosition(int dienumber, String diecolor) {
		return database.Select("SELECT position_x, position_y FROM tjpmsalt_db2.playerframefield WHERE idgame = "
				+ idgame + " AND player_idplayer = " + yourself + " AND dienumber = " + dienumber + " AND diecolor = '"
				+ diecolor + "';");
	}

	public void setNextToDiceExamption() {
		hasNextToDiceExamption = true;
	}

	public void generateRandomPatternCard() {
		boolean wantToFill = false;
		boolean colorOrNumber = false;
		int amountPlaced = 0;
		for (int x = 1; x <= 5; x++) {
			for (int y = 1; y <= 4; y++) {
				randomPatternfield.add(new Space(x, y));
			}
		}

		for (int i = 0; i < randomPatternfield.size(); i++) {
			wantToFill = random.nextBoolean();
			colorOrNumber = random.nextBoolean();
			if (wantToFill) {

				if (colorOrNumber) {
					randomPatternfield.get(i).setColor(getRandomColor());
					for (int j = 0; j < randomPatternfield.size(); j++) {
						//Left
						if(randomPatternfield.get(i).getXPos() - 1 > 0 && randomPatternfield.get(i).getXPos() - 1 == randomPatternfield.get(j).getXPos() && randomPatternfield.get(i).getYPos() == randomPatternfield.get(j).getYPos()) {
							if(randomPatternfield.get(i).getColor().equals(randomPatternfield.get(j).getColor())) {
								randomPatternfield.get(i).setColor("");
								break;
							}
						}
						
						//Up
						if(randomPatternfield.get(i).getYPos() - 1 > 0 &&randomPatternfield.get(i).getYPos() - 1 == randomPatternfield.get(j).getYPos() && randomPatternfield.get(i).getXPos() == randomPatternfield.get(j).getXPos()) {
							if(randomPatternfield.get(i).getColor().equals(randomPatternfield.get(j).getColor())) {
								randomPatternfield.get(i).setColor("");
								break;
							}
						}
		
					}

				}
					
				if(!colorOrNumber) {
					randomPatternfield.get(i).setEyes(random.nextInt(6)+1);
					for (int j = 0; j < randomPatternfield.size(); j++) {
						//Left
						if(randomPatternfield.get(i).getXPos() - 1 > 0 && randomPatternfield.get(i).getXPos() - 1 == randomPatternfield.get(j).getXPos() && randomPatternfield.get(i).getYPos() == randomPatternfield.get(j).getYPos()) {
							if(randomPatternfield.get(i).getEyes() == randomPatternfield.get(j).getEyes()) {
								randomPatternfield.get(i).setEyes(0);
								break;
							}
						}
						
						//Up
						if(randomPatternfield.get(i).getYPos() - 1 > 0 &&randomPatternfield.get(i).getYPos() - 1 == randomPatternfield.get(j).getYPos() && randomPatternfield.get(i).getXPos() == randomPatternfield.get(j).getXPos()) {
							if(randomPatternfield.get(i).getEyes() == randomPatternfield.get(j).getEyes()) {
								randomPatternfield.get(i).setEyes(0);
								break;
							}
						}	
						
					}
				}
			}

		}
	}
	
	public int getDifficulty() {
		int amountPlaced = 0;
		for (int i = 0; i < randomPatternfield.size(); i++) {
			if(randomPatternfield.get(i).getEyes() != 0 || !randomPatternfield.get(i).getColor().equals("")) {
				amountPlaced++;			
			}
		}
		
		if(amountPlaced < 3) {
			return 1;
		}
		if(amountPlaced > 2 && amountPlaced <= 5) {
			return 2;
		}
		if(amountPlaced > 5 && amountPlaced <= 6) {
			return 3;
		}
		if(amountPlaced > 6 && amountPlaced < 10) {
			return 4;
		}
		if(amountPlaced > 9 && amountPlaced < 14) {
			return 5;
		}
		if(amountPlaced > 13) {
			return 6;
		}
		
		return 0;
	}
	
	public void insertRandomPatternCardIntoDB() {
		database.CUD("INSERT INTO patterncard (idpatterncard, difficulty, standard) VALUES(" + (int)getNewPatternId() +"," + getDifficulty() + ", 0);");
		insertRandomPatternCardSpaces();
	}
	
	public void insertRandomPatternCardSpaces() {
		for (int i = 0; i < randomPatternfield.size(); i++) {
			database.CUD("INSERT INTO patterncardfield (patterncard_idpatterncard, position_x, position_y) VALUES(" + getHighestPatternId() +"," + randomPatternfield.get(i).getXPos() + ", " + randomPatternfield.get(i).getYPos() +");");
			if(!randomPatternfield.get(i).getColor().equals("")) {
				database.CUD("UPDATE patterncardfield SET color = '" + randomPatternfield.get(i).getColor() + "' WHERE patterncard_idpatterncard = " + getHighestPatternId() + " AND position_x = " + randomPatternfield.get(i).getXPos() + " AND position_y = " + randomPatternfield.get(i).getYPos() + "");
			}
			
			if(randomPatternfield.get(i).getEyes() != 0) {
				database.CUD("UPDATE patterncardfield SET value = " + randomPatternfield.get(i).getEyes() + " WHERE patterncard_idpatterncard = " + getHighestPatternId() + " AND position_x = " + randomPatternfield.get(i).getXPos() + " AND position_y = " + randomPatternfield.get(i).getYPos() + "");
			}
		}
	}
	
	
	public long getNewPatternId() {
		return (long) database.Select("SELECT idpatterncard+1 FROM tjpmsalt_db2.patterncard ORDER BY idpatterncard DESC LIMIT 1").get(0).get(0);
	}
	
	public int getHighestPatternId() {
		return (int) database.Select("SELECT idpatterncard FROM tjpmsalt_db2.patterncard ORDER BY idpatterncard DESC LIMIT 1").get(0).get(0);
	}
		
	
	
	public String getRandomColor() {
		String[] color = {"blauw", "groen", "rood", "geel", "paars"};
		return color[random.nextInt(color.length)];
	}
	
	public ArrayList<Space> getRandom(){
		return randomPatternfield;
	}

//	private boolean allowsColorPlacement(int x, int y) {
//
//		if (x + 1 >= 6) {	
//		}else if(){
//			
//		}
//
//		return false;
//	}

}
