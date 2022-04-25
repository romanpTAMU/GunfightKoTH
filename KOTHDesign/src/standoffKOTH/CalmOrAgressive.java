package standoffKOTH;

public class CalmOrAgressive extends PlayerClass {
	public CalmOrAgressive()
	{
		super(5,0,0,5);
	}
	protected int makeMove()
	{
		if(getAmmo()<2)
		{
			return move('r',this);
		}
		else if(getHP()<10&&getShotBy()!=null)
		{
			return move('s',getShotBy());
		}
		else
		{
			return move('s',getAliveEnemies().get(getAliveEnemies().size()-1));
		}
	}
}
