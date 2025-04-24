package game;

public class Weapon extends Item{

    private int damagePoints;

    public Weapon(String description, int damagePoints) {
        super(description);
        this.damagePoints =damagePoints;
    }


    public int getDamagePoints() {
        return damagePoints;
    }

    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }
}
