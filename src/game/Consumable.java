package game;

public class Consumable extends Item{
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
        return getDescription() + "and regenerates by "+ healthPoints;
   }
}
