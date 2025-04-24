package game;

public class Map {

    private Room startRoom;

    public void buildMap() {
        //create room
        Room room1 = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        room1.addItem("an old rusty flashlight");
        room1.addItem("a crinkly paper map with a big X in the middle", "map");

        Room room2 = new Room("Narrow cave", "narrow cave that seems to bend up and down almost infinitely");

        Room room3 = new Room("Large grotto", "large grotto with an opening very far below where no light shines through.");
        room3.addItem("a wicker bird cage");

        //TODO create rest of the 9 rooms in cave

        Room room4 = new Room("Wet cave","large dark cave - the floor is quite wet and slippery here.");
        room4.addConsumable("A delicious glistering apple",5);


                Room room7 = new Room("Cavern lake", "the dark cool reflection of the cavern roof portrays a grand cavern lake. there seem to be a light at the bottom");
                room7.addRangedWeapon("An old heavy crossbow",5,3);
                Room room8 = new Room("Mellon cave", "small stony path next to the dark lake. A small door seems to be carved into the rock-face");
                Room room6 = new Room("Stairwell","a tight enclosure leading deeper into the stomach of the cave");
                Room room9 = new Room("Den of snails", "a million snails all approach you have stepped into their secret lair!");
                Room room5 = new Room("Treasure cave","small dark cave with apparently only a single opening");
        // Connect rooms
        room1.setEastRoom(room2);
        room2.setEastRoom(room3);
        room3.setSouthRoom(room6);
        room6.setSouthRoom(room9);
        room9.setWestRoom(room8);
        room7.setEastRoom(room8);
        room8.setNorthRoom(room5);
        room1.setSouthRoom(room4);
        room4.setSouthRoom(room7);

        //TODO connect all rooms according to the specified map

        //TODO Set start room to room1
        startRoom = room1;
    }

    public Room getStartRoom() {
        return startRoom;
    }
}
