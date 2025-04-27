package game;

import java.util.ArrayList;

public class Room {
    private Room northRoom;
    private Room eastRoom;
    private Room southRoom;
    private Room westRoom;

    private String name;
    private String description;
    private ArrayList<Item> items;
    private ArrayList<Creature> creatures;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        creatures = new ArrayList<>();
    }


//    the actual add method that adds the object to the ArrayList
//    corresponding to the given object

    public void addItem(Item item) {
        items.add(item);
    }

    public void addCreature(Creature creature){creatures.add(creature);}




    // Convenience methods for creating and adding Item
    public void addItem(String description) {

        this.addItem(new Item(description));
    }

    // Convenience methods for creating and adding Item with custom short name
    public void addItem(String description, String name) {
        this.addItem(new Item(description, name));

    }

//    Method to add consumable to a room
    public void addConsumable(String description, int HP){
        this.addItem(new Consumable(description,HP));
    }

//     Add Ranged Weapons to a room
    public void addRangedWeapon(String description, int damagePoints, int rounds, int acceptedID){
        this.addItem(new RangedWeapon(description,damagePoints,rounds,acceptedID));
    }

//  add Melee Weapon
    public void addMeleeWeapon(String description,int damagePoints){
        this.addItem(new MeleeWeapon(description,damagePoints));
    }
// add creature
    public void addCreature(String description, String name, int healthPoints, int damagePoints, int successRate){
        this.addCreature(new Creature(description, name, healthPoints, damagePoints, successRate));
    }

//    Add a boss
    public void addBoss(String description, String name, int healthPoints, int damagePoints, int successRate,Weapon weapon){
        this.addCreature(new Boss(description, name, healthPoints, damagePoints, successRate,weapon));
    }
//   Add a minion
    public void addMinion(String description, String name, int healthPoints, int damagePoints, int successRate){
        this.addCreature(new Minion(description, name, healthPoints, damagePoints, successRate));
    }

// we set the room order by the call of the following methods
//    we then check if the current room is the opposite of the set room, north vs. south,
//    if it is not we set it too


    public Room getNorthRoom() {
        return northRoom;
    }

    public void setNorthRoom(Room northRoom) {
       this.northRoom=northRoom;
        if (northRoom.getSouthRoom() != this) {
            northRoom.setSouthRoom(this);
        }
    }

    public Room getEastRoom() {
        return eastRoom;
    }

    public void setEastRoom(Room eastRoom) {
        this.eastRoom=eastRoom;
        if (eastRoom.getWestRoom() != this) {
            eastRoom.setWestRoom(this);
        }
    }

    public Room getSouthRoom() {
        return southRoom;
    }

    public void setSouthRoom(Room southRoom) {
       this.southRoom=southRoom;
        if (southRoom.getNorthRoom() != this) {
            southRoom.setNorthRoom(this);
        }
    }

    public Room getWestRoom() { return westRoom; }

    public void setWestRoom(Room westRoom) {
        this.westRoom=westRoom;

        if (westRoom.getEastRoom() != this) {
            westRoom.setEastRoom(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

// Returns the complete arraylist of creatures tied to one room
    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

//    Removes a creature from the arraylist of creatures
    public void removeCreature(Creature creature){
        creatures.remove(creature);
    }

    public Item removeItem(String itemName) {
        Item itemToBeRemoved = findItem(itemName);

        if (itemToBeRemoved != null) {
            items.remove(itemToBeRemoved);
        }
        return itemToBeRemoved;
    }

    public Item findItem(String itemName) {
        for (Item item : items) {

            if (item.getName().equalsIgnoreCase(itemName)) {

                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
