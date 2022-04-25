package standoffKOTH;
public class NormalBot extends PlayerClass {
	public NormalBot()
	{
		super(6,1,3,0);
	}
	protected int makeMove()
	{
		if(getHP()<5)
		{
			return move('h',this);
		}
		else if (getAmmo()<4)
		{
			return move('r',this);
		}
		else if (getShotBy()!=null)
		{
			return move('s',getShotBy());
		}
		else if (getHP()<5)
		{
			return move('h',this);
		}
		else
		{
			return move('r',this);
		}
	}

}
