package standoffKOTH;

public class WinnerBot extends PlayerClass {
	public WinnerBot()
	{
		super(5,4,1,0);
	}
	public int makeMove()
	{
		if(getHP()<4)
		{
			return move('h',this);
		}
		else if (getAmmo()<1)
		{
			return move('r',this);
		}
		else if(getShotBy()!=null)
		{
			return move('s',getShotBy());
		}
		else
		{
			return 0;
		}
	}
}
