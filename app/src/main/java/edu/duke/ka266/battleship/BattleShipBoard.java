
package edu.duke.ka266.battleship;

public class BattleShipBoard implements Board {

  private final int width;
  private final int height;

  
 /**
   * Constructs a BattleShipBoard with the specified width
   * and height
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */
  BattleShipBoard(int w, int h) {

    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }

    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }

    this.width = w;
    this.height = h;
  }

 /**
   * Gets Width of BattleShipBoard 
   */
  public int getWidth() {
    return this.width;
  }
 
 /**
   * Gets Height of BattleShipBoard 
   */
  public int getHeight() {
    return this.height;
  }

}