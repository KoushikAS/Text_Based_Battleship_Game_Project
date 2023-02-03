package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_CheckMyRule() {

    InBoundsRuleChecker<Character> ruleChecker = new InBoundsRuleChecker<>(null);
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(2, 2, ruleChecker, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();

    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A1"), 'v')), b1));
    assertEquals("That placement is invalid: the ship goes off the right of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A1"), 'h')), b1));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B0"), 'v')), b1));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("C1"), 'h')), b1));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B11"), 'v')), b1));
    assertEquals("That placement is invalid: the ship goes off the top of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate(-1, 0), 'h')), b1));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate(1, 0), 'v')), b1));
    assertEquals("That placement is invalid: the ship goes off the left of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate(0, -1), 'h')), b1));
  }

}
