package game.items;

import java.io.Serializable;

public class Consumable extends Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private int healthPoints;

    public Consumable(String description, int healthPoints) {
        super(description);
        this.healthPoints = healthPoints;

    }
    public int getHealthPoints(){
        return this.healthPoints;
    }
   @Override
    public String toString(){
        return getDescription() + " that regenerates by "+ healthPoints;
   }
}
