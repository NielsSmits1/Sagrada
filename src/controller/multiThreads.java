package controller;

import View.HomePane;

public class multiThreads extends Thread {
	private ChallengesController challenge;
	private HomePane home;
	private InlogController controller;
	private long refreshRate;
	private boolean run = true;
	

	public multiThreads(ChallengesController challenge, long refreshRate) {
		this.challenge = challenge;
		this.refreshRate = Math.max(refreshRate, 10000L);
	}

	@Override
	public void run() {
		System.out.println("IM RUNNING");
		runChallenges();
//		runChallenger();
	}
	
	public void runChallenges() {

			try {
				challenge.refresh();
				System.out.println("facking gay");
				Thread.sleep(50);
	
				
			} catch (InterruptedException e) {
				System.out.println("gay");
			}

		}


}
