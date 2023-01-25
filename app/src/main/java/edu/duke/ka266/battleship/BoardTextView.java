package edu.duke.ka266.battleship;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the
 * enemy's board.
 */
public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board<Character> toDisplay;

  /**
   * Constructs a BoardView, given the board it will display.
   * 
   * @param toDisplay is the Board to display
   */
  public BoardTextView(Board toDisplay) {
    this.toDisplay = toDisplay;

    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }

  /**
   * This makes t he header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }

  public String displayMyOwnBoard() {
    StringBuilder ans = new StringBuilder("");

    ans.append(makeHeader());

    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getHeight(); i++) {
      ans.append((char) (i + 65) + " ");
      sep = "";
      for (int j = 0; j < toDisplay.getWidth(); j++) {
        ans.append(sep);
        
        Character cell = toDisplay.whatIsAt(new Coordinate(i, j));
        if (cell == null) {
          ans.append(" ");
        } else {
          ans.append(cell);
        }
        
        sep = "|";
      }
      ans.append(" " + (char) (i + 65));
      ans.append("\n");
    }
    ans.append(makeHeader());

    return ans.toString();
  }

}
