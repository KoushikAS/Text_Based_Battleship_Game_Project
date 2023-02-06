package edu.duke.ka266.battleship;

public class V2ShipFactory implements AbstractShipFactory<Character> {

  private Ship<Character> createRectangleShip(Placement where, int width, int height, char data, String name) {
    if (where.getOrientation() != 'V' && where.getOrientation() != 'H') {
      throw new IllegalArgumentException("Orientation provided is wrong" + where.getOrientation());
    }

    if (where.getOrientation() == 'V') {
      return new RectangleShip<Character>(name, where.getWhere(), width, height, data, '*');
    } else {
      return new RectangleShip<Character>(name, where.getWhere(), height, width, data, '*');
    }
  }

  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    return createRectangleShip(where, 1, 2, 's', "Submarine");
  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {
    if (where.getOrientation() != 'U' && where.getOrientation() != 'R' &&
        where.getOrientation() != 'D' && where.getOrientation() != 'L') {
      throw new IllegalArgumentException("Orientation provided is wrong" + where.getOrientation());
    }

    return new FourCoordinateShip<Character>("BattleShip", where.getWhere(), where.getOrientation(), 'b', '*');
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    if (where.getOrientation() != 'U' && where.getOrientation() != 'R' &&
        where.getOrientation() != 'D' && where.getOrientation() != 'L') {
      throw new IllegalArgumentException("Orientation provided is wrong" + where.getOrientation());
    }

    return new SevenCoordinateShip<Character>("Carrier", where.getWhere(), where.getOrientation(), 'c', '*');
  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    return createRectangleShip(where, 1, 3, 'd', "Destroyer");
  }

}
