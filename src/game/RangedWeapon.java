package game;

public class RangedWeapon extends Weapon{
    private int rounds;
    public RangedWeapon(String description, int damagePoints, int rounds) {
        super(description, damagePoints);
        this.rounds = rounds;
    }
}
