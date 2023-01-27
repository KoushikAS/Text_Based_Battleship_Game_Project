package edu.duke.ka266.battleship;

public interface ShipDisplayInfo<T> {

  public T getInfo(Coordinate where, boolean hit);

}
