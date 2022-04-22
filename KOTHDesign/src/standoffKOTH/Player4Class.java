package standoffKOTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Player4Class extends PlayerClass {
	Random random = new Random();
	PlayerClass firstShot = null;
	public Player4Class()
	{
		super(10,0,0,0);
	}
	protected int makeMove()
	{
		List<PlayerClass> g = new ArrayList<PlayerClass>();g= this.getAliveEnemies();
		if (this.getAliveEnemies().contains(firstShot)) {;} else if(getShotBy()!=null) {firstShot = getShotBy();} else {firstShot = this.getAliveEnemies().get(random.nextInt(this.getAliveEnemies().size()));}
		if(getHP()<1) 
		{return this.move('h', this);} 
		else {
			if ((int)getAmmo()<2)
			{return this.move('r',this);}
			else {
			if(firstShot!=null&& g.contains(firstShot)) {
				if((int)getAmmo()>0){
					return this.move('s',firstShot);}
				else{
					return this.move('r',this);}}
			else{
				if(getShotBy()!=null&&getShotBy().deadQ()&&this.getAliveEnemies().size()!=0)
				{Collections.shuffle(g);return this.move('s',g.get(0));}
				else {return this.move('r', this);}}
	}}}
}
