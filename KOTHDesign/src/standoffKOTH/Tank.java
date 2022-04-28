package standoffKOTH;
import java.util.concurrent.ThreadLocalRandom;

public class Tank extends PlayerClass {

    public Tank() {
        super(3, 1, 5, 1);
    }

    @Override
    protected int makeMove() {

        if (getHP() < 6 && ThreadLocalRandom.current().nextBoolean()) {
            return move('h', this);
        } else if (getAmmo() == 0) {
            return move('r', this);
        } else {
            PlayerClass lastShot = getShotBy();
            if (lastShot == null) {
                return move('r', this);
            } else {
                return move('s', lastShot);
            }
        }
    }
}