package sudoku;
/**.
 * classe GrilleImp
 */
public class GrilleImpl implements Grille {
	/**.
	 * grille
	 */
	private static char[][] grille;
	/**.
	 * taille du tableau
	 */
	private int taille;
	/**.
	 *
	 * @return char
	 */
	public char[][] getGrille() {
		return grille;
	}
/**
 *
 * @param grille
 */
	public void setGrille(char[][] grille) {
		this.grille = grille;
	}
  /**
   *
   * @return taille
   */
	public int getTaille() {
		return taille;
	}
  /**
   *
   * @param taille
   */
	public void setTaille(int taille) {
		this.taille = taille;
	}
/**.
 * constructeur
 * @param taille
 */
	public GrilleImpl(int taille) {
		this.taille = taille;
		grille = new char[taille][taille];
	}
	/**.
	 * retourne la taille de la grille
	 * @return taille
	 */
	public int getDimension() {
		// TODO Auto-generated method stub
		return grille.length;
	}
/**
 * set value.
 * @param x
 * @param y
 * @param value
 */
	public void setValue(int x, int y, char value) throws
	      IllegalArgumentException {
	  boolean f_ok = false;
		// TODO Auto-generated method stub
	  //si le nombre de ligne dépasse la taille de la grille
	   if (x > this.taille) {
		   throw new IllegalArgumentException();
	   }
	 //si le nombre de colonne dépasse la taille de la grille
	   if (y > this.taille) {
		   throw new IllegalArgumentException();
	   }
	  // on cherche dans le tableau est ce que la valeur est possible selon
	   //la taille du grille
	  for (int i = 0; i < this.taille; i++) {
		 if (value == possible[i]) {
			 f_ok = true;
		 }
	  }
	  if ((!f_ok) || !possible(x, y, value)) {
		  throw new IllegalArgumentException();
	  }
	  // affectation de la valeur
	  grille[x][y] = value;
	}
	/**
	 *
	 * @param x position x dans la grille
	 * @param y position y dans la grille
	 * @return valeur dans la case x,y
	 * @throws IllegalArgumentException si x ou y sont hors bornes
	 */
	public char getValue(int x, int y) throws
	                 IllegalArgumentException {
	// TODO Auto-generated method stub
    //si le nombre de ligne dépasse la taille de la grille
		   if (x > this.taille - 1) {
			  throw new IllegalArgumentException("taille depassee");
		   }
		 //si le nombre de colonne dépasse la taille de la grille
		   if (y > this.taille - 1) {
			  throw new IllegalArgumentException("taille depassee");
		   }
		 return grille[x][y];
	}
	/**.
	 * Test si une grille est terminee
	 * @return true si la grille est complete
	 */
	public boolean complete() {
		// TODO Auto-generated method stub
		for (int i = 0; i < grille.length; i++) {
			// si on a encore une case qui egale au caractre vide
			//donc la grille n'est pas complete
			for (int j = 0; j < grille[i].length; j++) {
				if (grille[i][j] == EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 *
	 * @param x position x dans la grille
	 * @param y position y dans la grille
	 * @param value valeur a mettre dans la case
	 * @return true si on peut mettre la valeur
	 * @throws IllegalArgumentException si x ou y sont hors bornes
	 * @throws IllegalArgumentException si la value n'est pas un caractère
	 * autorisé
	 *         ('1',....,'9')
	 */
	public boolean possible(int x, int y, char value) throws
	              IllegalArgumentException {
		// TODO Auto-generated method stub
		boolean existeColonne = false;
		boolean existeLigne = false;
		boolean existeCarre = false;
		  boolean f_ok = false;
			// TODO Auto-generated method stub
		  //si le nombre de ligne dépasse la taille de la grille
		 if (x > this.taille - 1) {
		     throw new IllegalArgumentException("taille depassee");
		   }
		 //si le nombre de colonne dépasse la taille de la grille
		 if (y > this.taille - 1) {
		   throw new IllegalArgumentException("taille depassee");
		  }
	//on cherche dans le tableau est ce que la valeur est possible
		  // selon la taille de la grille
		  for (int i = 0; i < this.taille; i++) {
			 if (value == possible[i]) {
				 f_ok = true;
			 }
		  }
		 if (!f_ok)  {
		  throw new IllegalArgumentException("valeur non autorise");
		  }
		int r;
		int c;
		int n = 0;
		for (int i = 0; i < taille; i++) {
		 if (grille[x][i] == value) {
			existeLigne = true;
		}
		}
			for (int i = 0; i < taille; i++) {
				if (grille[i][y] == value) {
				existeColonne = true;
				}
			}
	if (taille == 9) {
		n = 3;
		}
		if (taille == 16) {
			n = 4;
		}
		 r = x - x % n;
		 c = y - y % n;
		for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (grille[i][j] == value) {
					existeCarre = true;
				}
			}
		}
		if ((!existeLigne) && (!existeColonne) && (!existeCarre)
				&& this.grille[x][y] == EMPTY) {
			return true;
		}
		return false;
	}
}
