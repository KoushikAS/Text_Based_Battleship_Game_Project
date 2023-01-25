package edu.duke.ka266.battleship;

public interface Board<T> {

  public int getWidth();

  public int getHeight();

  /**
   * tryAddShip will check for validity of the placement and returns
   * true if the placement was ok, and false if it was invalid (and thus not
   * actually placed).
   * 
   **/
  public boolean tryAddShip(Ship<T>  toAdd);

    /**
   * his method takes a Coordinate, and sees which (if any) Ship
   * occupies that coordinate. If one is found, we return whatever
   * displayInfo it has at those coordinates
   * 
   */
  public T whatIsAt(Coordinate where);
}
