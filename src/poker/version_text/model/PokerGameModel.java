package poker.version_text.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

import poker.version_text.PokerGame;

public class PokerGameModel {
	private final ArrayList<Player> players = new ArrayList<>();
	private DeckOfCards deck;
	private int count;
	Timer timer = new Timer();
	TimerTask closeProgramm = new TimerTask() {
	public void run() {
	    System.exit(0);
	    }
	};
	
	public PokerGameModel() {
	
		Scanner userName = new Scanner(System.in);//Here we tried to add a scanner for changing the players name
		System.out.println("Enter the number of players");
		while(!userName.hasNextInt()) {

			count++;
			if(count == 3 ) {
				System.out.println("Programm will close itself due to three invalid inputs in 10 seconds");
				timer.schedule(closeProgramm, new Date(System.currentTimeMillis()+10*1000));
				
				
			
			} else {
				System.out.println("Input is not a number, try again");
			}
			
			userName.nextLine();
			

		
		}
		
		PokerGame.NUM_PLAYERS = userName.nextInt();//number of players
		System.out.println("Enter your GamerTag, click enter, and repeat this until all players have a GamerTag");
		for(int i=0 ; i < PokerGame.NUM_PLAYERS ; i++) { //inserting the names
		
		players.add(new Player(userName.next()));
			 
			 }
		
		deck = new DeckOfCards();
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public DeckOfCards getDeck() {
		return deck;
	}
	
}
