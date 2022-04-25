package standoffKOTH;

import java.util.Random;

public class DumbBot extends PlayerClass {
	Random rand = new Random();
	PlayerClass target = null;
	public DumbBot()
	{
		super(0,0,10,0);
	}
	protected int makeMove()
	{
		for (PlayerClass p:getAliveEnemies())
		{
			if(p instanceof RageBot3&&!p.deadQ()) {target = p;}
		}
		if (target == null||target.deadQ()) {target = getAliveEnemies().get(rand.nextInt(getAliveEnemies().size()));}
		if(getAmmo()<1) {return move('r',this);}
		else
		{
			return move('s',target);
		}
	}
	

}
