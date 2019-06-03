package model;

import java.util.ArrayList;

import java.util.Random;
import Database.db;
import View.DicePane;
import View.PatternPane;
import controller.BoardController;

public class PatternCard {

	private ArrayList<Space> patternfield;
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
	private ArrayList<PlacedDice> diceField;

	public PatternCard(int number, int idgame, int ownId, BoardController bc) {
		// patternfield = new ArrayList<Space>();
		patternfield = new ArrayList<>();
		controller = bc;
		this.idgame = idgame;
		yourself = ownId;
		random = new Random();
		setPatternId(number);
		p = getSelect();
		setpatternfield();
		addCard();
		// generateRandomPatternCard();
		// insertRandomPatternCardIntoDB();
		hasColorExamption = false;
		hasNumberExamption = false;
		hasNextToDiceExamption = false;
	}
	
	

	public PatternCard(int number) {
		patternfield = new ArrayList<>();
		// TODO aanpassen naar de size van de list van beschikbare kaarten
		setPatternId(number);
		p = getSelect();
		setpatternfield();
	}

	public PatternCard(int ownId, int idgame, BoardController bc) {
		random = new Random();
		yourself = ownId;
		this.idgame = idgame;
		controller = bc;
		patternfield = new ArrayList<>();
		generateRandomPatternCard();
		insertRandomPatternCardIntoDB();
		setPatternId(getHighestPatternId());
		p = getSelect();
		addCard();
		hasColorExamption = false;
		hasNumberExamption = false;
		hasNextToDiceExamption = false;
		// setpatternfield();
	}
	
	public PatternCard(int ownId, int idgame, int patternid) {
		yourself = ownId;
		this.idgame = idgame;
		patternfield = new ArrayList<>();
		setPatternId(patternid);
		diceField = new ArrayList<>();
		setPlacedDice();
		p = getSelect();
		setpatternfield();
		hasColorExamption = false;
		hasNumberExamption = false;
		hasNextToDiceExamption = false;
		// setpatternfield();
	}
	
