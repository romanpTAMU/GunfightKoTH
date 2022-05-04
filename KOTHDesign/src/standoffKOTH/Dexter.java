package standoffKOTH;

public class Dexter extends PlayerClass {
    public Dexter() {
        super(0, 10, 0, 0);
    }

    protected int makeMove() {
        if (getHP() < getMaxHP()) { // heal if we're not at full health
            return move('h', this);
        }

        if (getAmmo() == 0) { // reload if we have no bullets
            return move('r', this);
        } else if (getShotBy() != null) { // if someone shot at us, retaliate
            if (getAliveEnemies().contains(getShotBy())) { // only if they're alive
                return move('s', getShotBy());
            } else {
                return move('h', this); // heal otherwise
            }
        } else {
            return move('h', this); // heal if we cannot decide on an action
        }
    }
}