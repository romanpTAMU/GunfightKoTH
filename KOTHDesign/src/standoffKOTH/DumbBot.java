package standoffKOTH;

import java.util.Random;

public class DumbBot extends PlayerClass {
	Random rand = new Random();
	PlayerClass target = null;
	public DumbBot()
	{
		super(10,0,0,0);
	}
	protected int makeMove()
	{
		return 0;
	}
	

}
