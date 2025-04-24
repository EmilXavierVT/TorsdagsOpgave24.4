package game;


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
                        ui.printMessage("you do not carry: " + secondWord);
                    }
                    break;
                case "health":
                    ui.printMessage("you have "+player.getHP()+ "in HP");
                    break;
                case "equip":
                    if(player.EquipItem(secondWord)){
                        ui.printMessage("you have now equipped "+secondWord+" your damage points is now "+player.getCurrentAttack());
                    }          
                    break;
                case "attack":
                    if(!player.getCurrentRoom().getCreatures().isEmpty())

                            while(player.getCurrentRoom().getCreatures().getFirst().getHealthPoints()>0 && player.getHP()>0){
                              fight(player.getCurrentRoom().getCreatures().getFirst());
                              if(player.getHP()<=0){
                                  ui.printMessage("You have been defeated");
                                  gameRunning = false;
                                  ui.printMessage("Thank you for playing Adventure");
                                  break;
                              }else {
                                  ui.printMessage("Congratulations you have defeated the beast! here is 1 HP to prepare for the next fight coming soon!");
                              player.setHP(1);
                              player.getCurrentRoom().removeCreature(player.getCurrentRoom().getCreatures().getFirst());
                                  break;

                              }

                            }
                    else{
                        ui.printMessage("There are no creatures to fight!");
                        break;
                    }
                    break;
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
        ui.printMessage("you will now fight " + creature.getName());
        int counter =1;
        while(creature.getHealthPoints()>0&&player.getHP()>0) {
            int creatureAP = creature.attack();
            int playerAP = player.attack();
            if (playerAP > creatureAP) {
                ui.printMessage("you have successfully damaged " + creature.getName() + " by " + player.attack() + "!");
                creature.changeHealthPoints(-playerAP);
            } else if (playerAP < creatureAP) {
                ui.printMessage("you have been damaged by " + creatureAP);
                player.setHP(-creatureAP);

            } else {
                ui.printMessage("you're caught in a stalemate! attack again to be break free!");
            }
            if(player.isEquipped()&& player.getEquipedItem() instanceof RangedWeapon){
                ((RangedWeapon) player.getEquipedItem()).setRounds(-counter);

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
