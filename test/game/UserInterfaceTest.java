package game;

import game.board.Room;
import game.util.UserInterface;
import org.junit.jupiter.api.Test;

class UserInterfaceTest {

    @Test
    void showlist() {
        UserInterface um = new UserInterface();
        um.showHelp();
    }
}