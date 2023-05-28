

package JWordle;

/**
 * Store the current input position and provides several getters and setters.
 * <p>
 *     It is a static class with only static methods and class variables, shared by all the class in the package.
 * </p>
 * <p><b>row</b> indicates the current row.(initial value is 0)</p>
 * <p><b>col</b> indicates the current col.(initial value is 0)</p>
 */
public class Position {
    /**
     * <b>row</b> indicates the current row.(initial value is 0)
     */
    private static int row = 0;
    /**
     * <b>col</b> indicates the current col.(initial value is 0)
     */
    private static int col = 0;

    /**
     * Getter of row.
     * @return the current row.
     */
    public static int getRow(){
        return row;
    }

    /**
     * Getter of col.
     * @return the current column.
     */
    public static int getCol(){
        return col;
    }

    /**
     * Setter of row.
     * @param r Private static int row.
     */
    public static void setRow(int r){
        Position.row = r;
    }

    /**
     * Setter of row.
     * @param c Private static int col.
     */
    public static void setCol(int c){
        Position.col = c;
    }
}
