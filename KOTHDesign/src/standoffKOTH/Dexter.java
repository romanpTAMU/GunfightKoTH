package standoffKOTH;

public class Dexter extends PlayerClass {
    public Dexter() {
        super(0, 10, 0, 0);
    }

    protected int makeMove() {
        if (getAmmo() < 2) { // reload if we have less than 2 bullets
            return move('r', this);
        } else if (getShotBy() != null) { // if someone shot at us, retaliate
            return move('s', getShotBy());
        } else { // heal while idle
            return move('h', this);
        }
    }
}