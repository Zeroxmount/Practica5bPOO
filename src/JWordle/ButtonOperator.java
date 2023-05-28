

package JWordle;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Deal with Mouse click the button.
 * <p>
 *     This class was designed to operate the button.
 * </p>
 * @author rojas
 */
public class ButtonOperator implements MouseListener, Enter{
    JButton enter = null;
    int enter_hash = 0;
//    JButton setting = null;
    int setting_hash = 0;
    LetterBoxes boxes = null;
    WordList wordList = null;
    JFrame fatherFrame = null;

    /**
     * Initialize a {@code ButtonOperator} instance.
     * @param jbArray the button array to perform.
     * @param letterBoxes an instance of {@code LetterBoxes}.
     * @param wordList an instance of {@code WordList}.
     * @param fatherFrame an instance of {@code JFrame}, the father frame.
     */
    public ButtonOperator(JButton [] jbArray, LetterBoxes letterBoxes, WordList wordList, JFrame fatherFrame){
        this.enter = jbArray[0];
        enter_hash = jbArray[0].hashCode();
        enter.addMouseListener(this);

//        this.setting = jbArray[1];
//        setting_hash = jbArray[1].hashCode();
//        setting.addMouseListener(this);
        this.boxes = letterBoxes;
        this.wordList = wordList;
        this.fatherFrame = fatherFrame;

    }

    /**
     * Deal with the mouse click.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int src = e.getSource().hashCode();
        if (src == enter_hash){
            _pressEnter();
        }else if(src == setting_hash){
            System.out.println("in setting !!!");
        }
        boxes.requestFocusInWindow();
    }

    /**
     * Private method dealing with Enter.
     */
    private void _pressEnter(){
        if (Position.getCol() == LetterBoxes.COLS){
            int flag = Enter.submit(boxes.getRow(Position.getRow()), wordList);
            if (flag == 1){
                PopWindow pop = new PopRes(fatherFrame, true);
                pop.jb.addActionListener(new ClickRestart(pop, boxes));
                pop.setVisible(true);
                Position.setRow(0);
                Position.setCol(0);
                wordList.generateAns();
            }else if (flag == 0) {
                if (Position.getRow() < LetterBoxes.ROWS) {
                    if (Position.getRow() == 5){
                        PopWindow pop = new PopRes(fatherFrame, false);
                        pop.jb.addActionListener(new ClickRestart(pop, boxes));
                        pop.setVisible(true);
                    }else{
                        Position.setRow(Position.getRow() + 1);
                        Position.setCol(0);
                    }
                }
            }else{
                PopWindow pop = new PopWindow(fatherFrame, "No existe en lista de palabras", "Cerrar", true);
                pop.setVisible(true);
            }
        } else{
            PopWindow pop = new PopWindow(fatherFrame,"No hay suficientes letras", "Cerrar", true);
            pop.setVisible(true);
        }
        boxes.requestFocusInWindow();
    }

    /**
     * Overriding the mousePressed method in interface {@code MouseListener}
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Regain focus on the WordBoxes
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        boxes.requestFocusInWindow();
    }

    /**
     * Overriding the mouseEntered method in interface {@code MouseListener}
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Overriding the mouseExited method in interface {@code MouseListener}
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}



