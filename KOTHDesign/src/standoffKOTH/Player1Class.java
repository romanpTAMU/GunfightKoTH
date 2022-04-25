package standoffKOTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player1Class extends PlayerClass {

	public Player1Class()
	{
		super(0,8,1,1);
	}
	protected int makeMove()
	{
		List<PlayerClass> g = new ArrayList<PlayerClass>();g= this.getAliveEnemies();
		if((int)getHP()<6) 
		{return this.move('h', this);} 
		else {
			if (getAmmo()<1)
			{return this.move('r',this);}
			else {
			if(getShotBy()!=null&& g.contains(getShotBy())) {
				if(getAmmo()>0){
					return this.move('s',(PlayerClass) getShotBy());}
				else{
					return this.move('r',this);}}
			else{
				if(getShotBy()!=null&&(getShotBy()).deadQ()&&getAliveEnemies().size()!=0)
				{Collections.shuffle(g);return this.move('s',g.get(0));}
				else {return this.move('r', this);}}
	}}}
}
