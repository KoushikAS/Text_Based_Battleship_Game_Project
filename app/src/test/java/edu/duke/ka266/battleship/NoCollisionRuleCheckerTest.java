package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_checkMyRule() {
    NoCollisionRuleChecker<Character> ruleChecker = new NoCollisionRuleChecker<>(null);
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(3, 3, ruleChecker);
    V1ShipFactory shipFactory = new V1ShipFactory();

    b1.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate("B2"), 'v')));

    assertTrue(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertTrue(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'v')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A2"), 'v')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'h')), b1));

  }

  @Test
  public void test_NoCollisionInboundRuleChecker() {
    NoCollisionRuleChecker<Character> ruleChecker = new NoCollisionRuleChecker<>(new InBoundsRuleChecker<>(null));
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(3, 3, ruleChecker);
    V1ShipFactory shipFactory = new V1ShipFactory();

    b1.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate("B2"), 'v')));

    assertTrue(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertTrue(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'v')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A2"), 'v')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'h')), b1));
    assertTrue(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertTrue(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A1"), 'v')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("C1"), 'h')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A2"), 'v')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("E1"), 'h')), b1));
     assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B11"), 'v')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate(-1, 0), 'h')), b1));
    assertFalse(ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate(0, -1), 'v')), b1));

  }

}
