package standoffKOTH;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PlayerClass implements Comparable<PlayerClass> {
	private int hp;
	private int maxHP;
	private double dex;
	private double armor;
	private int speed;
	private int ammo;
	private List<PlayerClass> playerList;
	private PlayerClass lastShotBy;
	private PlayerClass lastShotAt;
	private Random rand = new Random();
	public PlayerClass()
	{
		
	}
	public PlayerClass(int extraHP, int extraDex, int extraarmor, int extraSpeed)
	{
		if(extraHP+extraDex+extraarmor+extraSpeed>10){hp=0;dex=0;armor=0;speed=0;}{maxHP = 10 + extraHP;hp=10+extraHP;dex=extraDex*.04;armor = .1*extraarmor;speed=extraSpeed;}
		ammo = 2;}
	@Override
	public int compareTo(PlayerClass p)
	{
		int otherSpeed = p.getSpeed();
		return otherSpeed-speed;
	}
	public boolean deadQ()
	{
		return hp<1;
	}
	public void setPlayerList(List<PlayerClass> pl)
	{
		playerList = pl;
	}
	protected List<PlayerClass> getPlayers()
	{
		return playerList;
	}
	public int getSpeed()
	{
		return speed;
	}
	protected int getAmmo()
	{
		return ammo;
	}
	protected int getHP()
	{
		return hp;
	}
	protected int getMaxHP()
	{
		return maxHP;
	}
	protected double getDex()
	{
		return dex;
	}
	protected double getArmor()
	{
		return armor;
	}
	
	private void reload()
	{
		ammo+=2;
	}
	private void heal()
	{
		if(hp<maxHP){hp++;}
	}
	protected List<PlayerClass> getAliveEnemies()
	{
		List<PlayerClass> z = (List<PlayerClass>) this.getPlayers();
		List<PlayerClass> out = new ArrayList<PlayerClass>();
		for (PlayerClass p:z)
		{
			if(!p.deadQ()&&p!=this) {out.add(p);}
		}
		return out;
	}
	private int shoot(PlayerClass p)
	{
		if(ammo>0) {ammo--;lastShotAt=p;return p.takeDamage(this);} else {return 0;}
	}
	public int takeDamage(PlayerClass p)
	{
		lastShotBy = p;
		int dmg;
		double b = rand.nextDouble();
		if(b<dex) {dmg=0;}else{dmg=rand.nextInt(3)+1;}
		if(rand.nextDouble()<armor) {dmg--;}hp-=dmg;
		return dmg;
		}
	protected int move(char action, PlayerClass p)
	{
		if(hp>0) {if(action =='r') {reload();return ammo;} else {if(action=='h') {heal();return hp;} else if(action=='s') {return shoot(p);}}}
		return 0;
	}
	protected int makeMove()
	{
		return ammo;
	}
	protected PlayerClass getShotAt()
	{
		return lastShotAt;
	}
	protected PlayerClass getShotBy()
	{
		return lastShotBy;
	}
}
