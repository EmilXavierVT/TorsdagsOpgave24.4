package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void showInventory(Player player) {
       List<Item> items = player.getInventory();
        if (items.size() == 0) {
            System.out.println("You aren't carrying anything");
        } else if (items.size() <= 3) {
            // Show a nice compact list if max. carrying three things
            System.out.println("You are carrying " + prettyCommaSeparatedList(items));

        } else {
            System.out.println("You are carrying: ");
            for (Item item : items) {
                System.out.print(" - " + item);
            }
            System.out.println();
        }
    }

    public String[] readInput() {
        System.out.println("Awaiting your command:");
        String[] commands = scanner.nextLine().trim().toLowerCase().split(" ");
        return commands;
    }

    public void describeRoom(Room room) {
        System.out.println(room.getDescription());
        ArrayList<Item> items = room.getItems();
        ArrayList<Creature> creatures =room.getCreatures();
        if (!items.isEmpty()) {
            System.out.print("Items in the room: ");
            for (Item item: items) {
                System.out.print(item.getDescription() + " ");
            }
            System.out.println();
        }
        if (!creatures.isEmpty()) {
            System.out.print("Creatures in the room: ");

                System.out.print(creatures.getFirst());

            System.out.println();
        }
    }

    public void printWelcome() {
        System.out.println("Welcome to the game of Adventure!");
        System.out.println("Type HELP or INFO for instructions on how to move around");
    }

    public void showHelp() {
        System.out.println("""
                Instructions
                ------------
                Type your command to the system, followed by enter. It doesn't matter if you use upper or lower-case.
                These are the instructions recognized:
                 HELP   or INFO, shows this help-text
                 GO     followed by one of the directions: North, South, East or West, moves the player in that direction (if possible)
                        You can also just write a direction, or simply the first letter of a direction.
                 EAT    will let you eat a consumable if present in your inventory
                 HEALTH returns your current health
                 LOOK   Looks around you, and describes what you can see
                 TAKE   or GET, followed by the name of an item, to pick up an item in the room
                 EXIT   Ends the game
                """);
    }

   private String prettyCommaSeparatedList(List items) {
        StringBuilder str = new StringBuilder();
        int length = items.size();
        for (int i = 0; i < length; i++) {
            str.append(items.get(i));
            if (i == length - 2) {
                str.append(" and ");
            } else if (i < length - 2) {
                str.append(", ");
            }
        }
        return str.toString();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
