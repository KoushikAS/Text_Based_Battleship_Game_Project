package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class FourCoordinateShipTest {

  @Test
  public void test_FourCoordinateShipConstructor() {

    // Test Upper
    Coordinate upperLeft = new Coordinate(1, 1);
    FourCoordinateShip<Character> s1 = new FourCoordinateShip<Character>("test", upperLeft, 'u', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 3)));

    // Test Right
    s1 = new FourCoordinateShip<Character>("test", upperLeft, 'r', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 1)));

    // Test Down
    s1 = new FourCoordinateShip<Character>("test", upperLeft, 'd', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 3)));

    // Test Left
    s1 = new FourCoordinateShip<Character>("test", upperLeft, 'l', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 2)));

    // Test Default construtor
    s1 = new FourCoordinateShip<Character>(upperLeft, 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 3)));
    assertEquals("test ship", s1.getName());
  }

  @Test
  public void test_MoveFourCoordinateShipConstructor() {

    // Test Upper
    Coordinate upperLeft = new Coordinate(1, 1);
    FourCoordinateShip<Character> s1 = new FourCoordinateShip<Character>("test", upperLeft, 'u', 's', '*');
    s1.recordHitAt(new Coordinate(2,1));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 3)));
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(2,1), true));
    assertEquals(upperLeft, s1.getUpperLeftCoordinate());

    s1.MoveCoordiantes(new Coordinate(4,5));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 6)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 6)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 7)));
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(5,5), true));
    assertEquals(new Coordinate(4,5), s1.getUpperLeftCoordinate());
    

    // Test Right
    s1 = new FourCoordinateShip<Character>("test", upperLeft, 'r', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 1)));
    s1.recordHitAt(new Coordinate(2,1)); 
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(2,1), true));
    assertEquals(upperLeft, s1.getUpperLeftCoordinate());

    
    s1.MoveCoordiantes(new Coordinate(4,5));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 6)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(6, 5)));
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(5,5), true));
    assertEquals(new Coordinate(4,5), s1.getUpperLeftCoordinate());

    // Test Down
    s1 = new FourCoordinateShip<Character>("test", upperLeft, 'd', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 3)));
    s1.recordHitAt(new Coordinate(1,2)); 
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(1,2), true));
    assertEquals(upperLeft, s1.getUpperLeftCoordinate());

    s1.MoveCoordiantes(new Coordinate(4,5));
    
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 6)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 6)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 7))); 
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(4,6), true));
    assertEquals(new Coordinate(4,5), s1.getUpperLeftCoordinate());



    // Test Left
    s1 = new FourCoordinateShip<Character>("test", upperLeft, 'l', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 2)));
        s1.recordHitAt(new Coordinate(2,2)); 
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(2,2), true));
    assertEquals(upperLeft, s1.getUpperLeftCoordinate());

    s1.MoveCoordiantes(new Coordinate(4,4));
    
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 4)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(6, 5)));
    assertEquals('*', s1.getDisplayInfoAt(new Coordinate(5,5), true));
    assertEquals(new Coordinate(4,4), s1.getUpperLeftCoordinate());

    
  }

 

}
