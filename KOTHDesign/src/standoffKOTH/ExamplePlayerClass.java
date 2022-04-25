package standoffKOTH;

public class ExamplePlayerClass extends PlayerClass {

	public ExamplePlayerClass()
	{
		super(0,0,0,0);//put stats here
	}
	protected int makeMove()
	{
		return this.move('h',this);//It doesn't matter who is target with non-shoot actions, so just self here
	}
}
