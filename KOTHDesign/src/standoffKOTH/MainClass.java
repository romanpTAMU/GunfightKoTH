package standoffKOTH;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MainClass {
	public static void main(String[] args) throws InterruptedException {
		int x = 0;
		int ties = 0;
		List<Integer> botSubscore = new ArrayList<Integer>();
		List<Integer> botWins = new ArrayList<Integer>();
		PlayerClass[] initialBots = new PlayerClass[]{
				new SmartBot(),
				new AlterOrAttackBot(),
				new Tank(),
				new NormalBot(),
				new Dexter(),
				new RageBot3(),
				new Destroyer(),
				new CalmOrAgressive()
		};
		for (int i = 0; i < initialBots.length; i++) {
			botSubscore.add(0);
			botWins.add(0);
		}
		while (x<100000)
		{
			int rounds = 0;
			PlayerClass[] bots =new PlayerClass[]{
					new SmartBot(),
					new AlterOrAttackBot(),
					new Tank(),
					new NormalBot(),
					new Dexter(),
					new RageBot3(),
					new Destroyer(),
					new CalmOrAgressive()
			};
			List<PlayerClass> players = new ArrayList<PlayerClass>();
			List<Boolean> botsDead = new ArrayList<Boolean>();
			List<Integer> hpList = new ArrayList<Integer>();
			for (PlayerClass z:bots) {
				botsDead.add(false);
				botSubscore.add(0);
				botWins.add(0);
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
							if(q.deadQ()&&q!=vBot) {botSubscore.set(v,botSubscore.get(v)+1);}
						}
					}
					hpList.set(v,vBot.getHP());
					//System.out.print("Character ");System.out.print(vBot);System.out.print(": ");
					//System.out.print(vBot.getHP());System.out.println(" HP");
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
				botWins.set(n, botWins.get(n)+1);
				botSubscore.set(n,botSubscore.get(n)+1);
			}
		}
		x++;
		System.out.println();
		for(int w=0;w<bots.length;w++)
		{
			System.out.println(players.get(w));
			System.out.println(botSubscore.get(w));
		}
		System.out.println();
		for(int e=0;e<bots.length;e++)
		{
			System.out.println(players.get(e));
			System.out.println(botWins.get(e));
		}
		System.out.println();
		for(int r=0;r<bots.length;r++)
		{
			System.out.println(players.get(r));
			System.out.println(((double)(botWins.get(r)*2+botSubscore.get(r)))/x);
		}}
		System.out.println(ties);	}}
