package standoffKOTH;

import java.util.Random;


public class FriendlyNotFriendlyBot extends PlayerClass
{
    Random rand = new Random();

    public FriendlyNotFriendlyBot()
    {
        super(10, 0, 0, 0);
    }

    @Override
    protected int makeMove()
    {
        if (this.getAmmo() < 1)
            return this.move('r', this);
        else if (this.getShotBy() != null && !this.getShotBy().deadQ())
            return this.move('s', this.getShotBy());
        else if (this.getAmmo() < 10)
            return this.move('r', this);
        else if (this.getHP() < 10)
            return this.move('h', this);
        else
            return this.move('s', this.getAliveEnemies().get(rand.nextInt(this.getAliveEnemies().size())));
    }
}