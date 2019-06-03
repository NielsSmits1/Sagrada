package model;

import java.util.ArrayList;

import java.util.Random;
import Database.Db;
import View.DicePane;
import View.PatternPane;
import controller.BoardController;

public class PatternCard {

	private ArrayList<Space> patternfield;
	private Db database = new Db();
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
		p = getselect();
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
		p = getselect();
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
		p = getselect();
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
		p = getselect();
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
		p = getselect();
		hasColorExamption = false;
		hasNumberExamption = false;
		hasNextToDiceExamption = false;
	}

	public ArrayList<ArrayList<Object>> getselect() {
		return database
				.select("select * FROM patterncardfield WHERE patterncard_idpatterncard = " + getPatternId() + ";");
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
		ArrayList<ArrayList<Object>> placed = database.select("select position_x, position_y, playerframefield.dienumber, playerframefield.diecolor, gamedie.eyes FROM playerframefield RIGHT JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE player_idplayer = " + yourself + " AND playerframefield.dienumber IS NOT NULL;");
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
		p = getselect();
		setpatternfield();
	}

	public void randomNumber() {
		patternId = random.nextInt(23) + 1;
	}

	public void addOptionToDB() {
		database.cud("insert into patterncardoption"
				+ "(patterncard_idpatterncard,player_idplayer) VALUES (" + patternId + "," + yourself + ");");
	}

	// TODO ADD THE CHOSEN PATTERNCARD TO PLAYERFRAMEFIELD

	private boolean checkFirstMove() {
		ArrayList<ArrayList<Object>> getQuery = database
				.select("select dienumber FROM playerframefield WHERE idgame = " + idgame
						+ " AND player_idplayer = " + yourself + " ORDER BY dienumber DESC LIMIT 1;");
		if (getQuery.get(0).get(0) == null) {
			return true;
		}
		return false;

	}

	public void addChosenCard(int xPos, int yPos) {

		database.cud(
				"insert into playerframefield (player_idplayer, position_x,position_y, idgame) VALUES ("
						+ yourself + "," + xPos + "," + yPos + "," + idgame + ");");
	}
	
	public ArrayList<ArrayList<Object>> getPlayerframeField(int idplayer, int idgame){
		return database.select("select * FROM playerframefield WHERE player_idplayer = " + idplayer + ", idgame = " + idgame + "");
	}

	public void moveDie(int dienumber, String diecolor, int xPos, int yPos) {
		database.cud("UPDATE playerframefield SET diecolor = '" + diecolor + "', dienumber = " + dienumber + " "
				+ "WHERE position_x = " + xPos + " AND position_y = " + yPos + " AND player_idplayer = " + yourself
				+ " AND idgame = " + idgame + ";");

	}

	public void setPositionEmpty(int dienumber, String diecolor, int xPos, int yPos) {
		database.cud("UPDATE playerframefield SET diecolor = null, dienumber = null  WHERE player_idplayer = "
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
				.select("select color FROM patterncardfield WHERE patterncard_idpatterncard = " + patternId
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
				.select("select value FROM patterncardfield WHERE patterncard_idpatterncard = " + patternId
						+ " && position_x = " + x + " && position_y = " + y);
		ArrayList<ArrayList<Object>> dieEyes = database.select("select eyes FROM gamedie WHERE idgame = "
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
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> upPosition = database
						.select("select dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = "
								+ idgame + ";");
				if (upPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> upColor = database
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> upEyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> downPosition = database
						.select("select dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = "
								+ idgame + ";");
				if (downPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> downColor = database
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> downEyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> rightPosition = database
						.select("select dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = "
								+ idgame + ";");
				if (rightPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> rightColor = database
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> rightEyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + x + " && position_y = " + y + " && idgame = " + idgame + ";");
				ArrayList<ArrayList<Object>> eyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
								+ dienumber + " && diecolor = '" + diecolor + "' ;");
				ArrayList<ArrayList<Object>> leftPosition = database
						.select("select dienumber FROM playerframefield WHERE player_idplayer = "
								+ yourself + " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = "
								+ idgame + ";");
				if (leftPosition.get(0).get(0) == null) {
					isEmpty = true;
					continue;
				}
				ArrayList<ArrayList<Object>> leftColor = database
						.select("select diecolor FROM playerframefield WHERE player_idplayer = " + yourself
								+ " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = " + idgame
								+ ";");
				ArrayList<ArrayList<Object>> leftEyes = database
						.select("select eyes FROM gamedie WHERE idgame = " + idgame + " && dienumber = "
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
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + x + " && position_y = " + (y - 1) + " && idgame = " + idgame + ";");
			if (upPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}
		// up right
		if (y - 1 > 0 && x + 1 < 6) {
			ArrayList<ArrayList<Object>> upRightPosition = database
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x + 1) + " && position_y = " + y + " && idgame = " + idgame + ";");
			if (rightPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}
		// down right
		if (y + 1 < 5 && x + 1 < 6) {
			ArrayList<ArrayList<Object>> downRightPosition = database
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + x + " && position_y = " + (y + 1) + " && idgame = " + idgame + ";");
			if (downPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// down left
		if (x - 1 > 0 && y + 1 < 5) {
			ArrayList<ArrayList<Object>> downLeftPosition = database
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
							+ " && position_x = " + (x - 1) + " && position_y = " + y + " && idgame = " + idgame + ";");
			if (leftPosition.get(0).get(0) == null) {
			} else {
				return true;
			}
		}

		// top left
		if (x - 1 > 0 && y - 1 > 0) {
			ArrayList<ArrayList<Object>> topLeftPosition = database
					.select("select dienumber FROM playerframefield WHERE player_idplayer = " + yourself
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
				.select("select dienumber FROM playerframefield WHERE position_x = " + x
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
		database.cud("UPDATE playerframefield SET dienumber = " + dienumber + ", diecolor = '" + color
				+ "' WHERE player_idplayer = " + yourself + " AND position_x = " + x + " AND position_y = " + y
				+ " AND idgame = " + idgame + ";");
	}

	private ArrayList<ArrayList<Object>> getPosition(int dienumber, String diecolor) {
		return database.select("select position_x, position_y FROM playerframefield WHERE idgame = "
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
		database.cud("INSERT INTO patterncard (idpatterncard, difficulty, standard) VALUES(" + (int) getNewPatternId()
				+ "," + getRandomDifficulty() + ", 0);");
		insertRandomPatternCardSpaces();
	}

	public void insertRandomPatternCardSpaces() {
		for (int i = 0; i < patternfield.size(); i++) {
			database.cud("INSERT INTO patterncardfield (patterncard_idpatterncard, position_x, position_y) VALUES("
					+ getHighestPatternId() + "," + patternfield.get(i).getXPos() + ", " + patternfield.get(i).getYPos()
					+ ");");
			if (!patternfield.get(i).getColor().equals("")) {
				database.cud("UPDATE patterncardfield SET color = '" + patternfield.get(i).getColor()
						+ "' WHERE patterncard_idpatterncard = " + getHighestPatternId() + " AND position_x = "
						+ patternfield.get(i).getXPos() + " AND position_y = " + patternfield.get(i).getYPos() + "");
			}

			if (patternfield.get(i).getEyes() != 0) {
				database.cud("UPDATE patterncardfield SET value = " + patternfield.get(i).getEyes()
						+ " WHERE patterncard_idpatterncard = " + getHighestPatternId() + " AND position_x = "
						+ patternfield.get(i).getXPos() + " AND position_y = " + patternfield.get(i).getYPos() + "");
			}
		}
	}

	public long getNewPatternId() {
		return (long) database
				.select("select idpatterncard+1 FROM patterncard ORDER BY idpatterncard DESC LIMIT 1")
				.get(0).get(0);
	}

	public int getHighestPatternId() {
		return (int) database
				.select("select idpatterncard FROM patterncard ORDER BY idpatterncard DESC LIMIT 1").get(0)
				.get(0);
	}

	public String getRandomColor() {
		String[] color = { "blauw", "groen", "rood", "geel", "paars" };
		return color[random.nextInt(color.length)];
	}

	public int getDifficulty() {
		return (int) database.select("select difficulty FROM patterncard WHERE idpatterncard = " + getPatternId() + ";")
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
	
	public int getObjectiveCardTwo() {
		int[] number = new int[2];
		for(PlacedDice pd : diceField) {
			switch(pd.getEyes()) {
			case 3: number[0] += number[0] + 1;
			break;
			case 4: number[1] += number[1] + 1;
			break;
			default: break;
			}
		}
		int counter = 0;
		while(counter != 100) {
			for(int i = 0;i<number.length;i++) {
				if(number[i] > counter) {
					
				}else {
					return counter*2;
				}
			}
			counter++;
		}
		return 0;
	}
	
	public int getObjectiveCardThree(int idplayer) {
		ArrayList<ArrayList<Object>> Column = new ArrayList<>();
		int counter = 0;
		for(int i = 1; i <= 5; i++) {
			Column = database.select("select DISTINCT p.position_x, p.position_y, d.eyes from playerframefield p join gamedie d on p.idgame = d.idgame and p.dienumber = d.dienumber and p.diecolor = d.diecolor where p.player_idplayer = " + idplayer +" AND position_x = " + i +";");
			if(Column.size() == 4) {
				counter++;
			}
		}
		return counter*4;
	}
	
	public int getObjectiveCardFour(int idplayer) {
		ArrayList<ArrayList<Object>> Column = new ArrayList<>();
		int counter = 0;
		for(int i = 1; i <= 5; i++) {
			Column = database.select("select DISTINCT p.position_x, p.position_y, p.diecolor from playerframefield p  where p.player_idplayer = " + idplayer +" AND position_x = " + i +" AND diecolor is not null;");
			if(Column.size() == 4) {
				counter++;
			}
		}
		return counter*4;
	}
	
	public int getObjectiveCardFive() {
		int[] number = new int[2];
		for(PlacedDice pd : diceField) {
			switch(pd.getEyes()) {
			case 5: number[0] += number[0] + 1;
			break;
			case 6: number[1] += number[1] + 1;
			break;
			default: break;
			}
		}
		int counter = 0;
		while(counter != 100) {
			for(int i = 0;i<number.length;i++) {
				if(number[i] > counter) {
					
				}else {
					return counter*2;
				}
			}
			counter++;
		}
		return 0;
	}
	
	public int getObjectiveCardSix() {
		int[] color = new int[5];
		for(PlacedDice pd : diceField) {
			switch(pd.getDieColor()) {
			case "blauw": color[0] += color[0] + 1;
			break;
			case "geel": color[1] += color[1] + 1;
			break;
			case "groen": color[2] += color[2] + 1;
			break;
			case "paars": color[3] += color[3] + 1;
			break;
			case "rood": color[4] += color[4] + 1;
			break;
			default: break;
			}
		}
		int counter = 0;
		while(counter != 100) {
			for(int i = 0;i<color.length;i++) {
				if(color[i] > counter) {
					
				}else {
					return counter*4;
				}
			}
			counter++;
		}
		return 0;
	}
	
	public int getObjectiveCardSeven(int idplayer) {
		ArrayList<ArrayList<Object>> Column = new ArrayList<>();
		int counter = 0;
		for(int i = 1; i <= 4; i++) {
			Column = database.select("select DISTINCT p.position_x, p.position_y, p.diecolor from playerframefield p  where p.player_idplayer = " + idplayer +" AND position_y = " + i +" AND diecolor is not null;");
			if(Column.size() == 5) {
				counter++;
			}
		}
		return counter*6;
	}
	
	public int getObjectiveCardEight(int idplayer) {
		ArrayList<ArrayList<Object>> diagonal = database.select("select p.position_x, p.position_y, p.diecolor from playerframefield p  where p.player_idplayer = " + idplayer +";");
		int counter = 0;
		int pointTotal = 0;
		String currentColor = new String("null");
		for (int x = 1; x <= 2; x++) {
			for (int y = 2; y >= 1; y--) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
			}
		}
		counter = 0;
		currentColor = new String("null");
		// 2
		for (int x = 1; x <= 3; x++) {
			for (int y = 3; y >= 1; y--) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
			}
		}
		counter = 0;
		currentColor = new String("null");
		// 3
		for (int x = 1; x <= 5; x++) {
			for (int y = 4; y >= 1; y--) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
			}
		}
		
		counter = 0;
		currentColor = new String("null");
		// 4
		for (int x = 2; x <= 5; x++) {
			for (int y = 4; y >= 1; y--) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
			}
		}
		
		counter = 0;
		currentColor = new String("null");
		// 5
		for (int x = 3; x <= 5; x++) {
			for (int y = 4; y >= 2; y--) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
			}
		}
		counter = 0;
		currentColor = new String("null");
		// 6
				for (int x = 4; x <= 5; x++) {
					for (int y = 4; y >= 3; y--) {
						if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//							if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//								currentColor = (String)diagonal.get(counter).get(2);
//							}
							if(currentColor.equals((String)diagonal.get(counter).get(2))) {
								pointTotal++;
							}else{
								currentColor = (String)diagonal.get(counter).get(2);
							}
						}
							counter++;
					}
				} 

		counter = 0;
		currentColor = new String("null");
		// 7
		for (int x = 1; x <= 2; x++) {
			for (int y = 3; y <= 4; y++) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
					}
				}
		
		counter = 0;
		currentColor = new String("null");
		// 8
		for (int x = 1; x <= 3; x++) {
			for (int y = 2; y <= 4; y++) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
					}
				}
		
		counter = 0;
		currentColor = new String("null");
		// 9
		for (int x = 1; x <= 4; x++) {
			for (int y = 1; y <= 4; y++) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
					}
				}
		
		counter = 0;
		currentColor = new String("null");
		// 10
		for (int x = 2; x <= 5; x++) {
			for (int y = 1; y <= 4; y++) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
					}
				}
		
		counter = 0;
		currentColor = new String("null");
		// 11
		for (int x = 3; x <= 5; x++) {
			for (int y = 1; y <= 3; y++) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
					}
				}
		
		counter = 0;
		currentColor = new String("null");
		// 12
		for (int x = 4; x <= 5; x++) {
			for (int y = 1; y <= 2; y++) {
				if((int)diagonal.get(counter).get(0) == x && (int)diagonal.get(counter).get(1) == y) {
//					if(currentColor.equals("null") && !diagonal.get(counter).get(2).equals("null")) {
//						currentColor = (String)diagonal.get(counter).get(2);
//					}
					if(currentColor.equals((String)diagonal.get(counter).get(2))) {
						pointTotal++;
					}else{
						currentColor = (String)diagonal.get(counter).get(2);
					}
				}
					counter++;
					}
				}
		
		return 0;
	}
	
	public int getObjectiveCardNine() {
		int[] number = new int[2];
		for(PlacedDice pd : diceField) {
			switch(pd.getEyes()) {
			case 1: number[0] += number[0] + 1;
			break;
			case 2: number[1] += number[1] + 1;
			break;
			default: break;
			}
		}
		int counter = 0;
		while(counter != 100) {
			for(int i = 0;i<number.length;i++) {
				if(number[i] > counter) {
					
				}else {
					return counter*2;
				}
			}
			counter++;
		}
		return 0;
	}
	
	public int getObjectiveCardTen(int idplayer) {
		ArrayList<ArrayList<Object>> Column = new ArrayList<>();
		int counter = 0;
		for(int i = 1; i <= 4; i++) {
			Column = database.select("select DISTINCT p.position_x, p.position_y, d.eyes from playerframefield p join gamedie d on p.idgame = d.idgame and p.dienumber = d.dienumber and p.diecolor = d.diecolor where p.player_idplayer = " + idplayer +" AND position_y = " + i +";");
			if(Column.size() == 5) {
				counter++;
			}
		}
		return counter*5;
	}
	
	
	

	
	

}
