package standoffKOTH;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class HpKing extends PlayerClass{
    // Keep a map of the amount of times an enemy (last) shot at us
    TreeMap<String, Integer> shotBy = new TreeMap<>();
    int previousHp;

    public HpKing(){
        super(10,0,0,0); // Health ftw!
        previousHp = getHP();
    }

    protected int makeMove() {
        // Update the shotBy-map with the latest enemy that shot at us
        if(getShotBy() != null && previousHp!=getHP()){
            shotBy.merge(getShotBy().getClass().toString(), 1, Integer::sum);
            previousHp = getHP();
        }
        // Remove any enemies from the shotBy-map that are already dead:
        shotBy.keySet().removeIf(enemyStr -> getAliveEnemies().stream().noneMatch(instance -> instance.getClass().toString().equals(enemyStr)));

        // If there are more than two enemies left: heal if we're not full, otherwise reload to get more ammo
        if(getAliveEnemies().size()>2){

        }

        // If we're not full health:
        if (getHP() < getMaxHP()){
            // If there are more than two enemies are left: heal!!
            if (getAliveEnemies().size() > 2)
                return move('h', this);
            // If there is more than one enemy left: only heal if we've below 10 HP:
            if (getAliveEnemies().size() > 1 && getHP() < 10)
                return move('h', this);
            // If there is just a single enemy left: just keep shooting and reloading, and hope for the best
        }

        // If we didn't have to heal, but are out of ammo: reload
        if(getAmmo()<1)
            return move('r', this);

        // If we didn't had to heal and still have ammo left: shoot the enemy that most frequently shot at us
        // (with priority to the more recent one, if multiple enemies shot at us an equal amount of times before):
        // If no one shot at us yet: shoot at a random enemy:
        return move('s',
                shotBy.isEmpty() ? getAliveEnemies().get((int)(Math.random()*getAliveEnemies().size()))
                        : getAliveEnemies().stream()
                        .filter(enemy -> enemy.getClass().toString().equals(
                                Collections.max(shotBy.descendingMap().entrySet(), Map.Entry.comparingByValue()).getKey()))
                        .findFirst().get());
    }
}