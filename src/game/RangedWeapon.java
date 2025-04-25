package game;

public class RangedWeapon extends Weapon{
    private int rounds;
    public RangedWeapon(String description, int damagePoints, int rounds) {
        super(description, damagePoints);
        this.rounds = rounds;
    }
    public void setRounds(int extraCharges){
        this.rounds = this.rounds + extraCharges;
    }
    public int getRounds(){
        return this.rounds;
    }
}
