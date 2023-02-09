package edu.duke.ka266.battleship;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextComputerPlayerTest {


   private TextComputerPlayer createTextPlayer(int w, int h, OutputStream bytes) {

    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
    V2ShipFactory shipFactory = new V2ShipFactory();
    return new TextComputerPlayer("A", board, output, shipFactory);
  }
  
  @Test
  public void test_PlacementPhase()  throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextComputerPlayer player = createTextPlayer(10, 20, bytes);
  }

}
