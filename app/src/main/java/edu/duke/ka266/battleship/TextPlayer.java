package edu.duke.ka266.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {

  final String TextPlayer;
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

  public TextPlayer(String TextPlayer, Board<Character> theBoard, BufferedReader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    this.TextPlayer = TextPlayer;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.out = out;
    this.inputReader = inputSource;
    this.shipFactory = shipFactory;
    this.shipsToPlace = new ArrayList<>();
    this.shipCreationFns = new HashMap<>();
    setupShipCreationList();
    setupShipCreationMap();
  }

  /**
   * Reading Input form user and placeing the ship.
   */
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }

  /**
   * Adding One Basic ship to the board.
   */
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement p = readPlacement("Player " + TextPlayer + " where do you want to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
  }

  /**
   * To show Initial phase. i.e. Instucting the player to setup his board.
   **/
  public void doPlacementPhase() throws IOException {
    out.print(view.displayMyOwnBoard());
    out.print("Player " + this.TextPlayer + ": you are going to place the following ships (which are all\n" +
        "rectangular). For each ship, type the coordinate of the upper left\n" +
        "side of the ship, followed by either H (for horizontal) or V (for\n" +
        "vertical).  For example M4H would place a ship horizontally starting\n" +
        "at M4 and going to the right.  You have\n" +
        "\n" +
        "2 \"Submarines\" ships that are 1x2\n" +
        "3 \"Destroyers\" that are 1x3\n" +
        "3 \"Battleships\" that are 1x4\n" +
        "2 \"Carriers\" that are 1x6\n");

    for (String ship : shipsToPlace) {
      doOnePlacement(ship, shipCreationFns.get(ship));
    }
  }

  protected void setupShipCreationMap() {
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
  }

  protected void setupShipCreationList() {
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(4, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(6, "Carrier"));
  }

}
