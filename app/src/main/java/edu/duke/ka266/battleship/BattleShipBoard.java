
package edu.duke.ka266.battleship;

import java.util.ArrayList;

public class BattleShipBoard<T> implements Board<T> {

  private final int width;
  private final int height;
  private final PlacementRuleChecker<T> placementChecker;

  final ArrayList<Ship<T>> myShips;

  /**
   * Constructs a BattleShipBoard with the specified width
   * and height
   * 
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or
   *                                  equal to zero.
   */
  BattleShipBoard(int w, int h,  PlacementRuleChecker<T> placementChecker) {
  
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }

    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }

    this.width = w;
    this.height = h;
    myShips = new ArrayList<>();

    this.placementChecker = placementChecker;
  }


    public BattleShipBoard(int w, int h) {
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<>(null)));
  }

  
  /**
   * Gets Width of BattleShipBoard
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets Height of BattleShipBoard
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * tryAddShip will check for validity of the placement and returns
   * true if the placement was ok, and false if it was invalid (and thus not
   * actually placed).
   * 
   **/
  public boolean tryAddShip(Ship<T> toAdd) {
    myShips.add(toAdd);
    return true;
  }

  /**
   * his method takes a Coordinate, and sees which (if any) Ship
   * occupies that coordinate. If one is found, we return whatever
   * displayInfo it has at those coordinates
   * 
   */
  public T whatIsAt(Coordinate where) {

    if (where.getRow() > height || where.getColumn() > width) {
      throw new IllegalArgumentException("Coordinate system provided is out of board area");
    }

    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        return s.getDisplayInfoAt(where);
      }
    }
    return null;
  }

}
