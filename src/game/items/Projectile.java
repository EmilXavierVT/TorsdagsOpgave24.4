package game.items;

import java.io.Serializable;

public class Projectile extends Weapon implements Serializable {
    private static final long serialVersionUID = 1L;

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
