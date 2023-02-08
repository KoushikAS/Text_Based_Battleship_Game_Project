package edu.duke.ka266.battleship;

public class Coordinate {

  private final int row;
  private final int column;

  /**
   * Reading Coordinate object from a String.
   */
  Coordinate(String descr) {

    if (descr == null) {
      throw new IllegalArgumentException("No Input was given");
    }

    if (descr.length() < 2) {
      throw new IllegalArgumentException("Not Enough Input was given");
    }

    char rowLetter = descr.toUpperCase().charAt(0);
    if (rowLetter < 'A' || rowLetter > 'Z') {
      throw new IllegalArgumentException("Row character should be an Aplha character but is " + descr.charAt(0));
    }

    this.row = (int) rowLetter - 'A';

    String columnString = descr.substring(1);
    try {

      this.column = Integer.parseInt(columnString);

    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Row character should be an Aplha character but is " + descr.charAt(0));
    }

  }

  Coordinate(int r, int c) {
    this.row = r;
    this.column = c;
  }

  public int getRow() {
    return this.row;
  }

  public int getColumn() {
    return this.column;
  }


  @Override
  public boolean equals(Object o) {
    if (o != null && o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o;
      return row == c.row && column == c.column;
    }
    return false;
  }

  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

}
