package sudoku;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;

import org.junit.Test;


public class TestGrille {
	private GrilleImpl grilleImpl = new GrilleImpl(9);
	public static char[][] GRID_TO_SOLVE = {
			{'9','@','@','1','@','@','@','@','5'},
			{'@','@','5','@','9','@','2','@','1'},
			{'8','@','@','@','4','@','@','@','@'},
			{'@','@','@','@','8','@','@','@','@'},
			{'@','@','@','7','@','@','@','@','@'},
			{'@','@','@','@','2','6','@','@','9'},
			{'2','@','@','3','@','@','@','@','6'},
			{'@','@','@','2','@','@','9','@','@'},
			{'@','@','1','9','@','4','5','7','@'},
	};
	
	private GrilleParserParLigne grilleParserParLigne = new GrilleParserParLigne(GRID_TO_SOLVE);




	@Test
	public void testDimension() {
		grilleImpl.setGrille(GRID_TO_SOLVE);
		assertEquals(9, grilleImpl.getDimension());


	}

	@Test
	public void testGetValue() {
		grilleImpl.setGrille(GRID_TO_SOLVE);
		assertEquals('1', grilleImpl.getValue(0,3));
		assertEquals('8', grilleImpl.getValue(2,0));



	}



	@Test
	public void testComplete() {
		grilleImpl.setGrille(GRID_TO_SOLVE);
		assertEquals(false, grilleImpl.complete());
	}

	@Test
	public void testPossible() {
		grilleImpl.setGrille(GRID_TO_SOLVE);
		assertEquals(false, grilleImpl.possible(0, 1, '1'));
		assertEquals(true, grilleImpl.possible(0, 1, '2'));
	}





	@Test
	public void testSetValue()
	{
		grilleImpl.setGrille(GRID_TO_SOLVE);
		grilleImpl.setValue(0, 1, '2');
		assertEquals('2', grilleImpl.getValue(0,1));

	}
//	@Test
//	//pou utiliser cette façon on doit utiliser un compilateur 1.8
//	public void testGetValueWithException() {
//		grilleImpl.setGrille(GRID_TO_SOLVE);
//		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> grilleImpl.getValue(0, 9));
//		assertEquals("taille depassee", exception.getMessage());
//	}
//
//
//
//	@Test
//	public void testSetValueWithException() {
//		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> grilleImpl.setValue(0, 9, '1'));
//		assertEquals("taille depassee", exception.getMessage());
//	}
//
//	@Test
//	public void testPossiblewithException() {
//		grilleImpl.setGrille(GRID_TO_SOLVE);
//		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> grilleImpl.possible(0, 9, '1'));
//		assertEquals("taille depassee", exception.getMessage());
//		IllegalArgumentException th = assertThrows(IllegalArgumentException.class, () -> grilleImpl.possible(0, 2, 'S'));
//		assertEquals("valeur non autorise", th.getMessage());
//
//
//	}
	
	
	   
    @Test
     public void testisInRow() {
         assertEquals(true, grilleParserParLigne.isInRow(0, '9'));
         assertEquals(false, grilleParserParLigne.isInRow(0, '3'));
     }
    
    @Test
    public void testisInCol() {
        assertEquals(true, grilleParserParLigne.isInCol(0, '9'));
        assertEquals(false, grilleParserParLigne.isInCol(0, '3'));
    }
    
    @Test
    public void testisInBox() {
        assertEquals(true, grilleParserParLigne.isInBox(0, 0, '9'));
        assertEquals(false, grilleParserParLigne.isInBox(0, 0,'3'));
    }
    
    
    @Test
    public void testisOk() {
        assertEquals(false, grilleParserParLigne.isOk(0, 0, '9'));
        assertEquals(true, grilleParserParLigne.isOk(0, 0,'3'));
    }
}