package edu.duke.ka266.battleship;

import java.util.HashSet;

public class SevenCoordinateShip<T> extends BasicShip<T> {

  private final String name;

  public SevenCoordinateShip(String name, Coordinate upperLeft, char orientation, ShipDisplayInfo<T> displayData,
      ShipDisplayInfo<T> enemyDisplayData) {
    super(makeCoords(upperLeft, orientation), displayData, enemyDisplayData);
    this.name = name;
  }

  public SevenCoordinateShip(String name, Coordinate upperLeft, char orientation, T data, T onHit) {
    this(name, upperLeft, orientation, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  public SevenCoordinateShip(Coordinate upperLeft, T data, T onHit) {
    this("test ship", upperLeft, 'U', data, onHit);
  }

  private static HashSet<Coordinate> makeCoords(Coordinate upperLeft, char orientation) {
    HashSet<Coordinate> coordinates = new HashSet<Coordinate>();

    if (Character.toUpperCase(orientation) == 'U') {
      for (int i = 0; i < 4; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + i, upperLeft.getColumn()));
      }
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + 2 + i, upperLeft.getColumn() + 1));
      }
    } else if (Character.toUpperCase(orientation) == 'R') {
      for (int i = 0; i < 4; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 1 + i));
      }
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + i));
      }
    } else if (Character.toUpperCase(orientation) == 'D') {
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + i, upperLeft.getColumn()));
      }
      for (int i = 0; i < 4; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + 1 + i, upperLeft.getColumn() + 1));
      }
    } else {
      for (int i = 0; i < 3; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow(), upperLeft.getColumn() + 2 + i));
      }
      for (int i = 0; i < 4; i++) {
        coordinates.add(new Coordinate(upperLeft.getRow() + 1, upperLeft.getColumn() + i));
      }
    }

    return coordinates;
  }

  @Override
  public String getName() {
    return name;
  }

}
