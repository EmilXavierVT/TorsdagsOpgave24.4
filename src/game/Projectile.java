package game;

public class Projectile extends Weapon{
    private final int id;
    public Projectile(String description, int damagePoints,int id) {
        super(description, damagePoints);
        this.id =id;
    }

    public int getId() {
        return id;
    }
    @Override
    public String toString(){
        return getName();
    }
}
