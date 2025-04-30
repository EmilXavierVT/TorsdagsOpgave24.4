package game.items;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    public Item(String description) {
        // grab name as the last word of the description
        this(description,description.substring(description.lastIndexOf(' ')+1));
    }

    public Item(String description, String name) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }


//    Overridden methods
    public int getDamagePoints(){return 0;};

    public  void setDamagePoints(int damagePoints){};
    public int getAcceptedID() {return 0;}
}
