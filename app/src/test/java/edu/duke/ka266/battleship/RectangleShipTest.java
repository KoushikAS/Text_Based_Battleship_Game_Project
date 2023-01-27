package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_RectangleShipConstructor() {

    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*') ;
    RectangleShip<Character> r1 = new RectangleShip<Character>(new Coordinate(1, 1), 2, 1,display);
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 2)));
    assertFalse(r1.occupiesCoordinates(new Coordinate(2, 1)));

    RectangleShip<Character> r2 = new RectangleShip<Character>(new Coordinate(1, 1), 1, 2, display);
    assertTrue(r2.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r2.occupiesCoordinates(new Coordinate(2, 1)));
    assertFalse(r2.occupiesCoordinates(new Coordinate(1, 2)));

    RectangleShip<Character> r3 = new RectangleShip<Character>(new Coordinate(1, 1), 2, 2,display);
    assertTrue(r3.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(2, 2)));
    assertFalse(r3.occupiesCoordinates(new Coordinate(3, 2)));

  }

}
