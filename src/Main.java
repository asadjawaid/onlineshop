import menu.Menu;
import menu.menutypes.MainMenu;

public class Main {
    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        Menu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
