package standoffKOTH;

import java.util.ArrayList;
import java.util.List;

public class WhatABot extends PlayerClass {
    List<Class> randomShooters = List.of(FriendlyAndHealthyBot.class, FriendlyNotFriendlyBot.class, HpKing.class, RageBot.class, RageBot2.class, Player2Class.class);
    List<Class> wontShootFirst = List.of(Dexter.class, WhatABot.class, Tank.class, NormalBot.class);
    List<PlayerClass> shot = new ArrayList<>();

    public WhatABot() {
        super(10, 0, 0, 0);
    }

    protected int makeMove() {

        if (this.deadQ()) {
            return 0;
        }
        shot.removeIf(PlayerClass::deadQ);

        // if there's only 1 enemy left, or I'm currently getting attacked, just spam shoot
        PlayerClass immediateTarget = null;
        if (this.getShotBy() != null && !this.getShotBy().deadQ()) {
            immediateTarget = this.getShotBy();
            // only retaliate if its not some bot randomly firing
            if (!shot.contains(immediateTarget)) {
                for (Class randomShooter : randomShooters) {
                    if (randomShooter.isInstance(immediateTarget)) {
                        immediateTarget = null;
                        break;
                    }
                }
            }
        }
        if (getAliveEnemies().size() == 1) {
            immediateTarget = this.getAliveEnemies().get(0);
            // if the bot won't shoot unless shot first, heal up and fill up ammo before engaging
            if (!shot.contains(immediateTarget) && !this.getWhoShotYou().contains(immediateTarget)) {
                for (Class bot : wontShootFirst) {
                    if (bot.isInstance(immediateTarget)) {
                        if (getHP() < getMaxHP()) {
                            return this.move('h', this);
                        } else if (getAmmo() < 40) {
                            return this.move('r', this);
                        }
                    }
                }
            }
        }
        if (immediateTarget != null) {
            if (getAmmo() == 0) {
                return this.move('r', this);
            } else {
                shot.add(immediateTarget);
                return this.move('s', immediateTarget);
            }
        }

        if (shot.size() > 0) {
            if (getHP() < 10) {
                return this.move('h', this);
            }
            if (getAmmo() == 0) {
                return this.move('r', this);
            }
            return this.move('s', shot.get(shot.size() - 1));
        }

        if (getHP() < 15) {
            return this.move('h', this);
        }
        if (getAmmo() < 10) {
            return this.move('r', this);
        }
        if (getHP() < getMaxHP()) {
            return this.move('h', this);
        }
        if (getAmmo() < 20) {
            return this.move('r', this);
        }


        // kill fast players first
        PlayerClass target = getAliveEnemies().get(0);
        if (target.getSpeed() <= this.getSpeed() && getAmmo() < 30) {
            return this.move('r', target);
        }
        shot.add(target);
        return this.move('s', target);
    }
}

