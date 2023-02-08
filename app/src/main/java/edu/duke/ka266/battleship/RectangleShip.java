package edu.duke.ka266.battleship;

import java.util.HashMap;
import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {

  RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> displayData,
      ShipDisplayInfo<T> enemyDisplayData) {
    super(makeCoords(upperLeft, width, height), displayData, enemyDisplayData, name, upperLeft);
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("test ship", upperLeft, 1, 1, data, onHit);
  }

  private static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
    HashSet<Coordinate> coordinates = new HashSet<Coordinate>();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + i, upperLeft.getColumn() + j));
      }
    }

    return coordinates;
  }

  
}
