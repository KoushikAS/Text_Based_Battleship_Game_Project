package edu.duke.ka266.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {

  T myData;
  T onHit;

  SimpleShipDisplayInfo(T d, T h) {
    myData = d;
    onHit = h;
  }

  @Override
  public T getInfo(Coordinate where, boolean hit) {
    if (hit) {
      return onHit;
    } else {
      return myData;
    }
  }
}
