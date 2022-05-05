package standoffKOTH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FocusFireBot extends PlayerClass {
    private PlayerClass focusTarget;
    private Map<PlayerClass, Integer> targetedBy = new HashMap<PlayerClass, Integer>();
    private List<PlayerClass> targetedLastRound = new ArrayList<PlayerClass>();

    public FocusFireBot() {
        super(2, 0, 8, 0);
    }

    protected int makeMove() {
        if (deadQ()) {
            return 0;
        }
        if (focusTarget != null && focusTarget.deadQ()) {
            focusTarget = null;
        }

        // if we have a target we kill or we die
        if (focusTarget != null) {
            if (getAmmo() < 1) {
                return this.move('r', this);
            } else {
                return this.move('s', focusTarget);
            }
        }

        List<PlayerClass> aliveEnemies = getAliveEnemies();
        // if only one enemy is left target it
        if (aliveEnemies.size() < 2) {
            focusTarget = aliveEnemies.get(0);
        } else {
            // if someone shot us twice in a row, we set him as target
            List<PlayerClass> targetedThisRound = getWhoShotYou();
            for (PlayerClass player : targetedThisRound) {
                if (!player.deadQ()) {
                    if (targetedLastRound.contains(player)) {
                        focusTarget = player;
                    }
                    targetedBy.merge(player, 1, Integer::sum);
                } else {
                    targetedLastRound.remove(player);
                }
            }
        }
        // shoot if we where shot twice in a row
        if (focusTarget != null) {
            if (getAmmo() < 1) {
                return this.move('r', this);
            } else {
                return this.move('s', focusTarget);
            }
        }

        // fully heal
        if (getHP() < getMaxHP()) {
            return this.move('h', this);
        }
        // get enought ammo to sustain focused fire
        if (getAmmo() < 50) {
            return this.move('r', this);
        }

        // focus on player who shot us the most
        List<Entry<PlayerClass, Integer>> shotByList = new ArrayList<>(targetedBy.entrySet());
        shotByList.sort(Entry.comparingByValue());
        Collections.reverse(shotByList);
        for (Entry<PlayerClass, Integer> playerE : shotByList) {
            PlayerClass player = playerE.getKey();
            if (player.deadQ()) {
                targetedBy.remove(player);
            } else {
                focusTarget = player;
                return this.move('s', focusTarget);
            }
        }

        //
        focusTarget = aliveEnemies.get(aliveEnemies.size() - 1);
        return this.move('s', focusTarget);
    }
}