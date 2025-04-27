package game;

import java.util.ArrayList;

public class RangedWeapon extends Weapon {
    private int rounds;

    private int acceptedID;

    public RangedWeapon(String description, int damagePoints, int rounds, int acceptedID) {
        super(description, damagePoints);
        this.rounds = rounds;
        this.acceptedID = acceptedID;


    }

    public void setRounds(int extraCharges) {
        this.rounds = this.rounds + extraCharges;
    }

    public int getRounds() {
        return this.rounds;
    }

    @Override
    public int getAcceptedID() {return acceptedID;}
}