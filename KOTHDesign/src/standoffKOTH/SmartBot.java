package standoffKOTH;

import java.util.ArrayList;
import java.util.List;

public class SmartBot extends PlayerClass {
 private PlayerClass lastFired = null;
 public SmartBot() {
  super(1, 5, 1, 3);
 }
 protected int makeMove() {
  List<Object> information = new ArrayList<Object>();
  information = this.getInfo();
  int hp =(int) information.get(0);
  if (hp < 5) {
   return this.move('h', this);
  }
  int ammo =(int) information.get(1);
  if (ammo < 4) {
   return this.move('r', this);
  }
  List<PlayerClass> enemies = new ArrayList<PlayerClass>();
  enemies = getAliveEnemies();
  for (int i = 0; i < enemies.size(); i++) {
   PlayerClass enemy = enemies.get(i);
   if (enemy.equals(lastFired)) {
    return this.move('s', enemy);
   }
  }
  lastFired = (PlayerClass) information.get(8);
  if (hp < 10) {
   return this.move('h', this);
  }
  return this.move('s', enemies.get(0));
 }
}