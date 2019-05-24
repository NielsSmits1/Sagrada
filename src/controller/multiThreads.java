package controller;

import View.HomePane;

public class multiThreads extends Thread {
	private ChallengesController challenge;
	private HomePane home;
	private InlogController controller;
	private long refreshRate;
	

	public multiThreads(ChallengesController challenge, long refreshRate) {
		this.challenge = challenge;
		this.refreshRate = Math.max(refreshRate, 10000L);
	}

	@Override
	public void run() {
		while (true) {
			try {
				challenge.refresh();
				System.out.println("facking gay");
				Thread.sleep(refreshRate);
			} catch (InterruptedException e) {
				System.out.println("gay");
			}

		}

	}

}
