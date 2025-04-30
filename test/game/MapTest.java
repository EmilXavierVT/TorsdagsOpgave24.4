package game;

import game.board.Map;
import game.board.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    public void testBuildMap_And_GetStartRoom() {
        //Arrange
        Map map = new Map();
        Room expectedStartRoom = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");

        //Act
        map.buildMap();

        //Assert
        Room actualStartRoom = map.getStartRoom();
        assertEquals(actualStartRoom.getName(), expectedStartRoom.getName());
    }
}