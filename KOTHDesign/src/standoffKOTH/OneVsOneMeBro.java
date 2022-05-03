package standoffKOTH;

public class OneVsOneMeBro extends PlayerClass{
    public OneVsOneMeBro(){
        super(9,0,0,1); // Sacrifice 1 HP for 1 Speed
    }

    protected int makeMove() {
        // If there are more than one enemy left:
        // heal if we're not max, otherwise reload to get more ammo
        if (getAliveEnemies().size()!=1) {
            if (getHP()<getMaxHP())
                return move('h', this);
            else
                return move('r', this);
        }
        // If we're out of ammo: reload
        if (getAmmo()<1)
            return move('r', this);
        // Just a single enemy left, and we have ammo: shoot, shoot, shoot!
        return move('s', getAliveEnemies().get(0));
    }
}