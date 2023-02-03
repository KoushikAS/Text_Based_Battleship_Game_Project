package edu.duke.ka266.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_checkMyRule() {
    NoCollisionRuleChecker<Character> ruleChecker = new NoCollisionRuleChecker<>(null);
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(3, 3, ruleChecker, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();

    b1.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate("B2"), 'v')));
    String expected = "That placement is invalid: the ship overlaps another ship.";

    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'v')), b1));
    assertEquals(expected,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A2"), 'v')), b1));
    assertEquals(expected,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'h')), b1));

  }

  @Test
  public void test_NoCollisionInboundRuleChecker() {
    NoCollisionRuleChecker<Character> ruleChecker = new NoCollisionRuleChecker<>(new InBoundsRuleChecker<>(null));
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(3, 3, ruleChecker, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();

    b1.tryAddShip(shipFactory.makeSubmarine(new Placement(new Coordinate("B2"), 'v')));
    String expected = "That placement is invalid: the ship overlaps another ship.";

    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'v')), b1));
    assertEquals(expected,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A2"), 'v')), b1));
    assertEquals(expected,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B1"), 'h')), b1));
    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A0"), 'h')), b1));
    assertEquals(null,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A1"), 'v')), b1));
    assertEquals(expected,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("C1"), 'h')), b1));
    assertEquals(expected,
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("A2"), 'v')), b1));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("E1"), 'h')), b1));
    assertEquals("That placement is invalid: the ship goes off the right of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate("B11"), 'v')), b1));
    assertEquals("That placement is invalid: the ship goes off the top of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate(-1, 0), 'h')), b1));
    assertEquals("That placement is invalid: the ship goes off the left of the board.",
        ruleChecker.checkPlacement(shipFactory.makeSubmarine(new Placement(new Coordinate(0, -1), 'v')), b1));
  }

}
