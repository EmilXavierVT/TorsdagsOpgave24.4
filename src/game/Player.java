package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {

    private int currentHealth = 10;
    private int currentAttack = 10;
    private int successRate = 100;
    private boolean isEquipped = false;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private Item equipedItem;

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
        if (requestedRoom != null) {
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

    public void addToInventory(Item item) {
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
//        takes the inventory list and runs through all possible names until one matches with the paramater string
//        removes the consumable and sets the hp of the player

            for (int i = 0; i < inventory.size(); i++) {
                Item item = inventory.get(i);
                if (item instanceof Weapon) {
                    if (item.getName().equalsIgnoreCase(WeaponName)) {
                        setCurrentAttack(((Weapon) item).getDamagePoints());
                        setSuccessRate(95); // TODO change to unique parameter
                        // Optionally, trigger effects of the consumable here
                        // ((Consumable) item).consume();
                        isEquipped = true;
                        equipedItem = item;
                        return true;
                    }
                }
                if (item instanceof RangedWeapon) {
                    if (((RangedWeapon) item).getRounds() != 0) {
                        setCurrentAttack(((RangedWeapon) item).getDamagePoints());
                        setSuccessRate(90); // TODO change to unique parameter
                        isEquipped = true;
                        equipedItem = item;
                    } else if (((RangedWeapon) item).getRounds() == 0) {
//                        UserInterface ui = new UserInterface();
//
//                        unequipWeapon();
//                        ui.printMessage("Your weapon has no more charges your Attack strength is back too " + currentAttack + " !");
                        break;
                    }
                }
                return true;
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
        return currentAttack + missFactor/25;
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

    public boolean isEquipped(){
        return isEquipped;
    }

}
