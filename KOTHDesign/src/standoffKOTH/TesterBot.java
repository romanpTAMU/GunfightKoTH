package standoffKOTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TesterBot extends PlayerClass {
	private boolean debug = false;
	public TesterBot()
	{
		super(8,0,2,0);
	}
	Random rand = new Random();
	private Set<PlayerClass> enemies = new LinkedHashSet<PlayerClass>();
	private List<PlayerClass> lastRoundEnemies = new ArrayList<PlayerClass>();
	private List<PlayerClass> targets = new ArrayList<PlayerClass>();
	private List<PlayerClass> recentTargets = new ArrayList<PlayerClass>();
	private int memoryCounter = 0;
	private int targetTurnCounter = 0;
	private int oneEnemyTracker = 0;
	private boolean readyToFire = false;
	private boolean killSwitch = false;
	private PlayerClass secretTarget;
	protected int makeMove()
	{
		if (getAmmo()>20&&killSwitch==false) {killSwitch = true;}
		if(!deadQ()) {if(debug) {System.out.print("Last round I was shot by: ");System.out.println(getWhoShotYou());System.out.print("HP: ");System.out.println(getHP());System.out.print("Ammo: ");System.out.println(getAmmo());}}
		if(secretTarget == null||secretTarget.deadQ()&&!deadQ())
		{
			List<PlayerClass> enemylist = new ArrayList<PlayerClass>(enemies);
			List<PlayerClass> aliveenemylist = new ArrayList<PlayerClass>();
			for (PlayerClass p : enemylist)
			{
				if (!p.deadQ())
				{
					aliveenemylist.add(p);
				}
			}
			if (aliveenemylist.size()>0)
			{
				killSwitch = false;
				secretTarget= aliveenemylist.get(rand.nextInt(aliveenemylist.size()));
				if(debug) {System.out.print("New secret target: ");System.out.println(secretTarget);}
			}
			else {killSwitch = false;
			secretTarget= getAliveEnemies().get(rand.nextInt(getAliveEnemies().size()));
			if(debug) {System.out.print("New secret target: ");System.out.println(secretTarget);}}targetTurnCounter=0;
		}targetTurnCounter++;
		if (memoryCounter>4)
		{
			memoryCounter = 0;
			recentTargets.clear();
		}
		else
		{
			memoryCounter++;
		}
		targets.clear();
		enemies.addAll(getWhoShotYou());
		for(PlayerClass p : getWhoShotYou())
		{
			if (lastRoundEnemies.contains(p))
			{
				targets.add(p);
				recentTargets.add(p);
			}
		}
		if(deadQ())
		{
			return move('h',this);
		}
		else if(getAmmo()<1)
		{
			killSwitch = false;
			if(debug) {System.out.println("Out of ammo: Reloading");}
			lastRoundEnemies =  getWhoShotYou();return move('r',this);
		}
		else if (getAliveEnemies().size()==1)
		{
			if (oneEnemyTracker==0)
			{
				if(debug) {System.out.print("Test them with a shot: shooting");System.out.println(getAliveEnemies().get(0));}
				oneEnemyTracker=1;lastRoundEnemies =  getWhoShotYou();return move('s',getAliveEnemies().get(0));
			}
			else if (oneEnemyTracker==1)
			{
				oneEnemyTracker=2;
				if(getHP()<getMaxHP())
				{
					if(debug) {System.out.println("Heal up and see their response: healing");}lastRoundEnemies =  getWhoShotYou();return move('h',this);
				}
				else
				{
					if(debug) {System.out.println("Just wait and reload: reloading");}lastRoundEnemies =  getWhoShotYou();return move('r',this);
				}
			}
			else if ((oneEnemyTracker==2&&getWhoShotYou().size()!=0)||oneEnemyTracker==-1)
			{
					if(oneEnemyTracker>0) {if(debug) {System.out.print("They are aggresive. ");}};oneEnemyTracker=-1;
						if(debug) {System.out.print("Fight them the best we can: shooting ");}System.out.println(getAliveEnemies().get(0));lastRoundEnemies =  getWhoShotYou();return move('s',getAliveEnemies().get(0));
			}
				else
				{
					oneEnemyTracker=5;
					if(getHP()<12||readyToFire)
					{
						if(debug) {System.out.println("One defensive enemy left: healing");}
						lastRoundEnemies = getWhoShotYou();return move('h',this);
					}
					else if(getAmmo()<22||readyToFire)
					{
						if(debug) {System.out.println("One defensive enemy left: reloading");}
						lastRoundEnemies = getWhoShotYou();return move('r',this);
					}
					else
					{
						if(debug) {System.out.println("Got enough ammo to take them out: SHOOOOOOT");}
						readyToFire = true;
						lastRoundEnemies = getWhoShotYou();return move('s',getAliveEnemies().get(0));
					}
				}
			}
		else if(targets.size()>0)
		{
			List<PlayerClass> legitTargets = new ArrayList<PlayerClass>();
			for (PlayerClass i:targets)
			{
				if(!i.deadQ())
				{
					legitTargets.add(i);
				}
			}
			if(legitTargets.size()!=0)
			{
				if(debug) {Collections.shuffle(legitTargets);System.out.print("Target found: shooting ");System.out.println(legitTargets.get(0));}
				lastRoundEnemies =  getWhoShotYou();return move('s',legitTargets.get(0));
			}
			else {
				{
					if(getHP()<10)
					{
						if(debug) {System.out.println("HP not great, all targets dead: healing");}
						lastRoundEnemies =  getWhoShotYou();return move('h',this);
					}
					else
					{
						if(debug) {System.out.println("all targets dead: reloading");}
						lastRoundEnemies =  getWhoShotYou();return move('r',this);
					}
				}
			}
		}
		else if(getWhoShotYou().size()!=0)
		{
			List<PlayerClass> enemiesShooting = new ArrayList<PlayerClass>();
			for (PlayerClass i:getWhoShotYou())
			{
				if(secretTarget.equals(i))
				{
					if(debug) {System.out.print("Secret target shot me: shooting ");System.out.println(i);}lastRoundEnemies = getWhoShotYou();return move('s',i);
				}
				else if (enemies.contains(i))
				{
					enemiesShooting.add(i);
				}
			}
			PlayerClass v;
			if(enemiesShooting.size()!=0) {v = enemiesShooting.get(rand.nextInt(enemiesShooting.size()));}
			else{v = getWhoShotYou().get(rand.nextInt(getWhoShotYou().size()));}
			if(debug) {System.out.print("Under attack: shooting ");System.out.println(v);}
			lastRoundEnemies = getWhoShotYou();return move('s',v);
		}
		else if((getHP()<8&&getWhoShotYou().size()==0&&lastRoundEnemies.size()==0)||(getHP()<12&&getAliveEnemies().size()<3))
		{
			if(debug) {System.out.println("Need better HP and not getting shot: healing");}
			lastRoundEnemies =  getWhoShotYou();return move('h',this);
		}
		else if(enemies.contains(secretTarget)&&targets.size()==0&&!secretTarget.deadQ()&&targetTurnCounter>4)
		{
			if(debug) {System.out.print("Secret target alive and my enemy: shooting ");System.out.println(secretTarget);}
			lastRoundEnemies =  getWhoShotYou();return move('s',secretTarget);
		}
		else if(getAmmo()<7&&!killSwitch)
		{
			if(debug) {System.out.println("Stockpiling ammo: reloading");}
			lastRoundEnemies =  getWhoShotYou();return move('r',this);
		}
		else if(getHP()<12&&!killSwitch)
		{
			if(debug) {System.out.println("Not great HP: healing");}
			lastRoundEnemies =  getWhoShotYou();return move('h',this);
		}

		else if(recentTargets.size()>0)
		{
			List<PlayerClass> enemiesShooting = new ArrayList<PlayerClass>();
			for (PlayerClass i:recentTargets)
			{
				if (!i.deadQ())
				{
					enemiesShooting.add(i);
				}
			}
			PlayerClass v;
			if(enemiesShooting.size()!=0) {v = enemiesShooting.get(rand.nextInt(enemiesShooting.size()));if(debug) {System.out.print("Recent target remains: shooting ");}}
			else{v=null;if(debug) {System.out.println("No recent targets alive: healing");}lastRoundEnemies = getWhoShotYou();return move('h',this);}
			if(debug) {System.out.println(v);}
			lastRoundEnemies = getWhoShotYou();return move('s',v);
		}
		else if (getHP()<getMaxHP()&&!killSwitch)
		{
			if(debug) {System.out.println("Not full HP: healing");}
			lastRoundEnemies =  getWhoShotYou();return move('h',this);
		}
		else if (getAmmo()<20&&!killSwitch)
		{
			if(debug) {System.out.println("Stockpiling even more ammo: reloading");}
			lastRoundEnemies =  getWhoShotYou();return move('r',this);
		}
		else if(!secretTarget.deadQ())
		{
			if(debug) {System.out.print("Secret target alive: shooting ");System.out.println(secretTarget);}
			lastRoundEnemies =  getWhoShotYou();return move('s',secretTarget);
		}
		else
		{
			PlayerClass t = getAliveEnemies().get(rand.nextInt(getAliveEnemies().size()));
			if(debug) {System.out.print("Fail case: shooting at random ");System.out.println(t);}
			lastRoundEnemies =  getWhoShotYou();return move('s',t);
		}
	
	}
}
