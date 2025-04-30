package game.items;

import java.io.Serializable;

public class RangedWeapon extends Weapon implements Serializable {
    private static final long serialVersionUID = 1L;

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