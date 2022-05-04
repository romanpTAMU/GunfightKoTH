package standoffKOTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PlayerClass implements Comparable<PlayerClass> {
	private int hp;
	private int maxHP;
	private double dex;
	private double armor;
	private int speed;
	private int ammo;
	private List<PlayerClass> playerList;
	private List<PlayerClass> shotYouList;
	private PlayerClass lastShotBy;
	private PlayerClass lastShotAt;
	private Random rand = new Random();
	public PlayerClass()
	{
		
	}
	public PlayerClass(int extraHP, int extraDex, int extraarmor, int extraSpeed)
	{
		Logger logger
        = Logger.getLogger(
            PlayerClass.class.getName());logger.setLevel(Level.WARNING);
		if(extraHP+extraDex+extraarmor+extraSpeed>10||extraHP<0||extraDex<0||extraarmor<0||extraSpeed<0)
		{hp=0;dex=0;armor=0;speed=0;logger.warning("INVALID STATS");}
		else {maxHP = 10 + extraHP;hp=10+extraHP;dex=extraDex*.04;armor = .1*extraarmor;speed=extraSpeed;}
		ammo = 2;}//set up values, make sure nothing goes wrong
	@Override
	public int compareTo(PlayerClass p)
	{
		int otherSpeed = p.getSpeed();
		return otherSpeed-speed;
	} //to sort the players
	public boolean deadQ()
	{
		return hp<1;
	}//to see who's left alive
	public void setPlayerList(List<PlayerClass> pl)
	{
		playerList = pl;
	}//random setup function
	protected List<PlayerClass> getPlayers()
	{
		return playerList;
	}//getter for the PlayerList (which nobody uses)
	public int getSpeed()
	{
		return speed;
	}//get the speed, is public.
	protected int getAmmo()
	{
		return ammo;
	}//getter for ammo (actually quite useful)
	protected int getHP()
	{
		return hp;
	}//find your hp
	protected int getMaxHP()
	{
		return maxHP;
	}//find your max HP. Not many bots use it yet because they just hardcode the value, but maybe has uses?
	protected double getDex()
	{
		return dex;
	}//find dex. Pretty useless because it doesn't change. Maybe if you had random stats you would use it.
	protected double getArmor()
	{
		return armor;
	}//find armor. Same situation as dex.
	private void reload()
	{
		ammo+=2;
	}//Get more ammo.
	private void heal()
	{
		if(hp<maxHP){hp++;}
	}//Gain an HP.
	protected List<PlayerClass> getAliveEnemies()
	{
		List<PlayerClass> z = (List<PlayerClass>) this.getPlayers();
		List<PlayerClass> out = new ArrayList<PlayerClass>();
		for (PlayerClass p:z)
		{
			if(!p.deadQ()&&p!=this) {out.add(p);}//If they are alive and not you, add to list.
		}
		return out;
	}//Find out who's left alive. Very useful.
	protected List<PlayerClass> getWhoShotYou()
	{
		Collections.shuffle(shotYouList);
		return shotYouList;
	}
	private int shoot(PlayerClass p)
	{
		if(ammo>0) {ammo--;lastShotAt=p;return p.takeDamage(this);} else {return 0;}
	}//shoot someone
	public int takeDamage(PlayerClass p)
	{
		lastShotBy = p;
		int dmg;
		double b = rand.nextDouble();
		if(b<dex) {dmg=0;}else{dmg=rand.nextInt(3)+1;}//Check to see if dodged.
		if(rand.nextDouble()<armor) {if(dmg!=0) {dmg--;}}hp-=dmg;//Check for armor.
		if(dmg>0){shotYouList.add(p);}
		return dmg;
		}//Get hit by someone.
	protected int move(char action, PlayerClass p)
	{
		shotYouList.clear();
		if(hp>0) {
			if(action =='r')
			{reload();return ammo;}
			else if(action=='h')
			{heal();return hp;}
			else if(action=='s')
			{return shoot(p);}}
		return 0;
	}//Perform your action.
	protected int makeMove()
	{
		return ammo;
	}//Default function, gets overwritten.
	protected PlayerClass getShotAt()
	{
		return lastShotAt;
	}//Get the person you shot last round.
	protected PlayerClass getShotBy()
	{
		return lastShotBy;
	}//Get the person who most recently shot you last round.
}
