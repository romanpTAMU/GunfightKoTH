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
	public int makeMove()
	{
		List<Object> b=new ArrayList<Object>();
		b=this.getInfo();
		List<PlayerClass> g = new ArrayList<PlayerClass>();g= this.getAliveEnemies();
		if(b.get(8)!=null) {firstShot = (PlayerClass) b.get(8);}
		if((int)b.get(0)<1) 
		{return this.move('h', this);} 
		else {
			if ((int)b.get(1)<1)
			{return this.move('r',this);}
			else {
			if(firstShot!=null&& g.contains(firstShot)) {
				if((int)b.get(1)>0){
					return this.move('s',firstShot);}
				else{
					return this.move('r',this);}}
			else{
				if(b.get(8)!=null&&((PlayerClass)b.get(8)).deadQ()&&this.getAliveEnemies().size()!=0)
				{Collections.shuffle(g);return this.move('s',g.get(0));}
				else {return this.move('r', this);}}
	}}}
}
