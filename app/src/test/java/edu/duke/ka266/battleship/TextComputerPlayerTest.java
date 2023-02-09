package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class TextComputerPlayerTest {

  @Test
  public void test_PlacementPhase() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    TextComputerPlayer player = new TextComputerPlayer("A", board, output, shipFactory);
    player.doPlacementPhase();
    String actual = player.view.displayMyOwnBoard();

    String expected = new String(
        getClass().getClassLoader().getResourceAsStream("computerPlayerPlacementPhaseTest-output.txt").readAllBytes());
    assertEquals(expected, actual);
  }

  @Test
  public void test_PlayOneTurn() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(2, 2, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    TextComputerPlayer player = new TextComputerPlayer("A", board, output, shipFactory);
    player.doPlacementPhase();
    player.playOneTurn(board, null, null);
    player.playOneTurn(board, null, null);
    player.playOneTurn(board, null, null);
    player.playOneTurn(board, null, null);
    player.playOneTurn(board, null, null);
    

    String expected = new String(
        getClass().getClassLoader().getResourceAsStream("computerPlayerPlayTurnTest-output.txt").readAllBytes());
    assertEquals(expected, bytes.toString());
  }
}
