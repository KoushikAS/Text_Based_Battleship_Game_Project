
package edu.duke.ka266.battleship;

import java.util.ArrayList;
import java.util.HashSet;

public class BattleShipBoard<T> implements Board<T> {

  private final int width;
  private final int height;
  private final PlacementRuleChecker<T> placementChecker;
  private final HashSet<Coordinate> enemyMisses;

  final T missInfo;
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
  BattleShipBoard(int w, int h, PlacementRuleChecker<T> placementChecker, T missInfo) {

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
    this.enemyMisses = new HashSet<>();
    this.missInfo = missInfo;
  }

  public BattleShipBoard(int w, int h, T missInfo) {
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<>(null)), missInfo);
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
   * null if the placement was ok,
   * else errorMEssage if it was invalid (and thus not
   * actually placed).
   * 
   **/
  public String tryAddShip(Ship<T> toAdd) {
    String errorMessage = placementChecker.checkPlacement(toAdd, this);
    if (errorMessage != null) {
      return errorMessage;
    }

    myShips.add(toAdd);
    return null;
  }

  protected T whatIsAt(Coordinate where, boolean isSelf) {

    if (where.getRow() > height || where.getColumn() > width) {
      throw new IllegalArgumentException("Coordinate system provided is out of board area");
    }

    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    return null;

  }

  /**
   * his method takes a Coordinate, and sees which (if any) Ship
   * occupies that coordinate. If one is found, we return whatever
   * displayInfo it has at those coordinates
   * 
   */
  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);
  }

  /**
   * his method takes a Coordinate, and sees which (if any) Ship
   * occupies that coordinate. If one is found, we return whatever
   * displayInfo for enenmy it has at those coordinates
   * 
   */
  public T whatIsAtForEnemy(Coordinate where) {
    if (this.enemyMisses.contains(where) == true) {
      return this.missInfo;
    } else {
      return whatIsAt(where, false);
    }
  }

  /**
   * This method should search for any ship that occupies coordinate c
   * If one is found, that Ship is "hit" by the attack and should
   * record it (you already have a method for that!). Then we
   * should return this ship.
   * Fires at the coordinate specified.
   */
  public Ship<T> fireAt(Coordinate where) {
    if (where.getRow() > height || where.getColumn() > width) {
      throw new IllegalArgumentException("Coordinate system provided is out of board area");
    }

    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        s.recordHitAt(where);
        return s;
      }
    }

    this.enemyMisses.add(where);
    return null;
  }

  
  /**
     This method tell if all the ships placed in the board are destroyed..
   **/
  public boolean isAllShipsDestroyed(){
    for(Ship<T> ship : myShips){
      if(ship.isSunk() == false){
        return false;
      }
    }
    return true;
  }
}
