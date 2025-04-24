package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int currentHealth = 10;
    private int currentAttack = 5;
    private boolean equipedItem = false;
    private Room currentRoom;
    private ArrayList<Item> inventory;

    public Player() {
        inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public boolean move(Direction direction) {
        Room requestedRoom = null;

        switch (direction) {
            case NORTH:
                requestedRoom = currentRoom.getNorthRoom();
                break;
            case SOUTH:
                requestedRoom = currentRoom.getSouthRoom();
                break;
            case EAST:
                requestedRoom = currentRoom.getEastRoom();
                break;
            case WEST:
                requestedRoom = currentRoom.getWestRoom();
                break;
            default:
                System.out.println("not a valid direction try again!");
                move(direction);
        }
        if(requestedRoom!= null)
        {
            currentRoom = requestedRoom;
            return true;
        }

        // TODO if (requestedRoom != null) make currentRoom the requestedRoom;
        // TODO return whether move was possible
        {
            return false;
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }
    public void addToInventory(Item item){
        inventory.add(item);

    }

    public boolean takeItem(String itemName) {
        Item pickupFromRoom = currentRoom.removeItem(itemName);
        if (pickupFromRoom != null) {
            inventory.add(pickupFromRoom);
            return true;
        }
        return false;
    }

    public void setHP(int changeOfHP){
        currentHealth = this.currentHealth+changeOfHP;
    }
    public int getHP(){
        return this.currentHealth;
    }
    public void setCurrentAttack(int changeOfAP){
        currentAttack = this.currentAttack+changeOfAP;
    }
    public int getCurrentAttack(){
        return this.currentAttack;
    }

    public boolean consumeItem(String consumableName) {
        if (inventory.isEmpty()) {
            return false;
        }
//        takes the inventory list and runs through all possible names until one matches with the paramater string
//        removes the consumable and sets the hp of the player

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            if (item instanceof Consumable) {
                if (item.getName().equalsIgnoreCase(consumableName)) {
                    setHP(((Consumable) item).getHealthPoints());
                    inventory.remove(i);
                    // Optionally, trigger effects of the consumable here
                    // ((Consumable) item).consume();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean EquipItem(String WeaponName) {

        if(equipedItem){
            unequipWeapon();

        }

        if (!equipedItem) {
//        takes the inventory list and runs through all possible names until one matches with the paramater string
//        removes the consumable and sets the hp of the player

            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                if (item instanceof Weapon) {
                    if (item.getName().equalsIgnoreCase(WeaponName)) {
                            setCurrentAttack(((Weapon)item).getDamagePoints());
                        // Optionally, trigger effects of the consumable here
                        // ((Consumable) item).consume();
                        equipedItem =true;
                        return true;
                    }
                }if(item instanceof RangedWeapon){
                    if(((RangedWeapon) item).getRounds()>0){
                        setCurrentAttack(((RangedWeapon) item).getDamagePoints());
                        equipedItem =true;
                        if(((RangedWeapon) item).getRounds()==0){
                            unequipWeapon();
                        }
                    }   return true;
                }
            }

        }
        return false;
    }

    public void unequipWeapon(){
        currentHealth = 5;
        equipedItem =false;
    }
}
