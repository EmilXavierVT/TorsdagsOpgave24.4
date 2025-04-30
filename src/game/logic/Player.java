package game.logic;

import game.board.Direction;
import game.board.Room;
import game.items.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player implements Serializable {

    private int currentHealth = 10;
    private int currentAttack = 10;
    private int successRate = 100;
    private boolean isEquipped = false;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private Item equipedItem;
    private ArrayList<Projectile> quiver;

    public Player() {
        inventory = new ArrayList<>();
        quiver = new ArrayList<>();
    }

    public Player(int currentHealth, int currentAttack, int successRate, boolean isEquipped, Room currentRoom,
                  ArrayList<Item> inventory, Item equipedItem, ArrayList<Projectile> quiver) {
        this.currentHealth = currentHealth;
        this.currentAttack = currentAttack;
        this.successRate = successRate;
        this.isEquipped = isEquipped;
        this.currentRoom = currentRoom;
        this.inventory = inventory;
        this.equipedItem = equipedItem;
        this.quiver = quiver;
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
        if (requestedRoom != null) {
            currentRoom = requestedRoom;
            return true;
        }


        {
            return false;
        }
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        if(item instanceof Projectile){
            quiver.add((Projectile) item);
        }else {
            inventory.add(item);
        }

    }

    public boolean takeItem(String itemName) {
        Item pickupFromRoom = currentRoom.removeItem(itemName);


        if (pickupFromRoom != null) {
            inventory.add(pickupFromRoom);
            switch (pickupFromRoom.getName()) {
                case "crossbow":
                    for (int i = 0; i < 3; i++) {
                        quiver.add(new Projectile("Arrow", 5, 1));
                    }

                    break;
            }

            return true;
        }

        return false;
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

        if (isEquipped) {
            unequipWeapon();

        }

        if (!isEquipped) {
//        takes the inventory list and runs through all possible names until one matches with the parameter string
//        removes the consumable and sets the hp of the player

            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                if (item instanceof MeleeWeapon) {
                    if (item.getName().equalsIgnoreCase(WeaponName)) {
                        setCurrentAttack(((Weapon) item).getDamagePoints());
                        setSuccessRate(95); // TODO change to unique parameter

                        this.isEquipped = true;
                        this.equipedItem = item;
                        return true;
                    }

                }
                if (item instanceof RangedWeapon) {
                    if (item.getName().equalsIgnoreCase(WeaponName)) {
                        if (!quiver.isEmpty()) {
                            this.isEquipped = true;
                            this.equipedItem = item;
                            this.equipedItem.setDamagePoints(quiver.getFirst().getDamagePoints());
                            setSuccessRate(90); // TODO change to unique parameter
                        return true;
                        } else {
                        unequipWeapon();

                        }
                    }
                }
//                                If i need to add more equippable items :)
            }
        }


        return false;

    }

    public void unequipWeapon(){
        this.currentAttack = 10;
        this.isEquipped =false;
        this.successRate =100;
        this.equipedItem =null;
    }
    public int attack() {
        Random random = new Random();
        int missFactor = random.nextInt(100);
        if (missFactor > successRate) {
            return 0;
        }
        if(isEquipped && equipedItem instanceof RangedWeapon) {
            for(Projectile p : quiver) {
            if ( p.getId() ==equipedItem.getAcceptedID()) {

                return currentAttack + equipedItem.getDamagePoints() + missFactor / 25;
            }

                if (quiver.isEmpty() || p.getId()!=equipedItem.getAcceptedID()) {
                    unequipWeapon();
                    return currentAttack + missFactor / 25;
                }
            }
        }
        return currentAttack +missFactor/25;
    }


    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
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

    public Item getEquipedItem() {
        return equipedItem;
    }

    public boolean isPlayerEquipped(){
        return isEquipped;
    }




    public void replenishQuiver(Projectile projectile,int charges){
        for(int i = 0; i<charges; i++){
            this.quiver.add(projectile);
        }
    }

    public void removeFromQuiver(int usedCharges){
        if(!quiver.isEmpty()) {

            for (int i = 0; i < usedCharges; i++) {
                this.quiver.removeFirst();
            }
        }
    }


    public ArrayList<Projectile> getQuiver() {
        return this.quiver;
    }


    public void setQuiver(ArrayList<Projectile> quiver) {
        this.quiver = quiver;
    }




}

