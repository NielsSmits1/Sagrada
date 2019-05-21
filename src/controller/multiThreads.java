package controller;

public class multiThreads implements Runnable {
	private ChallengesController challenge;
	private HomeController home;
	private InlogController controller;
	private long refreshRate;
	
	public multiThreads(InlogController inlog, long refreshRate) {
		this.controller = inlog;
		this.challenge = new ChallengesController(home);
		this.refreshRate = Math.max(refreshRate, 2000);
		run();
	}
	
	@Override
	public void run() {
		while (true) {
			challenge.refresh();
			System.out.println("facking gay");
		}
		
	}

}
