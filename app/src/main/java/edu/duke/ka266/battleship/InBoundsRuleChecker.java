package edu.duke.ka266.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  /**
   * Checks whether the ship being is added is out of bounds for the borad
   * selected.
   */
  InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  /**
     Checks if the ship is inbound and returns corresponding error message if there is error.
     Otherwise returns null.
   **/
  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    Iterable<Coordinate> coordinates = theShip.getCoordinates();
    for (Coordinate coordinate : coordinates) {
      if (0 > coordinate.getRow()) {
        return "That placement is invalid: the ship goes off the top of the board.";
      }
      if (coordinate.getRow() >= theBoard.getHeight()) {
        return "That placement is invalid: the ship goes off the bottom of the board.";
      }

      if (0 > coordinate.getColumn()) {
        return "That placement is invalid: the ship goes off the left of the board.";
      }

      if (coordinate.getColumn() >= theBoard.getWidth()) {
        return "That placement is invalid: the ship goes off the right of the board.";
      }

    }

    return null;
  }

}
