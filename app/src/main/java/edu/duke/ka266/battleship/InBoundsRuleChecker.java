package edu.duke.ka266.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    Iterable<Coordinate> coordinates = theShip.getCoordinates();
    for (Coordinate coordinate: coordinates){
      if(0 > coordinate.getRow() || coordinate.getRow() >= theBoard.getHeight() ||
         0 > coordinate.getColumn() || coordinate.getColumn() >= theBoard.getWidth()){
        return false;
      }
  }

  return true;
}

}
