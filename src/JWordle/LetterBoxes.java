

package JWordle;

import javax.swing.*;
import java.awt.*;

/**
 * Maintain an array of {@code SingleBox}. Formed the main interface with user.
 * <p>The {@code LetterBoxes} extends from {@code JPanel}, consist of 5*6=30 {@code SingleBox}. </p>
 * @author rojas
 */
public class LetterBoxes extends JPanel {
    /**
     * The core {@code SingleBox} array.
     */
    private final static SingleBox[][] boxes = new SingleBox[6][5];
    /**
     * define the width of the letter box
     */
    public static final int WIDTH = 335;
    /**
     * define the height of the letter box
     */
    public static final int HEIGHT = 403;
    /**
     * define the rows of the box
     */
    public static final int ROWS = 6;
    /**
     * define the colomns of the box
     */
    public static final int COLS = 5;

    /**
     * Create a LetterBoxes instance.
     */
    public LetterBoxes(){
        this.setLayout(new GridLayout(ROWS, COLS, 5, 5));
        this.setSize(WIDTH, HEIGHT);
        for (int i = 0; i < boxes.length; i++){ //initialize the array
            for (int j = 0; j < boxes[i].length; j++){
                boxes[i][j] = new SingleBox();
                this.add(boxes[i][j]);
            }

        }

    }

    /**
     * Get a specified row of LetterBoxes
     * @param row the row to return [0, 5]
     * @return SingleBox[] array
     */
    public SingleBox[] getRow(int row){
        return this.boxes[row];
    }

    /**
     * Get a specified {@code LetterBox} item indexing by row and column.
     * @param row the row to return [0, 5]
     * @param col the column to return [0, 4]
     * @return SingleBox[] array
     */
    public SingleBox getSingleBox(int row, int col){
        return this.boxes[row][col];
    }

    /**
     * Restart the game, refresh the {@code LetterBoxes}.
     */
    public  static void refresh(){
        for (int i = 0; i < boxes.length; i++){ //initialize the array
            for (int j = 0; j < boxes[i].length; j++){
                boxes[i][j].refresh(4, " ");
            }
        }
        Position.setRow(0);
        Position.setCol(0);
    }

}
