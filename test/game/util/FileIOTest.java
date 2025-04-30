package game.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileIOTest {

    @Test
    void loadMap() {
        FileIO io = new FileIO();
        io.loadMap("fred");
    }
}