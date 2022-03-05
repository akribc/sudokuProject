package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GrilleParserParLigne {
    
 
    public static char[][] GRID_TO_SOLVE = new char[9][9];
    private char[][] board;
    public static final int EMPTY = '@'; // empty cell
    public static final int SIZE = 9; // la taille de nos grilles de Sudoku
    
    public GrilleParserParLigne(char[][] board)  {
        board = fileToArray("sudoku1.txt");
        this.board = new char[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
    
    // on vérifie si un nombre possible est déjà dans une rangée
    public boolean isInRow(int row, char number) {
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == number)
                return true;
        
        return false;
    }
    
    // we check if a possible number is already in a column
    public boolean isInCol(int col, char number) {
        for (int i = 0; i < SIZE; i++)
            if (board[i][col] == number)
                return true;
        
        return false;
    }
    
    // on vérifie si un nombre possible est dans sa boîte 3x3
    public boolean isInBox(int row, int col, char number) {
        int r = row - row % 3;
        int c = col - col % 3;
        
        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (board[i][j] == number)
                    return true;
        
        return false;
    }
    
    // Méthode combinée pour vérifier si un nombre possible à une position row,col est correct.
    public boolean isOk(int row, int col, char number) {
        return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
    }
    
    // Méthode de résolution. Nous utiliserons un algorithme récursif de BackTracking.
       public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
          // nous cherchons une cellule vide
          if (board[row][col] == EMPTY) {
            // nous essayons des nombres possibles
            for (int number = 1; number <= SIZE; number++) {
              char  numbeer =  Character.forDigit(number , 10);  
              if (isOk(row, col, numbeer)) {
                board[row][col] = numbeer;
                if (solve()) { // nous commençons à revenir en arrière de façon récursive
                  return true;
                } else { // si ce n'est pas une solution, on vide la cellule et on continue
                  board[row][col] = EMPTY;
                }
             }
            }

            return false; // we return false
           }
          }
         }

         return true; // sudoku résolu
    }
    
    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }
        
            System.out.println();
        }
        
        System.out.println();
    }
    
    public static char[][] fileToArray(String filename)  {
        
        Scanner sc;
        int rows = 9;
        int columns = 9;
        char[][] grille = new char[rows][columns];
        try {
            sc = new Scanner(new BufferedReader(new FileReader(filename)));
           
            while(sc.hasNextLine()) {
               for (int i=0; i<columns; i++) {
                  String[] line = sc.nextLine().trim().split(" ");
                  for (int j=0; j<line.length; j++) {
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
    public static void main(String[] args) throws FileNotFoundException {
        
        
        GrilleParserParLigne sudoku = new GrilleParserParLigne(GRID_TO_SOLVE);
        System.out.println("Grille de Sudoku à résoudre");
        sudoku.display();
        
        // nous essayons la résolution
        if (sudoku.solve()) {
            System.out.println("Grille de Sudoku résolue avec un simple BT");
            sudoku.display();
        } else {
            System.out.println("Impossible à résoudre");
        }
    }

}
