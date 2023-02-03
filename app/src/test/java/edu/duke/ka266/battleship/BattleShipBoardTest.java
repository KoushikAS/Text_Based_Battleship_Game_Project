package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }

  @Test
  public void test_invalid_dimesnion() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-1, 10, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -10, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 10, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0, 'X'));
  }

  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected) {
    int width = b.getWidth();
    int height = b.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expected[i][j], b.whatIsAtForSelf(new Coordinate(i, j)));
      }
    }
  }

  @Test
  public void test_AddingShips() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(3, 3, 'X');
    Character[][] expected = new Character[3][3];

    RectangleShip<Character> s1 = new RectangleShip<Character>(new Coordinate(1, 1), 's', '*');
    RectangleShip<Character> s2 = new RectangleShip<Character>(new Coordinate(2, 0), 's', '*');

    assertEquals(null, b.tryAddShip(s1));
    expected[1][1] = 's';
    assertEquals(null, b.tryAddShip(s2));
    expected[2][0] = 's';

    checkWhatIsAtBoard(b, expected);

    RectangleShip<Character> s3 = new RectangleShip<Character>(new Coordinate(1, 1), 's', '*');
    RectangleShip<Character> s4 = new RectangleShip<Character>(new Coordinate(5, 0), 's', '*');
    assertEquals("That placement is invalid: the ship overlaps another ship.", b.tryAddShip(s3));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.", b.tryAddShip(s4));
    checkWhatIsAtBoard(b, expected);
  }

  @Test
  public void test_whatIsAt_error_cases() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(3, 3, 'X');
    assertThrows(IllegalArgumentException.class, () -> b.whatIsAtForSelf(new Coordinate(5, 5)));
  }

  @Test
  public void test_fireAt() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(3, 3, 'X');

    RectangleShip<Character> s1 = new RectangleShip<Character>(new Coordinate(1, 1), 's', '*');
    b.tryAddShip(s1);

    assertNull(b.fireAt(new Coordinate(0, 0)));
    assertFalse(s1.isSunk());
    assertSame(s1, b.fireAt(new Coordinate(1, 1)));
    assertEquals('*', b.whatIsAtForSelf(new Coordinate(1, 1)));
    assertTrue(s1.isSunk());
    assertThrows(IllegalArgumentException.class, () -> b.fireAt(new Coordinate(4, 0)));
    assertThrows(IllegalArgumentException.class, () -> b.fireAt(new Coordinate(0, 4)));
  }

  @Test
  public void test_whatIsAtForEnemy() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(3, 3, 'X');

    RectangleShip<Character> s1 = new RectangleShip<Character>(new Coordinate(1, 1), 's', '*');
    b.tryAddShip(s1);

    assertEquals(null, b.whatIsAtForEnemy(new Coordinate(0, 0)));
    assertEquals(null, b.whatIsAtForEnemy(new Coordinate(1, 1)));

    b.fireAt(new Coordinate(0, 0));
    b.fireAt(new Coordinate(1, 1));

    assertEquals('X', b.whatIsAtForEnemy(new Coordinate(0, 0)));
    assertEquals('s', b.whatIsAtForEnemy(new Coordinate(1, 1)));

    assertThrows(IllegalArgumentException.class, () -> b.fireAt(new Coordinate(4, 0)));
    assertThrows(IllegalArgumentException.class, () -> b.fireAt(new Coordinate(0, 4)));
  }
}
