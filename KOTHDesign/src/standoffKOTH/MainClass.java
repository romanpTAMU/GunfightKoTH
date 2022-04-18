package standoffKOTH;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class MainClass {
	public static void main(String[] args) throws InterruptedException {
		int x = 0;
		int p1wins=0;
		int p2wins=0;
		int p3wins = 0;
		while (x<5000)
		{
			boolean b = true;
			List<Integer> hpList = new ArrayList<Integer>();
			PlayerClass p1 = new Player1Class();
			PlayerClass p2 = new Player2Class();
			PlayerClass p3 = new Player3Class();
			List<PlayerClass> players = new ArrayList<PlayerClass>();
			players.add(p1);players.add(p2);players.add(p3);
			Collections.shuffle(players);
			Collections.sort(players);
			for (int i = 0;i<players.size();i++)
			{
				PlayerClass p = players.get(i);
				p.setPlayerList(players);
				hpList.add((Integer) p.getInfo().get(0));
			}
		while(b)
		{
			for (int i = 0;i<players.size();i++)
			{
				PlayerClass p = players.get(i);
				System.out.println(p);
				System.out.println(p.getInfo());
				System.out.println(p.makeMove());
				System.out.println();
				System.out.println(p.getInfo());
				for (int j = 0;j<players.size();j++)
				{
					int aliveCount = 0;
					hpList.set(j,(Integer)players.get(j).getInfo().get(0));
					if(hpList.get(j)>0) {aliveCount++;}
					System.out.print("Character ");System.out.print(players.get(j));System.out.print(": ");
					System.out.print(players.get(j).getInfo().get(0));System.out.println(" HP");
				}
				if(p.getAliveEnemies().size()==0){b=false;break;}
				System.out.println();
			}
	}
		if(!p1.deadQ()) {p1wins++;}if(!p2.deadQ()) {p2wins++;}if(!p3.deadQ()) {p3wins++;}
		x++;
		}
		System.out.println(p1wins);
		System.out.println(p2wins);
		System.out.println(p3wins);
	}}
