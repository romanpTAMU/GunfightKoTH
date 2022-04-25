package standoffKOTH;

import java.util.Random;

public class RageBot3 extends PlayerClass {
	Random rand = new Random();
	PlayerClass target = null;
	public RageBot3()
	{
		super(8,1,1,0);
	}
	protected int makeMove()
	{
		if(target==null||target.deadQ()) {
			for(PlayerClass p:getAliveEnemies())
			{
				if(p instanceof SmartBot&&getAliveEnemies().size()==1)
				{
					target=p;
					break;
				}
			}
		}
		if(target==null||target.deadQ()) {
			if(getShotBy()!=null)
			{
				target = getShotBy();
			}
			else{target = getAliveEnemies().get(rand.nextInt(getAliveEnemies().size()));}
		}
		if(getHP()<3)
		{
			return move('h',this); 
		}
		else if (getHP()<10&&getAmmo()>0)
		{
			return move('s',target);
		}
		else if (getAmmo()<6)
		{
			return move('r',this);
		}
		else if (getHP()<5)
		{
			return move('h',this);
		}
		else if (getAmmo()>0)
		{
			return move('s',target);
		}
		else
		{
			return move('r',this);
		}
	}
}
