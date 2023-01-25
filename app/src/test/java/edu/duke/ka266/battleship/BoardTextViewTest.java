package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {

  private void emptyBoardHelper(Board<Character> b1, String expectedHeader, String body) {
    BoardTextView view = new BoardTextView(b1);
    assertEquals(expectedHeader, view.makeHeader());
    String expected = expectedHeader + body + expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }

  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2);

    String expectedHeader = "  0|1\n";
    String body = "A  |  A\n" +
        "B  |  B\n";
    emptyBoardHelper(b1, expectedHeader, body);
  }

  @Test
  public void test_display_empty_3by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(3, 2);

    String expectedHeader = "  0|1|2\n";
    String body = "A  | |  A\n" +
        "B  | |  B\n";
    emptyBoardHelper(b1, expectedHeader, body);
  }

  @Test
  public void test_display_empty_3by5() {
    Board<Character> b1 = new BattleShipBoard<Character>(3, 5);

    String expectedHeader = "  0|1|2\n";
    String body = "A  | |  A\n" +
        "B  | |  B\n" +
        "C  | |  C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";

    emptyBoardHelper(b1, expectedHeader, body);
  }

  @Test
  public void test_display_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2);
    BasicShip s1 = new BasicShip(new Coordinate(1, 1));
    b1.tryAddShip(s1);

    String expectedHeader = "  0|1\n";
    String body = "A  |  A\n" +
        "B  |s B\n";
    emptyBoardHelper(b1, expectedHeader, body);
  }

  @Test
  public void test_display_3by5() {
    Board<Character> b1 = new BattleShipBoard<Character>(3, 5);
    BasicShip s1 = new BasicShip(new Coordinate(1, 1));
    b1.tryAddShip(s1);
    BasicShip s2 = new BasicShip(new Coordinate(3, 0));
    b1.tryAddShip(s2);
     BasicShip s3 = new BasicShip(new Coordinate(4, 2));
    b1.tryAddShip(s3);


    String expectedHeader = "  0|1|2\n";
    String body = "A  | |  A\n" +
        "B  |s|  B\n" +
        "C  | |  C\n" +
        "D s| |  D\n" +
        "E  | |s E\n";

    emptyBoardHelper(b1, expectedHeader, body);
  }

  @Test
  public void test_invalid_board_size() {
    Board wideBoard = new BattleShipBoard(11, 20);
    Board tallBoard = new BattleShipBoard(10, 27);

    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
  }

}
