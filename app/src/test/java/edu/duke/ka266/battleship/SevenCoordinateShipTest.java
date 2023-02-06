package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SevenCoordinateShipTest {
  @Test
  public void test_Constructor() {

    // Test Upper
    Coordinate upperLeft = new Coordinate(1, 1);
    SevenCoordinateShip<Character> s1 = new SevenCoordinateShip<Character>("test", upperLeft, 'u', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 2)));

    // Test Right
    s1 = new SevenCoordinateShip<Character>("test", upperLeft, 'r', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 3)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 4)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 3)));

    // Test Down
    s1 = new SevenCoordinateShip<Character>("test", upperLeft, 'd', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 2)));

    // Test Left
    s1 = new SevenCoordinateShip<Character>("test", upperLeft, 'l', 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 3)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 4)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 5)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 3)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 4)));

    // Test Default construtor
    s1 = new SevenCoordinateShip<Character>(upperLeft, 's', '*');

    assertTrue(s1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 1)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(3, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(4, 2)));
    assertTrue(s1.occupiesCoordinates(new Coordinate(5, 2)));
    assertEquals("test ship", s1.getName());
  }

}
