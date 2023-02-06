package edu.duke.ka266.battleship;

import java.util.HashSet;

public class FourCoordinateShip<T> extends BasicShip<T> {

  private final String name;

  public FourCoordinateShip(String name, Coordinate upperLeft, char orientation, ShipDisplayInfo<T> displayData,
      ShipDisplayInfo<T> enemyDisplayData) {
    super(makeCoords(upperLeft, orientation), displayData, enemyDisplayData);
    this.name = name;
  }

  public FourCoordinateShip(String name, Coordinate upperLeft, char orientation, T data, T onHit) {
    this(name, upperLeft, orientation, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  public FourCoordinateShip(Coordinate upperLeft, T data, T onHit) {
    this("test ship", upperLeft, 'U', data, onHit);
  }

  private static HashSet<Coordinate> makeCoords(Coordinate upperLeft, char orientation) {
    HashSet<Coordinate> coordinates = new HashSet<Coordinate>();

    if (Character.toUpperCase(orientation) == 'U') {
      coordinates.add(new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 1));
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + i));
      }
    } else if (Character.toUpperCase(orientation) == 'R') {
      coordinates.add(new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 1));
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + i, upperLeft.getColumn()));
      }
    } else if (Character.toUpperCase(orientation) == 'D') {
      coordinates.add(new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + 1));
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + i));
      }
    } else {
      coordinates.add(new Coordinate(upperLeft.getRow() +1, upperLeft.getColumn()));
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + i, upperLeft.getColumn() + 1));
      }
    }

    return coordinates;
  }

  @Override
  public String getName() {
    return name;
  }

}
