package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    @Test
    void describeRoom() {
//        arrange
        Room room = new Room("Treasure room","A final room filled with gold and a boss");
        // act
        room.addCreature("a huge slug","slug",80,10,80);
        // assert
        assertEquals();
    }
}