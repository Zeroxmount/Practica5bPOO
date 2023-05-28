

package JWordle;

/**
 * The {@code Start} class is the main entrance to start the game.
 * <p>
 *     In {@code Start} class, there is only a public static main method.
 * </p>
 *
 * @author rojas
 */
public class Start {

    /**
     * This main method is the lunches the whole GUI
     * @param args a default String array which is not used by this program.
     */
    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
        mainPage.myShow();
    }

}
