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
		int p4wins = 0;
		int p5wins = 0;
		int p1score = 0;
		int p2score = 0;
		int p3score = 0;
		int p4score = 0;
		int p5score = 0;
		boolean p1dead, p2dead, p3dead, p4dead, p5dead = false;
		while (x<5000)
		{
			boolean b = true;
			List<Integer> hpList = new ArrayList<Integer>();
			PlayerClass p1 = new Player1Class();
			PlayerClass p2 = new Player2Class();
			PlayerClass p3 = new Player3Class();
			PlayerClass p4 = new Player4Class();
			PlayerClass p5 = new SmartBot();
			List<PlayerClass> players = new ArrayList<PlayerClass>();
			players.add(p1);players.add(p2);players.add(p3);players.add(p4);players.add(p5);
			Collections.shuffle(players);
			Collections.sort(players);
			for (int i = 0;i<players.size();i++)
			{
				PlayerClass p = players.get(i);
				p.setPlayerList(players);
				hpList.add((Integer) p.getInfo().get(0));
			}
		p1dead = false;
		p2dead = false;
		p3dead = false;
		p4dead = false;
		p5dead = false;
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
				if(!p1dead&&p1.deadQ()){p1dead=true;if(p2dead) {p1score++;}if(p3dead) {p1score++;}if(p4dead) {p1score++;}if(p5dead) {p1score++;}}
				if(!p2dead&&p2.deadQ()){p2dead=true;if(p1dead) {p2score++;}if(p3dead) {p2score++;}if(p4dead) {p2score++;}if(p5dead) {p2score++;}}
				if(!p3dead&&p3.deadQ()){p3dead=true;if(p1dead) {p3score++;}if(p2dead) {p3score++;}if(p4dead) {p3score++;}if(p5dead) {p3score++;}}
				if(!p4dead&&p4.deadQ()){p4dead=true;if(p1dead) {p4score++;}if(p2dead) {p4score++;}if(p3dead) {p4score++;}if(p5dead) {p4score++;}}
				if(!p5dead&&p5.deadQ()){p5dead=true;if(p1dead) {p5score++;}if(p2dead) {p5score++;}if(p3dead) {p5score++;}if(p4dead) {p5score++;}}
				for (int j = 0;j<players.size();j++)
				{
					hpList.set(j,(Integer)players.get(j).getInfo().get(0));
					System.out.print("Character ");System.out.print(players.get(j));System.out.print(": ");
					System.out.print(players.get(j).getInfo().get(0));System.out.println(" HP");
				}
				if(p.getAliveEnemies().size()==0){b=false;break;}
				System.out.println();
			}
	}
		if(!p1.deadQ())
		{p1wins++;p1score++;}
		if(!p2.deadQ())
		{p2wins++;p2score++;}
		if(!p3.deadQ())
		{p3wins++;p3score++;}
		if(!p4.deadQ())
		{p4wins++;p4score++;}
		if(!p5.deadQ())
		{p5wins++;p4score++;}
		x++;
		}
		System.out.println(p1score);
		System.out.println(p2score);
		System.out.println(p3score);
		System.out.println(p4score);
		System.out.println(p5score);
		System.out.println();
		System.out.println(p1wins);
		System.out.println(p2wins);
		System.out.println(p3wins);
		System.out.println(p4wins);
		System.out.println(p5wins);
		System.out.println();
		System.out.println(p1wins*2+p1score);
		System.out.println(p2wins*2+p2score);
		System.out.println(p3wins*2+p3score);
		System.out.println(p4wins*2 + p4score);
		System.out.println(p5wins*2 + p5score);
	}}
