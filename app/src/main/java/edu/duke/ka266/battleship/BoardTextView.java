package edu.duke.ka266.battleship;

import java.util.function.Function;

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

  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
    StringBuilder ans = new StringBuilder("");

    ans.append(makeHeader());

    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getHeight(); i++) {
      ans.append((char) (i + 65) + " ");
      sep = "";
      for (int j = 0; j < toDisplay.getWidth(); j++) {
        ans.append(sep);

        Character cell = getSquareFn.apply(new Coordinate(i, j));
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

  /**
   * Displaying theBoard in a Textual format for self.
   */
  public String displayMyOwnBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForSelf(c));
  }

  /**
   * Displaying theBoard in a Textual format for Enemy.
   */
  public String displayEnemyBoard() {
    return displayAnyBoard((c) -> toDisplay.whatIsAtForEnemy(c));
  }

  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
    String[] myBoardDisplay = this.displayMyOwnBoard().split("\n");
    String[] enemyBoardDisplay = enemyView.displayEnemyBoard().split("\n");

    String[] display = new String[this.toDisplay.getHeight() + 3];

    StringBuilder headerDisplay = new StringBuilder();
    headerDisplay.append(" ".repeat(4 * this.toDisplay.getWidth() + 25));
  
    headerDisplay.insert(5, myHeader);
      headerDisplay.insert((2 * this.toDisplay.getWidth()) + 22, enemyHeader);
      display[0] = headerDisplay.toString();
      
      for(int i =0; i <myBoardDisplay.length ;i++ ){
      StringBuilder boardDisplay = new StringBuilder();
      boardDisplay.append(" ".repeat(4 * this.toDisplay.getWidth() + 25));    
      boardDisplay.insert(0, myBoardDisplay[i]);
      boardDisplay.insert((2 * this.toDisplay.getWidth()) + 19,
      enemyBoardDisplay[i]);
      display[i+1] = boardDisplay.toString();
      }
    return String.join("\n", display);
  }

}
