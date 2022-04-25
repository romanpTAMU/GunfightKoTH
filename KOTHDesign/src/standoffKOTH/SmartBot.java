package standoffKOTH;

import java.util.ArrayList;
import java.util.List;

public class SmartBot extends PlayerClass {
 private PlayerClass lastFired;
 public SmartBot() {
  super(0, 5, 2, 3);
 }
 protected int makeMove() {

  if (getHP() < 5) {
   return this.move('h', this);
  }
  if (getAmmo() < 2) {
   return this.move('r', this);
  }
  List<PlayerClass> enemies = new ArrayList<PlayerClass>();
  enemies = getAliveEnemies();
  for (int i = 0; i < enemies.size(); i++) {
   PlayerClass enemy = enemies.get(i);
   if (!(enemy instanceof RageBot3) && enemy.equals(lastFired)) {
    return this.move('s', enemy);
   }
  }
  lastFired =getShotBy();
  if (getHP() < 10) {
   return this.move('h', this);
  }
  return this.move('s', enemies.get(0));
 }
}