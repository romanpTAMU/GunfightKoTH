package standoffKOTH;

import java.util.Random;

public class RageBot2 extends PlayerClass {
	Random rand = new Random();
	public RageBot2()
	{
		super(8,1,1,0);
	}
	protected int makeMove()
	{
		if (getHP()<3)
		{
			return move('h',this);
		}
		else if (getHP()<10&&getAmmo()>0&&getShotBy()!=null&&!getShotBy().deadQ())
		{
			return move('s',getShotBy());
		}
		else if (getAmmo()<6)
		{
			return move('r',this);
		}
		else if (getHP()<8)
		{
			return move('h',this);
		}
		else if (getShotBy()!=null&&getAmmo()>0&&!getShotBy().deadQ())
		{
			return move('s',getShotBy());
		}
		else if (getAmmo()<1)
		{
			return move('r',this);
		}
		else
		{
			return move('s',getAliveEnemies().get(rand.nextInt(getAliveEnemies().size())));
		}
	}
}
