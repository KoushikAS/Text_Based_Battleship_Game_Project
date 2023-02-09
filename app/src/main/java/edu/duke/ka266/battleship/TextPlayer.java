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
  final protected ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;
  int moveAction;
  int sonarAction;

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
    moveAction = 2;
    sonarAction = 1;
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
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn)
      throws IOException {

    while (true) {
      try {
        Placement p = readPlacement( "Player " + TextPlayer + " where do you want to place a " + shipName + "?");
        Ship<Character> s = createFn.apply(p);
        String errorMessage = theBoard.tryAddShip(s);
        if (errorMessage != null) {
          out.print(errorMessage + "\n");
        } else {
          break;
        }
      } catch (IllegalArgumentException e) {
        out.print("That placement is invalid: it does not have the correct format." + "\n");
      }

    }
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
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }

  public boolean checkLost() {
    return this.theBoard.isAllShipsDestroyed();
  }

  /**
   * Reading Input form user to attack.
   */
  public Coordinate readCoordinate(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Coordinate(s);
  }

  /**
   * Move ship to a new location. Returns true if it is successful. otherwise
   * false
   **/
  private boolean moveShip() throws IOException {

    Coordinate where;
    try {
      where = readCoordinate("Please enter the coordinate of the ship you want to move");
    } catch (IllegalArgumentException e) {
      out.print("The Coordinate selected is invalid.");
      return false;
    }

    Ship<Character> shiptoMove;
    try {
      shiptoMove = this.theBoard.removeShip(where);
    } catch (IllegalArgumentException e) {
      out.print("There was no ship at the specified position");
      return false;
    }

    Coordinate newUpperLeft;
    try {
      newUpperLeft = readCoordinate( "Please enter the new coordinates where you want to move the ship.");
    } catch (IllegalArgumentException e) {
      out.print("The Coordinate selected is invalid.");
      this.theBoard.tryAddShip(shiptoMove);
      return false;
    }

    Coordinate oldUpperLeft = shiptoMove.getUpperLeftCoordinate();
    shiptoMove.MoveCoordiantes(newUpperLeft);

    String errorMessage = this.theBoard.tryAddShip(shiptoMove);

    if (errorMessage != null) {
      out.print(errorMessage);
      shiptoMove.MoveCoordiantes(oldUpperLeft);
      this.theBoard.tryAddShip(shiptoMove);
      return false;
    } else {
      return true;
    }
  }

  /**
   * Fire at enemy board. Returns True if it is successful otherwise it retuns
   * false.
   **/
  private boolean fire(Board<Character> enemyBoard, BoardTextView enemyView, String enemyName)
      throws IOException {
    try {
      Coordinate c = readCoordinate( "\nPlayer " + TextPlayer + " where do you want to fire ?\n");
      Ship<Character> s = enemyBoard.fireAt(c);
      if (s == null) {
        out.print("You missed!\n");
      } else {
        out.print("You hit " + s.getName() + "!\n");
      }
      return true;
    } catch (IllegalArgumentException e) {
      out.print("That placement is invalid: it does not have the correct format." + "\n");
      return false;
    }
  }

  /**
   * Sonar Scans the board based on a center point. Returns true if successfull
   * otherwise returns false
   **/
  private boolean sonarScan(Board<Character> enemyBoard) throws IOException {
    Coordinate center;
    try {
      center = readCoordinate( "\nPlayer " + TextPlayer + " please enter the center of the coordinate where you want to scan\n");
    } catch (IllegalArgumentException e) {
      out.print("The Coordinate entered is invalid.\n");
      return false;
    }

    HashMap<Character, Integer> record = new HashMap<>();

    boolean decrease = false;
    int noOfElementsPerLine = 1;
    int x = center.getRow();
    int y = center.getColumn() - 3;

    while (noOfElementsPerLine > 0) {
      for (int i = 0; i < noOfElementsPerLine; i++) {
        Character what = enemyBoard.whatIsAtForSelf(new Coordinate(x + i, y));
        if (what != null) {
          record.putIfAbsent(what, 0);
          record.computeIfPresent(what, (key, value) -> value + 1);
        }
      }

      if (noOfElementsPerLine == 7) {
        decrease = true;
      }

      if (decrease) {
        noOfElementsPerLine = noOfElementsPerLine - 2;
        x++;
      } else {
        noOfElementsPerLine = noOfElementsPerLine + 2;
        x--;
      }
      y++;
    }

    out.print("Submarines occupy " + String.valueOf(record.getOrDefault('s', 0)) + " squares\n");
    out.print("Destroyers occupy " + String.valueOf(record.getOrDefault('d', 0)) + " squares\n");
    out.print("BattleShips occupy " + String.valueOf(record.getOrDefault('b', 0)) + " squares\n");
    out.print("Carriers occupy " + String.valueOf(record.getOrDefault('c', 0)) + " squares\n");
    return true;
  }

  /**
   * Play one turn
   **/
  public void playOneTurn(Board<Character> enemyBoard, BoardTextView enemyView, String enemyName) throws IOException {
    out.print("Player " + this.TextPlayer + "'s turn:\n");
    out.print(this.view.displayMyBoardWithEnemyNextToIt(enemyView, "Your ocean", "Player " + enemyName + "'s ocean"));
    while (true) {

      out.print("\nPossible actions for Player " + this.TextPlayer + ":\n\n");
      out.print("F Fire at a square\n");
      if (moveAction > 0) {
        out.print("M Move a ship to another square (" + this.moveAction + " remaining)\n");
      }
      if (sonarAction > 0) {
        out.print("S Sonar scan (" + this.sonarAction + " remaining)\n");
      }
      String input = inputReader.readLine();

      // Fire
      if (input.equalsIgnoreCase("F")) {
        if (fire(enemyBoard, enemyView, enemyName) == true) {
          break;
        }
      }

      // MoveShip
      if (input.equalsIgnoreCase("M") && moveAction > 0) {
        if (moveShip() == true) {
          moveAction -= 1;
          break;
        }
      }

      // Sonar Scan
      if (input.equalsIgnoreCase("S") && sonarAction > 0) {
        if (sonarScan(enemyBoard)) {
          sonarAction -= 1;
          break;
        }
      }

    }
  }

}
