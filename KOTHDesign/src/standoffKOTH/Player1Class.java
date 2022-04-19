package standoffKOTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player1Class extends PlayerClass {

	public Player1Class()
	{
		super(0,8,1,1);
	}
	public int makeMove()
	{
		List<Object> b=new ArrayList<Object>();
		b=this.getInfo();
		List<PlayerClass> g = new ArrayList<PlayerClass>();g= this.getAliveEnemies();
		if((int)b.get(0)<6) 
		{return this.move('h', this);} 
		else {
			if ((int)b.get(1)<1)
			{return this.move('r',this);}
			else {
			if(b.get(8)!=null&& g.contains(b.get(8))) {
				if((int)b.get(1)>0){
					return this.move('s',(PlayerClass) b.get(8));}
				else{
					return this.move('r',this);}}
			else{
				if(b.get(8)!=null&&((PlayerClass)b.get(8)).deadQ()&&getAliveEnemies().size()!=0)
				{Collections.shuffle(g);return this.move('s',g.get(0));}
				else {return this.move('r', this);}}
	}}}
}
