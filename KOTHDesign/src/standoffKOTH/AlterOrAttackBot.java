package standoffKOTH;
import java.util.Random;

public class AlterOrAttackBot extends PlayerClass {
	int ticks = 0;
	int ticker = 0;
	Random random = new Random();
	public AlterOrAttackBot()
	{
		super(4,3,0,3);
	}
	protected int makeMove()
	{
		if (getHP()<3)
		{
			if (getAmmo()<1)
			{
				return move('r',this);
			}
			else if (getShotAt()!=null)
			{
				return move('s',getShotAt());
			}
			else
			{
				return move('s',getAliveEnemies().get(random.nextInt(getAliveEnemies().size())));
			}
		}
		else
		{
			if (ticks%3==0)
			{
				ticker++;
				if(ticker!=0) {return move('s',getAliveEnemies().get((ticker)%getAliveEnemies().size()));}else{return move('s',getAliveEnemies().get(getAliveEnemies().size()-1));}
			}
			else if (ticks%3==1)
			{
				return move('r',this);
			}
			else
			{
				return move('h',this);
			}
		}
	}
}
