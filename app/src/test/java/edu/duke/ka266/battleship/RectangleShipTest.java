package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_RectangleShipConstructor() {

    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display);
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 2)));
    assertFalse(r1.occupiesCoordinates(new Coordinate(2, 1)));

    RectangleShip<Character> r2 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 1, 2, display);
    assertTrue(r2.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r2.occupiesCoordinates(new Coordinate(2, 1)));
    assertFalse(r2.occupiesCoordinates(new Coordinate(1, 2)));

    RectangleShip<Character> r3 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 2, display);
    assertTrue(r3.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(2, 2)));
    assertFalse(r3.occupiesCoordinates(new Coordinate(3, 2)));

  }

  @Test
  public void test_recordHitAt() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display);

    Coordinate c1 = new Coordinate(5, 5);
    assertThrows(IllegalArgumentException.class, () -> r1.recordHitAt(c1));
    assertThrows(IllegalArgumentException.class, () -> r1.recordHitAt(null));

    Coordinate c2 = new Coordinate(1, 1);
    assertFalse(r1.wasHitAt(c2));
    r1.recordHitAt(c2);
    assertTrue(r1.wasHitAt(c2));
  }

  @Test
  public void test_wasHitAt() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display);

    Coordinate c1 = new Coordinate(5, 5);
    assertThrows(IllegalArgumentException.class, () -> r1.wasHitAt(c1));
    assertThrows(IllegalArgumentException.class, () -> r1.wasHitAt(null));

    Coordinate c2 = new Coordinate(1, 1);
    assertFalse(r1.wasHitAt(c2));
    r1.recordHitAt(c2);
    assertTrue(r1.wasHitAt(c2));
  }

  @Test
  public void test_isSunk() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display);

    assertFalse(r1.isSunk());
    Coordinate c1 = new Coordinate(1, 1);
    r1.recordHitAt(c1);
    assertFalse(r1.isSunk());
    Coordinate c2 = new Coordinate(1, 2);
    r1.recordHitAt(c2);
    assertTrue(r1.isSunk());
  }

  @Test
  public void test_getDisplayInfoAt() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display);

    Coordinate c1 = new Coordinate(5, 5);
    assertThrows(IllegalArgumentException.class, () -> r1.wasHitAt(c1));
    assertThrows(IllegalArgumentException.class, () -> r1.wasHitAt(null));

    Coordinate c2 = new Coordinate(1, 1);
    assertEquals(r1.getDisplayInfoAt(c2), 's');
    r1.recordHitAt(c2);
    assertEquals(r1.getDisplayInfoAt(c2), '*');
  }

  @Test
  void test_getName() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display);
    assertEquals(r1.getName(), "submarine");

    RectangleShip<Character> r2 = new RectangleShip<Character>("missile", new Coordinate(1, 1), 2, 1, 's', '*');
    assertEquals(r2.getName(), "missile");

    RectangleShip<Character> r3 = new RectangleShip<Character>(new Coordinate(1, 1), 's', '*');
    assertEquals(r3.getName(), "test ship");
  }
}
