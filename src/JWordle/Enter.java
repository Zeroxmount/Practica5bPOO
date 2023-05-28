
package JWordle;

/**
 * The {@code Enter} interface judges a line of letter reference a wordlist.
 * <p>
 *     This interface has only one static method {@code submit}. Components which implements this interface can check
 *     whether a line of word is in the wordlist and change the letter color to gray, green, yellow correspond to letter
 *     not in answer, letter in the correct place, letter in the wrong place via static method {@code submit}.
 * </p>
 * <p>
 *     This interface is the <b>ONLY WAY</b> to change theme in the {@code SingleBox} instance.
 * </p>
 *
 * @author rojas
 */
public interface Enter{
//    static String ans = "APPLE";

    /**
     * Check a fife letter word by referencing to a wordlist.
     * @param box_line  A row in the LetterBoxes, SingleBoxes[5].
     * @param wordList  the wordlist.
     * @return 0: not in wordlist; 1: win the game; 2: incorrect.
     */
    static int submit(SingleBox [] box_line, WordList wordList){
        int isWin = 0;  //return win or not 0:not, 1:win, 2:not in wordlist
        int cnt = 0;        //correct letter number

        String anscpy = WordList.getAns();
        SingleBox box = null;
        String tobeCheck = new String("");  //storge the c urrent string for checking

        for (SingleBox singleBox : box_line) {
            box = singleBox;
            tobeCheck = tobeCheck.concat(box.getText());
        }
        if (wordList.inWordlist(tobeCheck)){        //the word is in the wordlist
            for (int i = 0; i < box_line.length; i++){
                box = box_line[i];
                char current = box.getText().charAt(0); //the current letter in the box
                int idx = anscpy.indexOf(current);      //the index of box letter in answer
                if (anscpy.indexOf(current) != -1){    //founded in the remaining answer_copy string
                    if (idx == i){      //--correct
                        cnt ++;
                        box.refresh(3);
                    }else{
                        box.refresh(2);
                    }
                    char [] chars = anscpy.toCharArray();
                    chars[idx] = '?';
                    anscpy = new String(chars);

                }else{  //not founded in the answer --incorrect
                    box.refresh(1);
                }

            }
            if (cnt == 5){
                isWin = 1;
            }
        }else{      //the word is not in the wordlist
            isWin = 2;
            System.out.println("La palabra no esta en la lista de letras!");
        }

        return isWin;
    }

}