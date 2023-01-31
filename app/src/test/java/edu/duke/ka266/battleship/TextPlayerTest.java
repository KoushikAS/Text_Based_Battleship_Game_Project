package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    Board<Character> board = new BattleShipBoard<Character>(w, h);
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
    TextPlayer player = createTextPlayer(3, 5, "B2V\nC1H\na0v\n", bytes);

    String prompt = "Player A where would you like to put your ship?\n";

    String expectedHeader = "  0|1|2\n";
    String body = "A  | |  A\n" +
        "B  | |s B\n" +
        "C  | |s C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";

    String[] expected = new String[3];
    expected[0] = prompt + "\n" + expectedHeader + body + expectedHeader;

    body = "A  | |  A\n" +
        "B  | |s B\n" +
        "C  | |s C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";
    expected[1] = prompt + "\n" + expectedHeader + body + expectedHeader;

    body = "A s| |  A\n" +
        "B s| |s B\n" +
        "C  | |s C\n" +
        "D  | |  D\n" +
        "E  | |  E\n";
    expected[2] = prompt + "\n" + expectedHeader + body + expectedHeader;

    for (int i = 0; i < 3; i++) {
      player.doOnePlacement();
      assertEquals(expected[i], bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  void test_do_PlacementPhase() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 3, "A0V\n", bytes);

    String expectedHeader = "  0|1|2\n";
    String emptybody =  "A  | |  A\n" +
        "B  | |  B\n" +
      "C  | |  C\n";
    String prompt = "Player A where would you like to put your ship?\n";
    String body = "A s| |  A\n" +
        "B s| |  B\n" +
      "C  | |  C\n";


       String inital_prompt = "Player A: you are going to place the following ships (which are all\n" +
        "rectangular). For each ship, type the coordinate of the upper left\n" +
        "side of the ship, followed by either H (for horizontal) or V (for\n" +
        "vertical).  For example M4H would place a ship horizontally starting\n" +
        "at M4 and going to the right.  You have\n" +
        "\n" +
        "2 \"Submarines\" ships that are 1x2\n" +
        "3 \"Destroyers\" that are 1x3\n" +
        "3 \"Battleships\" that are 1x4\n" +
        "2 \"Carriers\" that are 1x6\n";

    
    String expected = expectedHeader + emptybody + expectedHeader +
      inital_prompt +
      prompt + "\n" + expectedHeader + body + expectedHeader;
    player.doPlacementPhase();
    assertEquals(expected, bytes.toString());
  }

}
