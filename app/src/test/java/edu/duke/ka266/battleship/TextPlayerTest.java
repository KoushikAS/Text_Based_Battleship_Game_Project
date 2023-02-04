package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {

  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }

  @Test
  void test_read_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);

    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');

    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(p, expected[i]);// did we get the right Placement back
      assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  void test_do_One_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 5, "B2V\na0Q\na0v\nb2v\na1v", bytes);

    String prompt = "Player A where do you want to place a Submarine?";

    String expectedHeader = "  0|1|2\n";
    String body = "A  | |  A\n" +
        "B  | |s B\n" +
        "C  | |s C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";

    String[] expected = new String[4];
    expected[0] = prompt + "\n" + expectedHeader + body
        + expectedHeader;

    body = "A s| |  A\n" +
        "B s| |s B\n" +
        "C  | |s C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";
    expected[1] = prompt + "\nThat placement is invalid: it does not have the correct format.\n" + prompt + "\n"
        + expectedHeader + body + expectedHeader;

    body = "A s|s|  A\n" +
        "B s|s|s B\n" +
        "C  | |s C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";
    expected[2] = prompt + "\nThat placement is invalid: the ship overlaps another ship.\n" + prompt + "\n"
        + expectedHeader + body + expectedHeader;

    for (int i = 0; i < 3; i++) {
      player.doOnePlacement("Submarine", (p) -> new V1ShipFactory().makeSubmarine(p));
      assertEquals(expected[i], bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  void test_do_PlacementPhase() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    String inputs = new String(getClass().getClassLoader().getResourceAsStream("textplayer-input.txt").readAllBytes());
    TextPlayer player = createTextPlayer(10, 20, inputs, bytes);

    String expected = new String(
        getClass().getClassLoader().getResourceAsStream("textplayer-output.txt").readAllBytes());

    player.doPlacementPhase();
    assertEquals(expected, bytes.toString());
  }

  @Test
  void test_checkLost() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    TextPlayer player = createTextPlayer(3, 5, "B2v", bytes);
    player.doOnePlacement("Submarine", (p) -> new V1ShipFactory().makeSubmarine(p));
    assertFalse(player.checkLost());
    player.theBoard.fireAt(new Coordinate(1, 2));
    assertFalse(player.checkLost());
    player.theBoard.fireAt(new Coordinate(2, 2));
    assertTrue(player.checkLost());
  }

}
