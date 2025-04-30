package game.npc;

import game.items.Weapon;

import java.io.Serializable;

public class Boss extends Creature implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Weapon weapon;

    public Boss(String description, String name, int healthPoints, int damagePoints, int successRate, Weapon weapon) {
        super(description, name, healthPoints, damagePoints, successRate);
        this.weapon =weapon;
    }
    @Override
    public Weapon  ifDefeated(){
        return weapon;
    }

}
