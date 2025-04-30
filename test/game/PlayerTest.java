package game;

import game.board.Direction;
import game.board.Map;
import game.board.Room;
import game.items.Consumable;
import game.logic.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Map map;
    Player player;

    @BeforeEach
    public void setUp() {
        map = new Map();
        map.buildMap();
        player = new Player();
        player.setCurrentRoom(map.getStartRoom());
    }

    @Test
    public void testMoveEastFromStartroom() {
        //Arrange
        Room expected = new Room("Narrow cave", "narrow cave that seems to bend up and down almost infinitely");

        //Act
        player.move(Direction.EAST);
        //Assert
        assertEquals(expected.getName(), player.getCurrentRoom().getName());
    }

    @Test
    public void testMoveSouthFromStartroom() {
        //Arrange
        Room expected = new Room("Wet cave", "large dark cave - the floor is quite wet and slippery here.");
        //Act
        player.move(Direction.SOUTH);
        //Assert
         assertEquals(expected.getName(), player.getCurrentRoom().getName());
    }

    @Test
    public void testMoveNorthFromStartroom_NotPossible() {
        assertFalse(player.move(Direction.NORTH));
    }

    @Test
    public void testMoveWestFromStartroomNotPossible() {
        assertFalse(player.move(Direction.WEST));
    }


    @Test
    public void testPickupItemFromRoom() {
        //Arrange BeforeEach
        assertTrue(player.getInventory().isEmpty());
        //Act
        player.takeItem("map");
        assertTrue(player.getInventory().size() == 1);
    }


    @Test
    public void testPickupNotExistingItemFromRoom() {
        //Arrange BeforeEach
        assertTrue(player.getInventory().isEmpty());
        // Act
        player.takeItem("ball");
        assertTrue(player.getInventory().isEmpty());
    }

    @Test
    public void addBerryToInventory(){
//        arrange before
        Consumable berry = new Consumable("An exquisite blue berry",3);
//        act
        player.addToInventory(berry);
        assertTrue(player.getInventory().contains(berry));

    }
    @Test
    public void TestPoisonousFoodEffect(){

//        arrange before
        player.addToInventory(new Consumable("a foul smelling piece of meat",-7));
//      act
        player.consumeItem("meat");
        assertEquals(3,player.getHP());
    }


}