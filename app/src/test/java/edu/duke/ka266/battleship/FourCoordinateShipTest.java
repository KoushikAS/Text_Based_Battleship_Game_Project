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

 

}
