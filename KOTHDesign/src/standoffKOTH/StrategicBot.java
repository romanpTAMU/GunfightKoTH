package standoffKOTH;

public class StrategicBot extends PlayerClass
{
    int total;
    PlayerClass foe;

    public StrategicBot()
    {
        super(10, 0, 0, 0);
    }
    @Override
    protected int makeMove()
    {
    	total = getPlayers().size();
        float friendliness;
        // The bot gets more aggressive when there are fewer players
        if (this.getAliveEnemies().size() == 1)
            friendliness = 0;
        else
            friendliness = (float) this.getAliveEnemies().size() / total;

        // When being repeatedly attacked by someone, act more aggressively
        if (this.foe != null && this.foe == this.getShotBy())
            friendliness -= 0.5;

        if (foe==null||this.foe.deadQ())
            this.foe = null;

        PlayerClass nextTarget; 
        if (this.getShotBy() != null && !this.getShotBy().deadQ())
            nextTarget = this.getShotBy();
        else
            nextTarget = this.getAliveEnemies().get(0);

        if (this.getAmmo() == 0)
            return this.move('r', this);

        if (friendliness > 0.7) {
            if (this.getHP() < this.getMaxHP())
                return this.move('h', this);
            else if (this.getAmmo() < 15)
                return this.move('r', this);
        }
        else if (friendliness > 0.35) {
            if (this.getHP() < 12)
                return this.move('h', this);
            else if (this.getAmmo() < 8)
                return this.move('r', this);
        }
        else if (friendliness > 0.15) {
            if (this.getAmmo() < 2)
                return this.move('r', this);
            else if (this.getHP() == 3)
                // heal so there won't be a instakill
                // probably less valuable when HP < 3     
                return this.move('h', this);
        }
        // Does not do anything else when friendliness <= 0.15
        return this.move('s', nextTarget);
    }
}