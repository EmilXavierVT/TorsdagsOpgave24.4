package game;

import java.util.Random;

public class Minion extends Creature {
    private Weapon loot;
    public int dropRate;
    private Random rdm = new Random();
    public Minion(String description, String name, int healthPoints, int damagePoints, int successRate,Weapon loot, int dropRate) {
        super(description, name, healthPoints, damagePoints, successRate);
        this.loot = loot;
        this.dropRate =dropRate;
    }
    public Weapon ifdefeated(){

        int chance = rdm.nextInt(100);
        if(chance<dropRate){return this.loot;
        }
         return null;
    }
}
