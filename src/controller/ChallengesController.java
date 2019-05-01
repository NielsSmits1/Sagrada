package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Challenge;

public class ChallengesController {

	private Challenge challenge;
	private HomeController home;
	private PlayerController self;

	public ChallengesController(HomeController home) {
		this.home = home;
		challenge = new Challenge();

	}

	public void acceptChallenge() {
		challenge.changePlayerStatusToAccepted();
	}

	public void declineChallenge() {
		challenge.changePlayerStatusToDeclined();
	}

//	public void checkChallengedPlayer() {
//		challenge.GetPlayerWithChallengedStatus();
//		
//		System.out.println(challenge.GetPlayerWithChallengedStatus());
//	}
	public void setChallenges() {
		home.buildPlayer("Teun");
	}

	public ArrayList<String> getChallengers() {
		ArrayList<String> challengedPlayerNames = new ArrayList<String>();
		String u;
		self = new PlayerController(home.getUsername());
		home = new HomeController(self);

		for (ArrayList<Object> a : challenge.GetPlayerWithChallengedStatus(home.getUsername())) {
			u = (String) a.get(0);
			if (!home.getUsername().equals(u)) {
				challengedPlayerNames.add(u);
			} else {
//				System.out.println("Checking urself?");
				continue;
				

			}

		}
		return challengedPlayerNames;

//		for(ArrayList<Object> a: challenge.GetPlayerWithChallengedStatus()){
//		a.get(0);

		/*
		 * 
		 * bouw instantie homecontrooller vul player in homecontroller met die mij heeft
		 * uitgedaagd gebruik die playerinstantie als parameter bij het ophalen van de
		 * uitgedaagde haal de lijst op uit challenge 
		 * loop daar doorheen
		 * cast het naar string 
		 * x voeg het toe aan arraylist return Arraylist
		 */

	}
	public HashMap<String, String> getChallenged() {
		HashMap<String, String> challengedPlayerNames = new HashMap<String, String>();
		String u;
		String s;
		self = new PlayerController(home.getUsername());
		home = new HomeController(self);

		for (ArrayList<Object> a : challenge.GetPlayerWithChallengeeStatus(home.getUsername())) {
			u = (String) a.get(1);
			s = (String) a.get(3);
			if (!home.getUsername().equals(u)) {
				challengedPlayerNames.put(u, s);
			} else {
//				System.out.println("Checking urself?");
				continue;
				

			}

		}
		return challengedPlayerNames;
	}
}
