package game.items;

import java.io.Serializable;

public class MeleeWeapon extends Weapon implements Serializable {
    private static final long serialVersionUID = 1L;

    public MeleeWeapon(String description, int damagePoints) {
        super(description, damagePoints);
    }

}
