package edu.duke.ka266.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T> {

  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> displayInfo) {
    myPieces = new HashMap<Coordinate, Boolean>();
    myDisplayInfo = displayInfo;

    for (Coordinate loc : where) {
      myPieces.put(loc, false);
    }
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

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Returns the display information at the coordinate.
   */
  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO this is not right. We need to
    // look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);

  }

}
