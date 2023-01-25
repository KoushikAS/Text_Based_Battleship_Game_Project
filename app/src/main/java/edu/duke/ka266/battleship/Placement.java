package edu.duke.ka266.battleship;

public class Placement {

  private final Coordinate where;
  private final char orientation;

  private void parsePlacement() {
    if (orientation < 'A' || orientation > 'Z') {
      throw new IllegalArgumentException("Row character should be an Aplha character but is " + orientation);
    }
  }

  Placement(Coordinate w, char o) {
    where = w;
    orientation = Character.toUpperCase(o);
    parsePlacement();
  }

  /**
   * Creating Placement object from a string.
   */
  Placement(String descr) {

    if(descr == null){
       throw new IllegalArgumentException("No Input was given");
    }

     if(descr.length() <3){
       throw new IllegalArgumentException("Not Enough Input was given");
    }

    String w = descr.substring(0, descr.length() - 1);
    where = new Coordinate(w);
    orientation = descr.toUpperCase().charAt(descr.length() - 1);
    parsePlacement();
  }

  public Coordinate getWhere() {
    return this.where;
  }

  public char getOrientation() {
    return this.orientation;
  }

  @Override
  public boolean equals(Object o) {
    if (o != null && o.getClass().equals(getClass())) {
      Placement p = (Placement) o;
      return where.equals(p.getWhere()) && orientation == p.orientation;
    }
    return false;
  }

  @Override
  public String toString() {
    return "Place " + where + "  with " + orientation;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

}
