package standoffKOTH;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
public class MainClass {
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException {
		int x = 0;
		int ties = 0;
		HashMap<Class<? extends PlayerClass>,Integer> scoreSaver = new HashMap<Class<? extends PlayerClass>,Integer>();
		PlayerClass[] initialBots = new PlayerClass[]{
				new CalmOrAgressive(),
				new Dexter(),
				new SmartBot(),
				new AlterOrAttackBot(),
				new HpKing(),
				new Tank(),
				new FriendlyNotFriendlyBot(),
				new RageBot3(),
				new NormalBot(),
				new Destroyer()
		};
		for (PlayerClass j:initialBots) {
			scoreSaver.put(j.getClass(), 0);
		}
		while (x<100000)
		{
			int rounds = 0;
			PlayerClass[] bots =new PlayerClass[]{
					new CalmOrAgressive(),
					new Dexter(),
					new SmartBot(),
					new AlterOrAttackBot(),
					new HpKing(),
					new Tank(),
					new FriendlyNotFriendlyBot(),
					new RageBot3(),
					new NormalBot(),
					new Destroyer()
			};
			List<PlayerClass> players = new ArrayList<PlayerClass>();
			List<Boolean> botsDead = new ArrayList<Boolean>();
			List<Integer> hpList = new ArrayList<Integer>();
			for (PlayerClass z:bots) {
				botsDead.add(false);
				players.add(z);
				hpList.add(z.getHP());
			}
			boolean b = true;
			Collections.shuffle(players);
			Collections.sort(players);
			for (int i = 0;i<players.size();i++)
			{
				PlayerClass p = players.get(i);
				p.setPlayerList(players);
				hpList.add(p.getHP());
			}
		while(b)
		{
			for (PlayerClass p:bots)
			{
				p.makeMove();
				for (int v=0;v<bots.length;v++)
				{
					PlayerClass vBot = players.get(v);
					if(vBot.deadQ()&&!botsDead.get(v))
					{
						botsDead.set(v, true);
						for(PlayerClass q:bots)
						{
							if(q.deadQ()&&q!=vBot) {
							scoreSaver.put(vBot.getClass(),scoreSaver.get(vBot.getClass())+1);	}
						}
					}
					hpList.set(v,vBot.getHP());
				}
				if(p.getAliveEnemies().size()==0){b=false;break;}
			}
			rounds++;
			if(rounds>250)
			{
				ties++;
				x++;
				break;
			}
	}
		for(int n=0;n<bots.length;n++)
		{
			PlayerClass nBot = players.get(n);
			if(!nBot.deadQ())
			{
				scoreSaver.put(nBot.getClass(),scoreSaver.get(nBot.getClass())+3);
				}
			}
		
		x++;
		System.out.println();
		for(int r=0;r<bots.length;r++)
		{
			System.out.println(players.get(r));
			System.out.println((double)scoreSaver.get(players.get(r).getClass())/x);
		}}
		System.out.println(ties);}}
