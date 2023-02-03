package edu.duke.ka266.battleship;

public interface Board<T> {

  public int getWidth();

  public int getHeight();

  /**
   * tryAddShip will check for validity of the placement and returns
   * null if the placement was ok,
   * else errorMEssage if it was invalid (and thus not
   * actually placed).
   * 
   **/
  public String tryAddShip(Ship<T> toAdd);

  /**
   * his method takes a Coordinate, and sees which (if any) Ship
   * occupies that coordinate. If one is found, we return whatever
   * displayInfo it has at those coordinates
   * 
   */
  public T whatIsAtForSelf(Coordinate where);

    /**
   * his method takes a Coordinate, and sees which (if any) Ship
   * occupies that coordinate For the enemy. If one is found, we return whatever
   * displayInfo it has at those coordinates
   * 
   */
  public T whatIsAtForEnemy(Coordinate where);

  /**
   * This method should search for any ship that occupies coordinate c
   * If one is found, that Ship is "hit" by the attack and should
   * record it (you already have a method for that!). Then we
   * should return this ship.
   * Fires at the coordinate specified.
   */
  public Ship<T> fireAt(Coordinate c);
}
