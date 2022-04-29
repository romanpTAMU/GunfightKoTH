package standoffKOTH;

import java.util.Random;


public class FriendlyAndHealthyBot extends PlayerClass
{
    Random rand = new Random();

    public FriendlyAndHealthyBot()
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
        else if (this.getHP() < 20)
            return this.move('h', this);
        else
            return this.move('s', this.getAliveEnemies().get(rand.nextInt(this.getAliveEnemies().size())));
    }
}