package game;

import game.board.Room;
import game.util.UserInterface;
import org.junit.jupiter.api.Test;

class UserInterfaceTest {

    @Test
    void showlist() {
        UserInterface ui = new UserInterface();
        ui.showHelp();
    }
    @Test
    void showWelcome() {
        UserInterface ui = new UserInterface();
        ui.printWelcome();
    }

}