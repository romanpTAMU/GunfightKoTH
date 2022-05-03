package standoffKOTH;

import java.util.List;

public class WhatABot extends PlayerClass {
    List<Class> randomShooters = List.of(FriendlyNotFriendlyBot.class, RageBot.class, RageBot2.class, Player2Class.class);

    public WhatABot() {
        super(10, 0, 0, 0);
    }

    protected int makeMove() {

        if (this.deadQ()) {
            return 0;
        }

        // if there's only 1 enemy left, or I'm currently getting attacked, just spam shoot
        PlayerClass immediateTarget = null;
        if (this.getShotBy() != null && !this.getShotBy().deadQ()) {
            immediateTarget = this.getShotBy();
            // only retaliate if its not some bot randomly firing
            for (Class randomShooter : randomShooters) {
                if (randomShooter.isInstance(immediateTarget) && this.getShotAt() != immediateTarget) {
                    immediateTarget = null;
                    break;
                }
            }
        }
        if (getAliveEnemies().size() == 1) {
            immediateTarget = this.getAliveEnemies().get(0);
        }
        if (immediateTarget != null) {
            if (getAmmo() == 0) {
                return this.move('r', this);
            } else {
                return this.move('s', immediateTarget);
            }
        }

        if (this.getShotAt() != null && !this.getShotAt().deadQ()) {
            if (getHP() < 10) {
                return this.move('h', this);
            }
            if (getAmmo() < 2) {
                return this.move('r', this);
            }
            return this.move('s', this.getShotAt());
        }
        
        if (getHP() < 15) {
            return this.move('h', this);
        }
        if (getAmmo() < 6) {
            return this.move('r', this);
        }
        if (getHP() < getMaxHP()) {
            return this.move('h', this);
        }
        if (getAmmo() < 12) {
            return this.move('r', this);
        }

        // kill fast players first
        PlayerClass target = getAliveEnemies().get(0);
        if (target.getSpeed() <= this.getSpeed() && getAmmo() < 20) {
            return this.move('r', target);
        }
        return this.move('s', target);
    }
}