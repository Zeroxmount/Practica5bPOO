
package JWordle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  The {@code MainPage} class build up the main page, including the word boxes and buttons.
 *  <p>
 *      {@code MainPage} class contains a {@code KeyboardReader} instance for keyboard operation, a
 *      {@code ButtonOperator} instance for button operation, some {@code JLabel} instance for display and some other
 *      components which extends {@code JPanel} and {@code JLabel}.
 *  </p>
 *
 * @author rojas
 */
public class MainPage extends JFrame{
    /**
     * The width of the whole GUI window
     */
    public static int WIDTH = 601;
    /**
     * The height of the whole GUI window
     */
    public static int HEIGHT = 850;
    /**
     * The first text line containing "Wordle".
     */
    private JLabel titleLabel = null;
    /**
     * The text line in the button indicating the version.
     */
    private JLabel version = null;
    /**
     * An {@code LetterBoxes} instance displayed in the main page.
     */
    private LetterBoxes letterBoxes = null;
    /**
     * An instance of {@code WordList}, the wordlist in use.
     */
    private WordList wordList = null;
    /**
     * Store all the buttons in main page.
     * <p>Although there is only one button in the main page, but it is necessary to make my code scalability</p>
     */
    private JButton [] jbArray = null;

    /**
     * Respond to Keyboard input events.
     */
    KeyboardReader k;

    /**
     * Responds to MouseClick button events.
     */
    ButtonOperator b;

    /**
     * The default constructor of {@code MainPage} class.
     * Initialize the layout, size etc.
     */
    public MainPage(){
        this.setTitle("Wordle por Leo");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);       //make the window always generate at the center of screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit javax when close the window
        this.setResizable(false);   //do not let user resize the window
        this.setLayout(null);
        this.wordList = new WordList("words.txt");
        this.jbArray = new JButton[10];     //ten button in total.
        this._buildPage();
        k = new KeyboardReader(letterBoxes, wordList, this);
        b = new ButtonOperator(jbArray, letterBoxes, wordList, this);
    }

    /**
     * Show this main page.
     * Decorate {@code JFrame} method {@code setVisible}.
     */
    public void myShow(){
        this.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                letterBoxes.requestFocusInWindow();
            }
        });
        this.setVisible(true);
    }

    /**
     * Static method to quickly make a {@code JLabel}
     * @param text text to present.
     * @param font the font.
     * @param FontStyle Bold or Regular or sth.
     * @param textSize text size.
     * @return a made label
     */
    public static JLabel makeLabel(String text, String font , int FontStyle, int textSize){
        JLabel label = new JLabel(text);
        label.setFont(new Font(font, FontStyle, textSize) );
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        return label;
    }

    /**
     * Private method for the constructor to use to build the page.
     */
    private void _buildPage(){  //add component
        Container c = this.getContentPane();
        letterBoxes = new LetterBoxes();
        letterBoxes.setBounds(133, 130, LetterBoxes.WIDTH, LetterBoxes.HEIGHT);
        c.add(letterBoxes);

        //the title
        titleLabel = new JLabel("Wordle");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 45) );
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBounds(200, 40, 201, 50);
        c.add(titleLabel);

        //the test version
        version = new JLabel("Version 1");
        version.setFont(new Font("Serif", Font.PLAIN, 15) );
        version.setHorizontalAlignment(JLabel.CENTER);
        version.setVerticalAlignment(JLabel.CENTER);
        version.setBounds(200, 750, 201, 50);
        c.add(version);

        //the enter button in main page
        jbArray[0] = new JButton("ENTER");
        jbArray[0].setFont(new Font("SansSerif", Font.PLAIN, 22));
        jbArray[0].setBounds(225, 650, 151, 70);
        c.add(jbArray[0]);

    }
}
