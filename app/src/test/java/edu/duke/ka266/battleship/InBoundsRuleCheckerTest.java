package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_CheckMyRule() {

    InBoundsRuleChecker<Character> ruleChecker = new InBoundsRuleChecker<>(null);
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(2, 2, ruleChecker);
    V1ShipFactory shipFactory = new V1ShipFactory();

    assertTrue(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertTrue(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate("A1"), 'v')), b1));
    assertFalse(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate("A1"), 'h')), b1));
    assertFalse(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate("B0"), 'v')), b1));
    assertFalse(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate("C1"), 'h')), b1));
    assertFalse(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate("B11"), 'v')), b1));
    assertFalse(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate(-1, 0), 'h')), b1));
    assertFalse(ruleChecker.checkMyRule(shipFactory.makeSubmarine(new Placement(new Coordinate(1, 0), 'v')), b1));

  }

}
