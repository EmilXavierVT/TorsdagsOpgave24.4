package game;


import java.util.ArrayList;

public class Adventure {

    private Map map;
    private Player player;
    private UserInterface ui = new UserInterface();

    public Adventure() {
        map = new Map();
        map.buildMap();
        player = new Player();
        player.setCurrentRoom(map.getStartRoom());
    }

    public void startGame() {
        boolean gameRunning = true;
        ui.printWelcome();

        while (gameRunning) {
            String[] commandString = ui.readInput();
            String firstWord = commandString[0].trim();
            String secondWord = "";
            if (commandString.length > 1) {
                secondWord = commandString[1].trim();
            }

            switch (firstWord) {
                case "inv":
                case "invent":
                case "inventory":
                    ui.showInventory(player);
                    break;
                case "look":
                    ui.describeRoom(player.getCurrentRoom());
                    break;
                case "help":
                case "info":
                    ui.showHelp();
                    break;
                case "quit":
                case "exit":
                case "bye":
                    gameRunning = false;
                    ui.printMessage("Thank you for playing Adventure");
                    break;
                case "go":
                    if(commandString.length<2){break;}
                    Direction direction = parseCommand(commandString[1].trim());
                    if(direction != null) {goCommand(direction);break;}
                    else {break;}
                case "take":
                    if (player.takeItem(secondWord)) {
                        ui.printMessage("You have taken the " + secondWord);
                    } else {
                        ui.printMessage("There is nothing like " + secondWord + " to take around here.");
                    }
                    break;
                case "eat":
                    if(player.consumeItem(secondWord)){
                        ui.printMessage("you're now eating the " + secondWord+ " your HP changes to "+player.getHP());
                    }else {
                        ui.printMessage("you do not carry or you can not eat " + secondWord);
                    }
                    break;
                case "hp":
                    ui.printMessage("you have "+player.getHP()+ "in HP");
                    break;
                case "dp":
                    ui.printMessage("your strength is "+player.attack());
                    break;
                case "quiver":
                    for(Projectile p: player.getQuiver()) {
                        ui.printMessage(p.toString());

                    }
                    break;
                case "equip":
                    if(player.EquipItem(secondWord)){
                        ui.printMessage("you have now equipped "+secondWord+" your damage points is now around "+ player.attack());
                        break;
                    }

                    else{
                        ui.printMessage("I'm sorry but you can't equip "+secondWord);

                    }
                    break;
                case "unequip":
                    player.unequipWeapon();
                    ui.printMessage("You are now unequipped! your strength is around "+player.attack());
                    break;
                case "attack":
                    ArrayList<Creature> creatureList=player.getCurrentRoom().getCreatures();
                    if(!creatureList.isEmpty()) {
                        Creature curentCreature = creatureList.getFirst();

                        while (curentCreature.getHealthPoints() > 0 && player.getHP() > 0) {
                            fight(curentCreature);
                            if (player.getHP() <= 0) {

                                ui.printMessage("You have been defeated");
                                gameRunning = false;
                                ui.printMessage("Thank you for playing Adventure");
                                break;


                            } else {
                                if ((curentCreature instanceof Boss)) {
                                    player.addToInventory(curentCreature.ifDefeated());
                                    ui.printMessage("an item have been added to your inventory!");
                                    ui.printMessage("Congratulations you have defeated the boss beast! Here is 3 HP to prepare for the next fight coming soon!");
                                    player.setHP(3);
                                    player.getCurrentRoom().removeCreature(curentCreature);
                                    break;
                                } else {
                                    ui.printMessage("Congratulations you have defeated the beast! Here is 1 HP to prepare for the next fight coming soon!");
                                    player.setHP(1);
                                    player.getCurrentRoom().removeCreature(curentCreature);
                                    if(curentCreature instanceof Minion){ player.replenishQuiver((Projectile)((Minion) curentCreature).ifdefeated(),1);}
                                    break;
                                }

                            }

                        }
                        break;
                    }
                    else{
                        ui.printMessage("There are no creatures to fight!");
                        break;
                    }
                   
//
                default:
                    ui.printMessage("I do not understand that command.");
            }
        }
    }

    public void goCommand(Direction direction) {
        if (goDirection(direction)) {
            Room currentRoom = getCurrentRoom();
            String name = currentRoom.getName();
            String line = "-".repeat(name.length());
            ui.printMessage(line + "\n" + name + "\n" + line);
            ui.describeRoom(currentRoom);
        } else {
            ui.printMessage("You cannot go in that direction");
        }
    }

    private Direction parseCommand(String word) {
        Direction requestedDirection = null;
        switch (word) {
            case "n", "north":
                requestedDirection = Direction.NORTH;
                break;
            case "s", "south":
                requestedDirection = Direction.SOUTH;
                break;
            case "e", "east":
                requestedDirection = Direction.EAST;
                break;
            case "w", "west":
                requestedDirection = Direction.WEST;
                break;
            default:
                return null;

        }
        return requestedDirection;
    }

    public void fight(Creature creature){
        int counter=0;
        ui.printMessage("you will now fight " + creature.getName());

        while(creature.getHealthPoints()>0&&player.getHP()>0) {

            int creatureAP = creature.attack();
            int playerAP = player.attack();


            if (playerAP > creatureAP) {

                ui.printMessage("you have successfully damaged " + creature.getName() + " by " + playerAP + "!");

                creature.changeHealthPoints(-playerAP);

            } else if (playerAP < creatureAP) {
                ui.printMessage("you have been damaged by " + creatureAP);
                player.setHP(-creatureAP);

            } else {
                ui.printMessage("you're caught in a stalemate! attack again to be break free!");
            }
            if(player.isEquipped() && !player.getQuiver().isEmpty() && player.getEquipedItem().getAcceptedID() == player.getQuiver().getFirst().getId()) {
                 player.removeFromQuiver(1);  }
            if(!player.isEquipped() && counter<1){
                ui.printMessage("you are no longer equipped! your current attack point is "+player.getCurrentAttack());
                counter++;
            }



        }

    }

    public boolean goDirection(Direction direction) {
        return player.move(direction);
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }
}
