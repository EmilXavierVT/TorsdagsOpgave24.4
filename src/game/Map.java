package game;

import java.util.ArrayList;

public class Map {
    private ArrayList<Projectile> allProjectiles = new ArrayList<>();

    private Room startRoom;

//            Creates all the rooms


    public void buildMap() {

//        Spawn point Player can go either South or East; room2 or room4
        Room room1 = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        room1.addItem("an old rusty flashlight");
        room1.addItem("a crinkly paper map with a big X in the middle", "map");

//       room with weapon; magical stick

        Room room2 = new Room("Narrow cave", "narrow cave that seems to bend up and down almost infinitely");
        room2.addMeleeWeapon("An unfathomable magical stick",7);

//      room with dummy item


        Room room3 = new Room("Large grotto", "large grotto with an opening very far below where no light shines through.");
        room3.addItem("a wicker bird cage");

//      room with consumable: Apple. maybe add logic to spawn new consumable when killing a boss.

        Room room4 = new Room("Wet cave","large dark cave - the floor is quite wet and slippery here.");
        room4.addConsumable("A delicious glistering apple",5);


//      Boss room! here the player fights the boss and gets a reward
        Room room5 = new Room("Treasure cave","small dark cave with apparently only a single opening");
        room5.addBoss("A huge slimy slug","KingSlug",30,11,80,new Projectile("poisonous arrow",6,1));
        room5.addBoss("a huge shining armour","Haunted Armour",50,16,85,new Weapon("A ragged long sword",12));


//     Transit room maybe hidden passage ?
        Room room6 = new Room("Stairwell","a tight enclosure leading deeper into the stomach of the cave");


//     Ranged weapon room!
        Room room7 = new Room("Cavern lake", "the dark cool reflection of the cavern roof portrays a grand cavern lake. there seem to be a light at the bottom");
        room7.addRangedWeapon("An old heavy crossbow",1,3,1);



//     The room before Boss room maybe regen capabilities?
        Room room8 = new Room("Mellon cave", "small stony path next to the dark lake. A small door seems to be carved into the rock-face");

//      Farm room, room filled with snails
        Room room9 = new Room("Den of snails", "a million snails all approach you have stepped into their secret lair!");
        for(int i =0; i<100;i++){
            room9.addMinion("one of many slimy snails","lowlife snail",10,1+i,65);
        }


        // Connect rooms together
        room1.setEastRoom(room2);
        room2.setEastRoom(room3);
        room3.setSouthRoom(room6);
        room6.setSouthRoom(room9);
        room9.setWestRoom(room8);
        room7.setEastRoom(room8);
        room8.setNorthRoom(room5);
        room1.setSouthRoom(room4);
        room4.setSouthRoom(room7);

//        Set start room
        startRoom = room1;
    }




    private void generateProjectiles() {

        allProjectiles.add(new Projectile("Arrow", 5, 1));
        allProjectiles.add(new Projectile("Poisonous Arrow", 7, 1));
        allProjectiles.add(new Projectile("Stone", 3, 3));
        allProjectiles.add(new Projectile("Fireball", 10, 4));

    }

    public ArrayList<Projectile> getAllProjectiles() {
        return allProjectiles;
    }
    //    Get the start Room

    public Room getStartRoom() {
        return startRoom;
    }
}
