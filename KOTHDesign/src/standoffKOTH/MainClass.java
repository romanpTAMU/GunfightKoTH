//I'm going to try to comment this thing, so wish me luck!
package standoffKOTH;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class MainClass {
	public static void main(String[] args) throws InterruptedException {
		int round = 0; //Counter, keeps track of the round
		int ties = 0; //Mostly for debugging, counts up whenever a round goes to time.
		boolean debug = false; //Turns on death/win messages.
		HashMap<Class<? extends PlayerClass>,Integer> scoreSaver = new HashMap<Class<? extends PlayerClass>,Integer>();
		/*Keeps track of score. Only works if all bots are different classes, but that should always be true.
		 Probably my seventh method of score tracking, but I think this one works and lets me randomize.
		 */
		PlayerClass[] initialBots = new PlayerClass[]{
				new Dexter(),
				new SmartBot(),
				new WinnerBot(),
				new HpKing(),
				new Tank(),
				new FriendlyNotFriendlyBot(),
				new RageBot3(),
				new NormalBot(),
				new Destroyer(),
				new OneVsOneMeBro(),
				new ChargeBot(),
				new FriendlyAndHealthyBot(),
				new WhatABot()
		}; //Define a list of all the bots.
		for (PlayerClass j:initialBots) {
			scoreSaver.put(j.getClass(), 0);
		} //Fill in the keys of the HashMap with each bot's class and a starting score of 0
		while (round<1000000) //edit round number here
		{
			int turnCycles = 0; //count how many times each bot has gone
			PlayerClass[] bots =new PlayerClass[]{
					new Dexter(),
					new SmartBot(),
					new WinnerBot(),
					new HpKing(),
					new Tank(),
					new FriendlyNotFriendlyBot(),
					new RageBot3(),
					new NormalBot(),
					new Destroyer(),
					new OneVsOneMeBro(),
					new ChargeBot(),
					new FriendlyAndHealthyBot(),
					new WhatABot()
			}; //Initialize the bots for the fight. Can't use the ones from above because they need to reset.
			List<PlayerClass> players = new ArrayList<PlayerClass>(); //Get players in list form.
			List<Boolean> botsDead = new ArrayList<Boolean>(); //Who's dead?
			for (PlayerClass z:bots) {
				botsDead.add(false);
				players.add(z);
			}//Get the lists set up
			boolean roundGoing = true;
			Collections.shuffle(players);
			Collections.sort(players);
			for (int i = 0;i<players.size();i++)
			{
				PlayerClass p = players.get(i);
				p.setPlayerList(players);
			}
		while(roundGoing) //Turn code starts here
		{
			for (PlayerClass p:bots)
			{
				p.makeMove(); //Call each bot, see what it wants to do
				for (int v=0;v<bots.length;v++) //Score-related checks.
				{
					PlayerClass vBot = players.get(v);
					if(vBot.deadQ()&&!botsDead.get(v)) //If you killed a bot
					{
						botsDead.set(v, true); //Make them dead
						for(PlayerClass q:bots)
						{
							if(q.deadQ()&&q!=vBot) //Then they get a point for everyone else who's dead.
							{scoreSaver.put(vBot.getClass(),scoreSaver.get(vBot.getClass())+1);}
						}
						if(debug) {System.out.print(vBot);System.out.print(" is DEAD, killed by ");System.out.print(p);System.out.print(" on turn cycle ");System.out.println(turnCycles);}
					}
				}
				if(p.getAliveEnemies().size()==0){if(debug) {System.out.print(p);System.out.println(" wins the round!"); }
				roundGoing=false;break;}//End the round if one player left.
			}
			turnCycles++;
			if(turnCycles>250) //End the round if 250 turn cycles done.
			{
				ties++;
				round++;
				if(debug) {System.out.println("Tied round!");}
				break;
			}
	}
		for(int n=0;n<bots.length;n++)
		{
			PlayerClass nBot = players.get(n);
			if(!nBot.deadQ())//If at the end of the round you are alive
			{
				scoreSaver.put(nBot.getClass(),scoreSaver.get(nBot.getClass())+1+players.size());
				/*
				 * Get 3 points (1 for each bot other than yourself, 2 for winning the round)
				 */
				}
			}
		
		round++; //Up the round
		System.out.println(round);
		System.out.println();
		for(int r=0;r<bots.length;r++)
		{
			System.out.println(players.get(r));
			System.out.println((double)scoreSaver.get(players.get(r).getClass())/round); //Display score.
		}}
		System.out.println(ties); //Show ties at the end
		}}
