package model;

import java.util.ArrayList;

import Database.Db;

public class Opponent {
	private int gamemode;
	private ArrayList<ArrayList<Space>> opponents;
	private ArrayList<ArrayList<Object>> p;
	private ArrayList<Integer> idOpponent;
	private Db database;
	private int idgame;
	private int yourself;

	public Opponent(int idgame, int ownId) {
		database = new Db();
		this.idgame = idgame;
		yourself = ownId;
		idOpponent = new ArrayList<>();
		// TODO REMOVE
		setOpponents();
		setGamemode();
		opponents = new ArrayList<>();
		p = getSelect();
		setPatternField();
		addCard();

	}

	// TODO MAKE THIS AUTOMATIC, CURRENTLY THIS IS HARD CODED FOR TESTING SCENARIOS
	private void setOpponents() {
		for (int i = 0; i < 3; i++) {
			ArrayList<ArrayList<Object>> opponent = database.select(
					"SELECT (idplayer+1) AS newPlayerId FROM tjpmsalt_db2.player ORDER BY idplayer DESC LIMIT 1;");
			long id = (long) opponent.get(0).get(0);
			idOpponent.add((int) id);
			database.cud(
					"INSERT INTO tjpmsalt_db2.player (idplayer, username, game_idgame, playstatus_playstatus, isCurrentPlayer, private_objectivecard_color, patterncard_idpatterncard) "
							+ "VALUES(" + opponent.get(0).get(0) + ",'niels'," + idgame
							+ ", 'geaccepteerd', 0, 'blauw', 5)");
		}

	}

	public void setGamemode() {
		ArrayList<ArrayList<Object>> opponents = database.select(
				"SELECT idplayer FROM player WHERE idplayer != " + yourself + " AND game_idgame = " + idgame + ";");
		gamemode = opponents.size();
	}

	public ArrayList<ArrayList<Object>> getSelect() {
		return database.select("SELECT * FROM patterncardfield WHERE patterncard_idpatterncard = " + 5 + ";");
	}

	public void addCard() {
		for (int j = 0; j < gamemode; j++) {
			for (int i = 0; i < opponents.get(j).size(); i++) {
				addChosenCard(opponents.get(j).get(i).getXPos(), opponents.get(j).get(i).getYPos(), idOpponent.get(j));
			}
		}
	}

	public void addChosenCard(int xPos, int yPos, int currentOpponent) {
		database.cud(
				"insert into tjpmsalt_db2.playerframefield (player_idplayer, position_x,position_y, idgame) VALUES ("
						+ currentOpponent + "," + xPos + "," + yPos + "," + idgame + ");");
	}

	private void setPatternField() {
		for (int j = 0; j < gamemode; j++) {
			opponents.add(new ArrayList<Space>());
			for (int i = 0; i < 20; i++) {
				opponents.get(j).add(new Space());
				opponents.get(j).get(i).setXPOS((int) p.get(i).get(1));
				opponents.get(j).get(i).setYPOS((int) p.get(i).get(2));
				/// *
				// The color might be null, in that case the color will be set the white.
				/// **
				if ((String) p.get(i).get(3) == null) {
					opponents.get(j).get(i).setColor("wit");
				} else {
					opponents.get(j).get(i).setColor((String) p.get(i).get(3));
				}

				if (p.get(i).get(4) != null) {
					opponents.get(j).get(i).setEyes((int) p.get(i).get(4));
				}
			}
		}
	}

	public ArrayList<ArrayList<Space>> getOpponents() {
		return opponents;
	}

}
