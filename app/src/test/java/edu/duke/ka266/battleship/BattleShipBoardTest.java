package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board b1 = new BattleShipBoard(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }

  @Test
  public void test_invalid_dimesnion() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(-1, 10));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(10, -10));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(0, 10));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(10, 0));
  }

}
