package controller;

public class multiThreads extends Thread implements Runnable{
	private ChallengesController challengeS;
	private ChallengerController challengeR;
	private HomeController home;
	private InlogController controller;
	private long refreshRate;
	private boolean run = true;
	
	public multiThreads(long refreshRate, ChallengesController cc, ChallengerController ccr) {
		this.challengeR = ccr;
		this.challengeS = cc;
		this.refreshRate = Math.max(refreshRate, 10000L);
	}
	@Override
	public void run() {
		System.out.println("IM RUNNING");
		runChallenges();
		runChallenger();
	}
	
	public void runChallenges() {

			try {
				this.challengeS.refresh();
				System.out.println("facking gay");
				Thread.sleep(50);
	
				
			} catch (InterruptedException e) {
				System.out.println("gay");
			}
			
		}
		
	
	public void runChallenger() {

			try {
				this.challengeR.refresh();
				System.out.println("facking gay2");
				Thread.sleep(refreshRate);

			} catch (InterruptedException e) {
				System.out.println("gay2");
			}
			
		}
		
	
	


}
