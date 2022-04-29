package standoffKOTH;

public class ChargeBot extends PlayerClass {
	public ChargeBot()
	{
		super(5,0,0,5);
	}
	protected int makeMove()
	{
		if(getAmmo()<1)
		{
			return move('r',this);
		}
		else if(getHP()<7)
		{
			return move('h',this);
		}
		else
		{
			return move('s',getAliveEnemies().get(getAliveEnemies().size()-1));
		}
	}
}
