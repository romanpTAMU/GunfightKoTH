package standoffKOTH;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class PlayerClass implements Comparable<PlayerClass> {
	protected int hp;
	protected int maxHP;
	protected double dex;
	protected double armor;
	protected int speed;
	protected int ammo;
	protected List<PlayerClass> playerList;
	protected PlayerClass lastShotBy;
	protected PlayerClass lastShotAt;
	protected Random rand = new Random();
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
	public int getSpeed()
	{
		return speed;
	}
	protected void reload()
	{
		ammo+=2;
	}
	protected void heal()
	{
		if(hp<maxHP){hp++;}
	}
	protected List<PlayerClass> getAliveEnemies()
	{
		List<PlayerClass> z = (List<PlayerClass>) this.getInfo().get(6);
		List<PlayerClass> out = new ArrayList<PlayerClass>();
		for (PlayerClass p:z)
		{
			if(!p.deadQ()&&p!=this) {out.add(p);}
		}
		return out;
	}
	protected int shoot(PlayerClass p)
	{
		if(ammo>0) {ammo--;lastShotAt=p;return p.takeDamage(this);} else {return 0;}
	}
	public int takeDamage(PlayerClass p)
	{
		lastShotBy = p;
		int dmg;
		double b = rand.nextDouble();
		if(b<dex) {dmg=0;}else{dmg=rand.nextInt(3)+1;}
		if(rand.nextDouble()<armor) {hp-=(dmg-1);}{hp-=dmg;}
		return dmg;
		}
	public int move(char action, PlayerClass p)
	{
		if(hp>0) {if(action =='r') {reload();return ammo;} else {if(action=='h') {heal();return hp;} else if(action=='s') {return shoot(p);}}}
		return 0;
	}
	public int makeMove()
	{
		reload();return ammo;
	}
	public List<Object> getInfo()
	{
		List<Object> out = new ArrayList<Object>();
		out.add(hp);out.add(ammo);out.add(maxHP);out.add(dex);out.add(armor);out.add(speed);out.add(playerList);out.add(lastShotAt);out.add(lastShotBy);
		return out;
	}
}
