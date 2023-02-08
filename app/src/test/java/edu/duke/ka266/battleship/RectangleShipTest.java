package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_RectangleShipConstructor() {

    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    SimpleShipDisplayInfo<Character> enemyDisplay = new SimpleShipDisplayInfo<Character>(' ', 's');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display,
        enemyDisplay);
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 2)));
    assertFalse(r1.occupiesCoordinates(new Coordinate(2, 1)));
    assertEquals(r1.getUpperLeftCoordinate(), new Coordinate(1, 1));

    RectangleShip<Character> r2 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 1, 2, display,
        enemyDisplay);
    assertTrue(r2.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r2.occupiesCoordinates(new Coordinate(2, 1)));
    assertFalse(r2.occupiesCoordinates(new Coordinate(1, 2)));
    assertEquals(r2.getUpperLeftCoordinate(), new Coordinate(1, 1));

    RectangleShip<Character> r3 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 2, display,
        enemyDisplay);
    assertTrue(r3.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(2, 1)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(r3.occupiesCoordinates(new Coordinate(2, 2)));
    assertFalse(r3.occupiesCoordinates(new Coordinate(3, 2)));
    assertEquals(r3.getUpperLeftCoordinate(), new Coordinate(1, 1));

  }

  @Test
  public void test_recordHitAt() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    SimpleShipDisplayInfo<Character> enemyDisplay = new SimpleShipDisplayInfo<Character>(' ', 's');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display,
        enemyDisplay);

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
    SimpleShipDisplayInfo<Character> enemyDisplay = new SimpleShipDisplayInfo<Character>(' ', 's');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display,
        enemyDisplay);

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
    SimpleShipDisplayInfo<Character> enemyDisplay = new SimpleShipDisplayInfo<Character>(' ', 's');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display,
        enemyDisplay);

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
    SimpleShipDisplayInfo<Character> enemyDisplay = new SimpleShipDisplayInfo<Character>(' ', 's');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display,
        enemyDisplay);

    Coordinate c1 = new Coordinate(5, 5);
    assertThrows(IllegalArgumentException.class, () -> r1.wasHitAt(c1));
    assertThrows(IllegalArgumentException.class, () -> r1.wasHitAt(null));

    Coordinate c2 = new Coordinate(1, 1);
    assertEquals(r1.getDisplayInfoAt(c2, true), 's');
    assertEquals(r1.getDisplayInfoAt(c2, false), ' ');
    r1.recordHitAt(c2);
    assertEquals(r1.getDisplayInfoAt(c2, true), '*');
    assertEquals(r1.getDisplayInfoAt(c2, false), 's');
  }

  @Test
  void test_getName() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    SimpleShipDisplayInfo<Character> enemyDisplay = new SimpleShipDisplayInfo<Character>(' ', 's');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 2, 1, display,
        enemyDisplay);
    assertEquals(r1.getName(), "submarine");

    RectangleShip<Character> r2 = new RectangleShip<Character>("missile", new Coordinate(1, 1), 2, 1, 's', '*');
    assertEquals(r2.getName(), "missile");

    RectangleShip<Character> r3 = new RectangleShip<Character>(new Coordinate(1, 1), 's', '*');
    assertEquals(r3.getName(), "test ship");
  }

  @Test
  void test_moveCoordinate() {
    SimpleShipDisplayInfo<Character> display = new SimpleShipDisplayInfo<Character>('s', '*');
    SimpleShipDisplayInfo<Character> enemyDisplay = new SimpleShipDisplayInfo<Character>(' ', 's');
    RectangleShip<Character> r1 = new RectangleShip<Character>("submarine", new Coordinate(1, 1), 3, 1, display,
        enemyDisplay);

    Coordinate c1 = new Coordinate(1, 2);
    r1.recordHitAt(c1);

    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 1)));
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 2)));
    assertTrue(r1.occupiesCoordinates(new Coordinate(1, 3)));
    assertEquals(r1.getDisplayInfoAt(c1, true), '*');
    assertEquals(r1.getUpperLeftCoordinate(), new Coordinate(1, 1));
    r1.MoveCoordiantes(new Coordinate(4, 5));

    assertFalse(r1.occupiesCoordinates(new Coordinate(1, 1)));
    assertFalse(r1.occupiesCoordinates(new Coordinate(1, 2)));
    assertFalse(r1.occupiesCoordinates(new Coordinate(1, 3)));
    assertThrows(IllegalArgumentException.class, () -> r1.getDisplayInfoAt(new Coordinate(1, 2), true));
    assertTrue(r1.occupiesCoordinates(new Coordinate(4, 5)));
    assertTrue(r1.occupiesCoordinates(new Coordinate(4, 6)));
    assertTrue(r1.occupiesCoordinates(new Coordinate(4, 7)));
    assertEquals(r1.getDisplayInfoAt(new Coordinate(4, 6), true), '*');
    assertEquals(r1.getUpperLeftCoordinate(), new Coordinate(4, 5));
  }

}
