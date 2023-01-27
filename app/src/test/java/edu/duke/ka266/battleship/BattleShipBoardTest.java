package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }

  @Test
  public void test_invalid_dimesnion() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-1, 10));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -10));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 10));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
  }

  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected) {
    int width = b.getWidth();
    int height = b.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        assertEquals(expected[i][j], b.whatIsAt(new Coordinate(i, j)));
      }
    }
  }

  @Test
  public void test_AddingShips() {
    BattleShipBoard<Character> b = new BattleShipBoard<Character>(3, 3);
    Character[][] expected = new Character[3][3];

    RectangleShip<Character> s1 = new RectangleShip<Character>(new Coordinate(1, 1), 's', '*');
    RectangleShip<Character> s2 = new RectangleShip<Character>(new Coordinate(2, 0), 's', '*');

    b.tryAddShip(s1);
    expected[1][1] = 's';
    b.tryAddShip(s2);
    expected[2][0] = 's';

    checkWhatIsAtBoard(b, expected);
  }

   @Test
  public void test_whatIsAt_error_cases() {
      BattleShipBoard<Character> b = new BattleShipBoard<Character>(3, 3);
      assertThrows(IllegalArgumentException.class, () -> b.whatIsAt(new Coordinate(5,5)));
     }
}
