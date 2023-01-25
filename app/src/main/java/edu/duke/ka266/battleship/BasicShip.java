package edu.duke.ka266.battleship;

public class BasicShip implements Ship<Character> {

  private final Coordinate myLocation;

  BasicShip(Coordinate loc){
    myLocation = loc;
  }

  /**
   *Returns if a ship occupies this cooridnate
   */ 
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    return where.equals(myLocation);
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
     Returns the display information at the coordinate.
   */
  @Override
  public Character getDisplayInfoAt(Coordinate where) {
    return 's';
  }
  

}
