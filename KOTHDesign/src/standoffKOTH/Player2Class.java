package standoffKOTH;

import java.util.List;
import java.util.Random;

public class Player2Class extends PlayerClass {
	Random rand = new Random();
	public Player2Class()
	{
		super(9,0,0,1);
	}
	protected int makeMove()
	{
		List<PlayerClass> z = getAliveEnemies();
		if(getAmmo()>0) {return this.move('s',z.get(rand.nextInt(z.size())));}{return this.move('r',this);}
	}
}
