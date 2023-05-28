
package JWordle;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * A class that can read wordlist from file and store in an {@code ArrayList}.
 * <p>
 *     This class maintain a wordlist in an {@code ArrayList}, and maintain an answer to the game.
 * </p>
 * <p>
 *     The class also provides several methods to deal with the wordlist and the answer.
 * </p>
 *
 * @author rojas
 **/
public class WordList {

    private ArrayList<String> list = null;  //wordlist
    private BufferedReader br = null;
    private static String ANS = "LEVER";    //the answer

    /**
     * Read file from path, add to the Arraylist.
     * @param path the file path.
     */
    public WordList(String path){
        list = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(path));
            String tmp = br.readLine();
            while (tmp != null){
                list.add(tmp.toUpperCase(Locale.ROOT));
                tmp = br.readLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found in FileReader");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("IO wrong in BufferedReader");
            e.printStackTrace();
        }finally {
            try{
                if (br != null) br.close();
            }catch (IOException e){
                System.out.println("Error in closing the BufferReader");
                e.printStackTrace();
            }
        }
        generateAns();
    }

    /**
     * Judge whether a string is in the wordlist.
     * @param s the fife letter string to judge.
     * @return true: in wordlist; false: not in wordlist.
     */
    public boolean inWordlist(String s){
        return list.contains(s);
    }

    /**
     * Generate a random answer from wordlist.
     */
    public void generateAns(){
        Random random = new Random();
        WordList.ANS = list.get(random.nextInt(list.size()));
        System.out.println("La respuesta es: " + WordList.ANS);
    }

    /**
     * static method get the private static variable <b>ANS</b> of the game.
     * @return string answer
     */
    public static String getAns(){
        return ANS;
    }

}