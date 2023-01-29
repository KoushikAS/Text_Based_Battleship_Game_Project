package edu.duke.ka266.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {

  /**
   * Checks if the ship being added collides with any other ship that is already
   * placed int the board
   **/
  NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {

    for (Coordinate coordinate : theShip.getCoordinates()) {
      try {
        if (theBoard.whatIsAt(coordinate) != null) {
          return false;
        }
      } catch (IllegalArgumentException e) {
        // ignore as the ship is still not colliding with anyother ship.
        // Should Check this condtion in InBoundRuleCheck.
      }
    }
    return true;
  }

}
