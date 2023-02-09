package edu.duke.ka266.battleship;

public abstract class PlacementRuleChecker<T> {

  // Is used to chain different rules of the game.
  private final PlacementRuleChecker<T> next;

  /**
   * Is used to check if the placement selected obeys all the rules of the game.
   **/
  public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    this.next = next;
  }

  /**
   * Is the Method which checks if theShip follows the Rule
   * Returns NULL if no errors.
   * Otherwise returns Error message.
   **/
  protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);

  /**
   * Is used to check all the rules in the chain.
   * Returns NULL if no errors.
   * Otherwise returns Error message.
   **/
  public String checkPlacement(Ship<T> theShip, Board<T> theBoard) {
    // if we fail our own rule: stop the placement is not legal
    String errorMessage = checkMyRule(theShip, theBoard);
    if (errorMessage != null) {
      return errorMessage;
    }
    // other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard);
    }
    // if there are no more rules, then the placement is legal
    return null;
  }

}
