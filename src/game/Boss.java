package game;

public class Boss extends Creature{
    private final Weapon weapon;

    public Boss(String description, String name, int healthPoints, int damagePoints, int successRate, Weapon weapon) {
        super(description, name, healthPoints, damagePoints, successRate);
        this.weapon =weapon;
    }
    public Weapon  ifDefeated(){
        return weapon;
    }

}
