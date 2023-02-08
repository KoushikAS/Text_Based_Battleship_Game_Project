package edu.duke.ka266.battleship;

import java.util.HashMap;
import java.util.Iterator;

public abstract class BasicShip<T> implements Ship<T> {

  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected ShipDisplayInfo<T> enemyDisplayInfo;
  protected final String name;
  protected Coordinate upperLeft;

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> displayInfo, ShipDisplayInfo<T> enemyDisplayInfo,
      String name, Coordinate upperLeft) {
    myPieces = new HashMap<Coordinate, Boolean>();
    myDisplayInfo = displayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
    for (Coordinate loc : where) {
      myPieces.put(loc, false);
    }
    this.name = name;
    this.upperLeft = upperLeft;
  }

  /**
   * Returns if a ship occupies this cooridnate
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    if (myPieces.containsKey(where)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the ship is sunk
   */
  @Override
  public boolean isSunk() {

    Iterator<HashMap.Entry<Coordinate, Boolean>> it = myPieces.entrySet().iterator();

    while (it.hasNext()) {
      HashMap.Entry<Coordinate, Boolean> entry = it.next();
      if (entry.getValue() == false) {
        return false;
      }
    }
    return true;
  }

  /**
   * Records a hit at the coorinate
   * Note if the Coordinate is not there in the ship it will throw na exception
   */
  @Override
  public void recordHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);
    myPieces.replace(where, true);
  }

  /**
   * checks if the Ship was hit at the coordiante system
   * Note throws an exception if the coordinates are no present in the ship
   */
  @Override
  public boolean wasHitAt(Coordinate where) {
    checkCoordinateInThisShip(where);

    return myPieces.get(where);
  }

  /**
   * Returns the display information at the coordinate.
   * Note throws an exception if the coorinates are not present in the ship
   */
  @Override
  public T getDisplayInfoAt(Coordinate where, boolean myShip) {
    checkCoordinateInThisShip(where);

    if (myShip == true) {
      if (myPieces.get(where).equals(true)) {
        return myDisplayInfo.getInfo(where, true);
      } else {
        return myDisplayInfo.getInfo(where, false);
      }
    }
    if (myPieces.get(where).equals(true)) {
      return enemyDisplayInfo.getInfo(where, true);
    } else {
      return enemyDisplayInfo.getInfo(where, false);
    }
  }

  /**
   * To check if the given coordinates is present in the ship or else throw an
   * exception
   **/
  protected void checkCoordinateInThisShip(Coordinate c) {
    if (c == null) {
      throw new IllegalArgumentException("Coordinate  of a ship cannot be null");
    }

    if (myPieces.containsKey(c) == false) {
      throw new IllegalArgumentException("Coordinates is not part of the ship");
    }
  }

  /**
   * Get all of the Coordinates that this Ship occupies.
   * 
   * @return An Iterable with the coordinates that this Ship occupies
   */
  @Override
  public Iterable<Coordinate> getCoordinates() {
    return myPieces.keySet();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Coordinate getUpperLeftCoordinate(){
    return this.upperLeft;
  }

  /**
   * Move the ship to the new coordiantes
   **/
  @Override
  public void MoveCoordiantes(Coordinate newUpperLeft) {

    int diffRow = newUpperLeft.getRow() - this.upperLeft.getRow();
    int diffColumn = newUpperLeft.getColumn() - this.upperLeft.getColumn();
    HashMap<Coordinate, Boolean> newMap = new HashMap<Coordinate, Boolean>();
    
    for (Coordinate coordinate : this.myPieces.keySet()) {
      Boolean value = this.myPieces.get(coordinate);
      //      this.myPieces.remove(coordinate);
      Coordinate newCoords = new Coordinate(coordinate.getRow() + diffRow, coordinate.getColumn() + diffColumn);
      newMap.put(newCoords, value);
   }

    this.myPieces = newMap;
    this.upperLeft = newUpperLeft;
  }

}
