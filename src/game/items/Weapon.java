package game.items;

import java.io.Serializable;

public class Weapon extends Item implements Serializable {
    private static final long serialVersionUID = 1L;


    private int damagePoints;

    public Weapon(String description, int damagePoints) {
        super(description);
        this.damagePoints =damagePoints;
    }

@Override
    public int getDamagePoints() {
        return damagePoints;
    }
    @Override
    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }
}
