package standoffKOTH;
import java.util.Random;
public class RageBot extends PlayerClass {
	Random rand = new Random();
	public RageBot()
	{
		super(10,0,0,0);
	}
	protected int makeMove()
	{
		if(getHP()<5&&getShotAt()!=null)
		{
			return move('s',getShotAt());
		}
		else if (getHP()>4)
		{
			return move('r',this);
		}
		else
		{
			return move('s',getAliveEnemies().get(rand.nextInt(getAliveEnemies().size())));
		}
	}
}