	public PatternCard(BoardController bc, int ownId, int idgame, int patternid) {
		yourself = ownId;
		this.idgame = idgame;
		controller = bc;
		setPatternId(patternid);
		p = getSelect();
		hasColorExamption = false;
		hasNumberExamption = false;
		hasNextToDiceExamption = false;
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
	
	public void setPlacedDice() {
		diceField.clear();
		ArrayList<ArrayList<Object>> placed = database.Select("SELECT position_x, position_y, playerframefield.dienumber, playerframefield.diecolor, gamedie.eyes FROM playerframefield RIGHT JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE player_idplayer = " + yourself + " AND playerframefield.dienumber IS NOT NULL;");
		for (int i = 0; i < placed.size(); i++) {
			diceField.add(new PlacedDice((int)placed.get(i).get(0), (int)placed.get(i).get(1), (int)placed.get(i).get(2), (String)placed.get(i).get(3), (int)placed.get(i).get(4)));
		}
	}

	private void setpatternfield() {
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
	
	public ArrayList<PlacedDice> getDiceField(){
		return diceField;
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
		setpatternfield();
	}

	public void randomNumber() {
		patternId = random.nextInt(23) + 1;
	}

	public void addOptionToDB() {
		database.CUD("insert into patterncardoption"
				+ "(patterncard_idpatterncard,player_idplayer) VALUES (" + patternId + "," + yourself + ");");
	}

	// TODO ADD THE CHOSEN PATTERNCARD TO PLAYERFRAMEFIELD

	private boolean checkFirstMove() {
		ArrayList<ArrayList<Object>> getQuery = database
				.Select("SELECT dienumber FROM playerframefield WHERE idgame = " + idgame
						+ " AND player_idplayer = " + yourself + " ORDER BY dienumber DESC LIMIT 1;");
		if (getQuery.get(0).get(0) == null) {
			return true;
		}
		return false;

	}

	public void addChosenCard(int xPos, int yPos) {

		database.CUD(
				"insert into playerframefield (player_idplayer, position_x,position_y, idgame) VALUES ("
						+ yourself + "," + xPos + "," + yPos + "," + idgame + ");");
	}
	
	public ArrayList<ArrayList<Object>> getPlayerframeField(int idplayer, int idgame){
		return database.Select("SELECT * FROM playerframefield WHERE player_idplayer = " + idplayer + ", idgame = " + idgame + "");
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
					controller.disableMovement(old_x, old_y);
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
				.Select("SELECT color FROM patterncardfield WHERE patterncard_idpatterncard = " + patternId
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
				.Select("SELECT value FROM patterncardfield WHERE patterncard_idpatterncard = " + patternId
						+ " && position_x = " + x + " && position_y = " + y);
		ArrayList<ArrayList<Object>> dieEyes = database.Select("SELECT eyes FROM gamedie WHERE idgame = "
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
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> upPosition = database
						.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = "
								+ idgame + ";");
				if (upPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> upColor = database
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> upEyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> downPosition = database
						.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = "
								+ idgame + ";");
				if (downPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> downColor = database
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> downEyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> rightPosition = database
						.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = "
								+ idgame + ";");
				if (rightPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> rightColor = database
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> rightEyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> leftPosition = database
						.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = "
								+ idgame + ";");
				if (leftPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> leftColor = database
						.Select("SELECT diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> leftEyes = database
						.Select("SELECT eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = " + idgame + ";");
			if (upPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}
		// up right
		if (y - 1 > 0 && x + 1 < 6) {
			ArrayList<ArrayList<Object>> upRightPosition = database
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = " + idgame + ";");
			if (rightPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}
		// down right
		if (y + 1 < 5 && x + 1 < 6) {
			ArrayList<ArrayList<Object>> downRightPosition = database
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = " + idgame + ";");
			if (downPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// down left
		if (x - 1 > 0 && y + 1 < 5) {
			ArrayList<ArrayList<Object>> downLeftPosition = database
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = " + idgame + ";");
			if (leftPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// top left
		if (x - 1 > 0 && y - 1 > 0) {
			ArrayList<ArrayList<Object>> topLeftPosition = database
					.Select("SELECT dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
				.Select("SELECT dienumber FROM playerframefield WHERE position_x = " + x
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
		database.CUD("UPDATE playerframefield SET dienumber = " + dienumber + ", diecolor = '" + color
				+ "' WHERE player_idplayer = " + yourself + " AND position_x = " + x + " AND position_y = " + y
				+ " AND idgame = " + idgame + ";");
	}

	private ArrayList<ArrayList<Object>> getPosition(int dienumber, String diecolor) {
		return database.Select("SELECT position_x, position_y FROM playerframefield WHERE idgame = "
				+ idgame + " AND player_idplayer = " + yourself + " AND dienumber = " + dienumber + " AND diecolor = '"
				+ diecolor + "';");
	}

	public void setNextToDiceExamption() {
		hasNextToDiceExamption = true;
	}

	public void generateRandomPatternCard() {
		boolean wantToFill = false;
		boolean colorOrNumber = false;
		for (int x = 1; x <= 5; x++) {
			for (int y = 1; y <= 4; y++) {
				patternfield.add(new Space(x, y));
			}
		}

		for (int i = 0; i < patternfield.size(); i++) {
			wantToFill = random.nextBoolean();
			colorOrNumber = random.nextBoolean();
			if (wantToFill) {

				if (colorOrNumber) {
					patternfield.get(i).setColor(getRandomColor());
					for (int j = 0; j < patternfield.size(); j++) {
						// Left
						if (patternfield.get(i).getXPos() - 1 > 0
								&& patternfield.get(i).getXPos() - 1 == patternfield.get(j).getXPos()
								&& patternfield.get(i).getYPos() == patternfield.get(j).getYPos()) {
							if (patternfield.get(i).getColor().equals(patternfield.get(j).getColor())) {
								patternfield.get(i).setColor("");
								break;
							}
						}

						// Up
						if (patternfield.get(i).getYPos() - 1 > 0
								&& patternfield.get(i).getYPos() - 1 == patternfield.get(j).getYPos()
								&& patternfield.get(i).getXPos() == patternfield.get(j).getXPos()) {
							if (patternfield.get(i).getColor().equals(patternfield.get(j).getColor())) {
								patternfield.get(i).setColor("");
								break;
							}
						}

					}

				}

				if (!colorOrNumber) {
					patternfield.get(i).setEyes(random.nextInt(6) + 1);
					for (int j = 0; j < patternfield.size(); j++) {
						// Left
						if (patternfield.get(i).getXPos() - 1 > 0
								&& patternfield.get(i).getXPos() - 1 == patternfield.get(j).getXPos()
								&& patternfield.get(i).getYPos() == patternfield.get(j).getYPos()) {
							if (patternfield.get(i).getEyes() == patternfield.get(j).getEyes()) {
								patternfield.get(i).setEyes(0);
								break;
							}
						}

						// Up
						if (patternfield.get(i).getYPos() - 1 > 0
								&& patternfield.get(i).getYPos() - 1 == patternfield.get(j).getYPos()
								&& patternfield.get(i).getXPos() == patternfield.get(j).getXPos()) {
							if (patternfield.get(i).getEyes() == patternfield.get(j).getEyes()) {
								patternfield.get(i).setEyes(0);
								break;
							}
						}

					}
				}
			}

		}
	}

	public int getRandomDifficulty() {
		int amountPlaced = 0;
		for (int i = 0; i < patternfield.size(); i++) {
			if (patternfield.get(i).getEyes() != 0 || !patternfield.get(i).getColor().equals("")) {
				amountPlaced++;
			}
		}

		if (amountPlaced < 3) {
			return 1;
		}
		if (amountPlaced > 2 && amountPlaced <= 5) {
			return 2;
		}
		if (amountPlaced > 5 && amountPlaced <= 6) {
			return 3;
		}
		if (amountPlaced > 6 && amountPlaced < 10) {
			return 4;
		}
		if (amountPlaced > 9 && amountPlaced < 14) {
			return 5;
		}
		if (amountPlaced > 13) {
			return 6;
		}

		return 0;
	}

	public void insertRandomPatternCardIntoDB() {
		database.CUD("INSERT INTO patterncard (idpatterncard, difficulty, standard) VALUES(" + (int) getNewPatternId()
				+ "," + getRandomDifficulty() + ", 0);");
		insertRandomPatternCardSpaces();
	}

	public void insertRandomPatternCardSpaces() {
		for (int i = 0; i < patternfield.size(); i++) {
			database.CUD("INSERT INTO patterncardfield (patterncard_idpatterncard, position_x, position_y) VALUES("
					+ getHighestPatternId() + "," + patternfield.get(i).getXPos() + ", " + patternfield.get(i).getYPos()
					+ ");");
			if (!patternfield.get(i).getColor().equals("")) {
				database.CUD("UPDATE patterncardfield SET color = '" + patternfield.get(i).getColor()
						+ "' WHERE patterncard_idpatterncard = " + getHighestPatternId() + " AND position_x = "
						+ patternfield.get(i).getXPos() + " AND position_y = " + patternfield.get(i).getYPos() + "");
			}

			if (patternfield.get(i).getEyes() != 0) {
				database.CUD("UPDATE patterncardfield SET value = " + patternfield.get(i).getEyes()
						+ " WHERE patterncard_idpatterncard = " + getHighestPatternId() + " AND position_x = "
						+ patternfield.get(i).getXPos() + " AND position_y = " + patternfield.get(i).getYPos() + "");
			}
		}
	}

	public long getNewPatternId() {
		return (long) database
				.Select("SELECT idpatterncard+1 FROM patterncard ORDER BY idpatterncard DESC LIMIT 1")
				.get(0).get(0);
	}

	public int getHighestPatternId() {
		return (int) database
				.Select("SELECT idpatterncard FROM patterncard ORDER BY idpatterncard DESC LIMIT 1").get(0)
				.get(0);
	}

	public String getRandomColor() {
		String[] color = { "blauw", "groen", "rood", "geel", "paars" };
		return color[random.nextInt(color.length)];
	}

	public int getDifficulty() {
		return (int) database.Select("SELECT difficulty FROM patterncard WHERE idpatterncard = " + getPatternId() + ";")
				.get(0).get(0);
	}
	
	public int getObjectiveCardOne() {
		int[] number = new int[6];
		for(PlacedDice pd : diceField) {
			switch(pd.getEyes()) {
			case 1: number[0] += number[0] + 1;
			break;
			case 2: number[1] += number[1] + 1;
			break;
			case 3: number[2] += number[2] + 1;
			break;
			case 4: number[3] += number[3] + 1;
			break;
			case 5: number[4] += number[4] + 1;
			break;
			case 6: number[5] += number[5] + 1;
			break;
			default: break;
			}
		}
		int counter = 0;
		while(counter != 100) {
			for(int i = 0;i<number.length;i++) {
				if(number[i] > counter) {
					
				}else {
					return counter*5;
				}
			}
			counter++;
		}
		return 0;
		
	}
	
	
	

	
	

}
