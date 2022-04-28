package standoffKOTH;

public class Destroyer extends PlayerClass {
	public Destroyer()
	{
		super(10,0,0,0);
	}
	protected int makeMove()
	{
		if(getHP()<2)
		{
			return move('h',this);
		}
		else if(getAmmo()<3)
		{
			return move('r',this);
		}
		else
		{
			return move('s',getAliveEnemies().get(0));
		}
	}
}
