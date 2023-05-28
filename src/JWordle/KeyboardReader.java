

package JWordle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Deal with Keyboard input.
 * <p>
 *     This class was designed to get the keyboard input and make some response. such as input letter into the WordBox,
 *     make actions respond to some function key and so on.
 * </p>
 *
 * @author rojas
 * @version 1
 */
public class KeyboardReader implements KeyListener, Enter {
    LetterBoxes boxes = null;
    SingleBox box = null;
    WordList wordList = null;
    JFrame fatherFrame = null;

    /**
     * Initialize a {@code KeyboardReader}.
     * @param letterBoxes an instance of {@code LetterBoxes}.
     * @param wordList an instance of {@code WordList}.
     * @param fatherFrame the father Frame.
     */
    public KeyboardReader(LetterBoxes letterBoxes, WordList wordList, JFrame fatherFrame){
        this.wordList = wordList;
        this.fatherFrame = fatherFrame;
        this.boxes = letterBoxes;
        boxes.addKeyListener(this);

    }

    /**
     * General method response to a key typing event.
     * @param e the KeyEvent to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        int ret  =_validate(c);
        if (ret == 1){
            _typeLetter(c);
        }else if (ret == 2){
            int tmp = (int)c - 32;      //change to capital
            c = (char)tmp;
            _typeLetter(c);
        }else if (ret == 3){
            _typeBackspace();
        }else if (ret ==4){
            _typeEnter();
        }
    }

    /**
     * Do nothing (empty method).
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {}

    /**
     * Do nothing (empty method).
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {}


    /**
     * private method to validate the keyboard input char c
     * @param c input char tobe validate
     * @return 1 is a capital letter; 2 is a small letter; 3 is Backspace; 4 is Enter.
     */
    private int _validate (char c){
        int ascii = (int)c;
//        65-A, 90-Z, 97-a, 122-z
        if (ascii >= 65 && ascii <= 90){
            return 1;
        }else if(ascii >= 97 && ascii <= 122){
            return 2;
        }else if (ascii == 8){      //backspace
            return 3;
        }else if (ascii == 10){     //enter
            return 4;
        }else return 0;
    }

    /**
     * Private method to type a letter into a {@code SingleBox}.
     * @param c new letter to be written.
     */
    private void _typeLetter (char c){
        if (Position.getCol() < LetterBoxes.COLS){
            box = boxes.getSingleBox(Position.getRow(), Position.getCol());
            box.setText(Character.toString(c));
            Position.setCol(Position.getCol() + 1); //pointer move forward
        }
    }

    /**
     * Private method to response to a Backspace.
     * <p>Delete a letter in current row</p>
     */
    private void _typeBackspace (){
        if (Position.getCol() > 0){
            Position.setCol(Position.getCol() - 1);
        }
        box = boxes.getSingleBox(Position.getRow(), Position.getCol());
        box.setText(" ");
    }

    /**
     * Private method response to an Enter.
     */
    private void _typeEnter (){
        if (Position.getCol() == LetterBoxes.COLS){     //the input letter is 5
            int flag = Enter.submit(boxes.getRow(Position.getRow()), wordList); //0-not, 1-win, 2-word not found
            if (flag == 1){
                PopWindow pop = new PopRes(fatherFrame, true);
                pop.jb.addActionListener(new ClickRestart(pop, boxes));
                pop.setVisible(true);
                Position.setRow(0);
                Position.setCol(0);
                wordList.generateAns();
            }else if (flag == 0){
                if (Position.getRow() < LetterBoxes.ROWS) {
                    if (Position.getRow() == 5){
                        PopWindow pop = new PopRes(fatherFrame, false);
                        pop.jb.addActionListener(new ClickRestart(pop, boxes));
                        pop.setVisible(true);
                    }else {
                        Position.setRow(Position.getRow() + 1);
                        Position.setCol(0);
                    }
                }
            }else{  //2-not in wordlist
                PopWindow pop = new PopWindow(fatherFrame, "No esta en lista", "Close", true);
                pop.setVisible(true);
            }

        }
        else{   //not enough letters
            PopWindow pop = new PopWindow(fatherFrame,"No hay suficientes", "Close", true);
            pop.setVisible(true);
        }
    }

}

