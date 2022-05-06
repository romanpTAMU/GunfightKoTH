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
    protected int move = 0;
    private int movesPerRound = 250;

    public FocusFireBot() {
        super(0, 0, 10, 0);
    }

    protected int makeMove() {
        move++;
        if (deadQ()) {
            return 0;
        }
        // fully heal
        if (getHP() < getMaxHP()) {
            return this.move('h', this);
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
            // if (getShotBy() != null && !getShotBy().deadQ()) {
            // focusTarget = getShotBy();
            // }
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
            // if (getHP() < getMaxHP() && move < 240) {
            return this.move('h', this);
        }
        // get enought ammo to sustain focused fire
        // if (getAmmo() < 50) {
        if (getAmmo() < 50 && getAmmo() + move < movesPerRound) {
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