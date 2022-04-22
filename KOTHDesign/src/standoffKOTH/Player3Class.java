package standoffKOTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player3Class extends PlayerClass {
	PlayerClass firstShot = null;
	public Player3Class()
	{
		super(5,0,5,0);
	}
	protected int makeMove()
	{
		List<PlayerClass> g = new ArrayList<PlayerClass>();g= this.getAliveEnemies();
		if(getShotBy()!=null&&firstShot==null) {firstShot = (PlayerClass) getShotBy();}
		if(getHP()<1) 
		{return this.move('h', this);} 
		else {
			if (getAmmo()<2)
			{return this.move('r',this);}
			else {
			if(firstShot!=null&& g.contains(firstShot)) {
				if(getAmmo()>0){
					return this.move('s',firstShot);}
				else{
					return this.move('r',this);}}
			else{
				if(getShotBy()!=null&&((PlayerClass)getShotBy()).deadQ()&&this.getAliveEnemies().size()!=0)
				{Collections.shuffle(g);return this.move('s',g.get(0));}
				else {return this.move('r', this);}}
	}}}
}
