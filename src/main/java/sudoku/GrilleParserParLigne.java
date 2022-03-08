package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/**.
 * classe GrilleParserParLigne
 */
public class GrilleParserParLigne {
    /**.
     * @param Tail_Grille
     */
    static final int  TAIL_GRILLE = 9;
    /**.
     *  GRID_TO_SOLVE.
     */
    private static char[][] g = new char[TAIL_GRILLE][TAIL_GRILLE];
    /**.
     *  board.
     */
    private final char[][] bo;
    /**.
     *  EMPTY.
     */
    public static final int EMPTY = '@'; // empty cell
    /**.
     *  SIZE.
     */
    public static final int SIZE = 9; // la taille de nos grilles de Sudoku
    /**
    *
    * @param board char[][]
    */
    public GrilleParserParLigne(char[][] board)  {
        board = fileToArray("sudoku1.txt");
        this.bo = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.bo[i][j] = board[i][j];
            }
        }
    }
    /**
    *
    * @param row int
    * @param number int
    * @return boolean
    *  on v�rifie si un nombre possible est d�j� dans une rang�e
    */
    public final boolean isInRow(final int row, final char number) {
        for (int i = 0; i < SIZE; i++) {
            if (bo[row][i] == number) {
                return true;
            }
        }
        return false;
    }
    /**
    *
    * @param number char
    * @param col int
    * we check if a possible number is already in a column
    * @return boolean
    */
    public final boolean isInCol(final int col, final char number) {
        for (int i = 0; i < SIZE; i++) {
            if (bo[i][col] == number) {
                return true;
                }
        }
        return false;
    }
    /**
    *
    * @param row int
    * @param col int
    * @param number char
    * @return boolean
    * on v�rifie si un nombre possible est dans sa bo�te 3x3
    */
    public final boolean isInBox(final int row, final int col,
            final char number) {
        final int n = 3;
        int r = row - row % n;
        int c = col - col % n;
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (bo[i][j] == number) {
                    return true;
                    }
            }
        }
        return false;
    }
    /**
    *
    * @param row int
    * @param col int
    * @param number char
    * @return boolean
    * M�thode combin�e pour v�rifier si un nombre possible
    * � une position row,col est correct.
    */
    public final boolean isOk(final int row, final int col, final char number) {
        return !isInRow(row, number)
                && !isInCol(col, number)
                && !isInBox(row, col, number);
    }
    /**
    * M�thode de r�solution. Nous utiliserons un
    * algorithme r�cursif de BackTracking.
    * @return boolean
    */
       public final boolean solve() {
       final int v = 10;
        for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
          // nous cherchons une cellule vide
          if (bo[row][col] == EMPTY) {
            // nous essayons des nombres possibles
            for (int number = 1; number <= SIZE; number++) {
              char  numbeer =  Character.forDigit(number, v);
              if (isOk(row, col, numbeer)) {
                  bo[row][col] = numbeer;
                if (solve()) {
                  return true;
                } else {
                    bo[row][col] = EMPTY;
                }
             }
            }
            return false; // we return false
           }
          }
         }
         return true; // sudoku r�solu
    }
    /**
    * display.
    */
    public final void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + bo[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    /**
    *
    * @param filename String
    * @return char[][]
    */
    public static char[][] fileToArray(final String filename)  {
        Scanner sc;
        final int  rows = 9;
        final int columns = 9;
        char[][] grille = new char[rows][columns];
        try {
            sc = new Scanner(new BufferedReader(new FileReader(filename)));
            while (sc.hasNextLine()) {
               for (int i = 0; i < columns; i++) {
                  String[] line = sc.nextLine().trim().split(" ");
                  for (int j = 0; j < line.length; j++) {
                      grille[i][j] = line[j].charAt(0);
                  }
               }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return grille;
    }
    /**
    *
    * @throws FileNotFoundException File
    * @param args String[]
    */
    public static void main(final String[] args) throws FileNotFoundException {
        GrilleParserParLigne sudoku = new GrilleParserParLigne(g);
        System.out.println("Grille de Sudoku � r�soudre");
        sudoku.display();
        // nous essayons la r�solution
        if (sudoku.solve()) {
            System.out.println("Grille de Sudoku r�solue avec un simple BT");
            sudoku.display();
        } else {
            System.out.println("Impossible � r�soudre");
        }
    }

}
