package game;

import java.util.Random;

public class Creature {
    private String description;
    private String name;
    private int healthPoints;
    private int damagePoints;
    private int successRate;
    public Creature(String description, String name, int healthPoints, int damagePoints, int successRate){
    this.description =description;
    this.name = name;
    this.healthPoints=healthPoints;
    this.damagePoints=damagePoints;
    this.successRate=successRate;
    }

    public int attack(){
        Random random = new Random();
        int missFactor =random.nextInt(100);
        if(missFactor>successRate) {return 0;}
        return damagePoints + missFactor/25;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public void changeHealthPoints(int healthChange) {
        this.healthPoints += healthChange;
    }


    public void setDamagePoints(int damagePoints) {
        this.damagePoints = damagePoints;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        return description + "\n" + " Health " + healthPoints +
                " || Attack Strength " + damagePoints;
    }
    public Weapon ifDefeated(){ return null;}
}